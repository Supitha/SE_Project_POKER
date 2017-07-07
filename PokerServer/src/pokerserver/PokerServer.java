/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author supithaweerasinghe
 */
public class PokerServer {

    public static int number_of_Players = 0;
    public static int total_number = 0;
    public static int marks = 0;
    public static ArrayList player_name = new ArrayList();
    public static ServerSocket listener;

    public static ArrayList player1 = new ArrayList();
    public static ArrayList player2 = new ArrayList();
    public static ArrayList player3 = new ArrayList();
    public static ArrayList player4 = new ArrayList();
    public static ArrayList player5 = new ArrayList();
    public static ArrayList player6 = new ArrayList();

    public static ArrayList remainCards = new ArrayList();
    public static ArrayList initial = new ArrayList();
    public static ArrayList index = new ArrayList();
    
    public static String combination = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("The Game Server is running.");
        int clientNumber = 0;
        listener = new ServerSocket(9898);
        //ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                Socket game = listener.accept();
                System.out.print(clientNumber);
                if (clientNumber == 0 | clientNumber < number_of_Players) {
                    new Game(game, clientNumber++).start();
                } else {
                    //send result to client
                    PrintStream p = new PrintStream(game.getOutputStream());
                    p.println("PLEASE_WAIT");
                    //listener.close();
                    //game.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Error handling client# " + clientNumber + ": " + e);
            listener.close();
        } finally {
            listener.close();
        }
    }

    private static class Game extends Thread {

        GameLogicHandsofCards handClass = new GameLogicHandsofCards();
        private Socket socket;
        private int clientNumber;

        public Game(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
            System.out.println(clientNumber);
        }

        public void run() {
            try {

                //time out
                //TimeUnit.SECONDS.sleep(10);
                //////////testing implementation
                //pint
                //DemoCardHands demo = new DemoCardHands();
                ArrayList testobj = new ArrayList();
                ArrayList teststr = new ArrayList();
                String arrayListString;
                String initialCards;

//                testobj = demo.hand(clientNumber);
//                teststr = objectsToString(testobj);
//                arrayListString = stringClear(teststr.toString());
                PrintStream p = new PrintStream(socket.getOutputStream());
                Scanner s = new Scanner(socket.getInputStream());
                RefereePointsHandler refpt = new RefereePointsHandler();

//               System.out.println(arrayListString);
                if (clientNumber == 0) {
                    //send result to client
                    ///PrintStream p = new PrintStream(socket.getOutputStream());
                    p.println("FIRST");
                    //get number of players
                    ///Scanner s = new Scanner(socket.getInputStream());
                    number_of_Players = Integer.parseInt(s.next());
                    newGame();
                    player1 = handClass.player1;
                    player2 = handClass.player2;
                    player3 = handClass.player3;
                    player4 = handClass.player4;
                    player5 = handClass.player5;
                    player6 = handClass.player6;
                    //clear table
                    refpt.clearTable();
                } else {
                    //send result to client
                    ///PrintStream p = new PrintStream(socket.getOutputStream());
                    p.println("NORMAL");
                }

                //get name from client
                ///Scanner s = new Scanner(socket.getInputStream());
                String client_name = s.next();
                //add name to arraylist
                player_name.add(s);
                total_number = total_number + 1;

                System.out.println(number_of_Players + " - " + client_name);

                if (number_of_Players == total_number) {
                    p.println(stringClear(client_name));
                }

                ////logic calling////
                //newGame();
                /////////////////////
                if (clientNumber == 0) {
                    testobj = player1;
                } else if (clientNumber == 1) {
                    testobj = player2;
                } else if (clientNumber == 2) {
                    testobj = player3;
                } else if (clientNumber == 3) {
                    testobj = player4;
                } else if (clientNumber == 4) {
                    testobj = player5;
                } else if (clientNumber == 5) {
                    testobj = player6;
                }

                teststr = objectsToString(testobj);
                arrayListString = stringClear(teststr.toString());
                initialCards = stringClear(objectsToString(initial).toString());
                String handWithInitial = concatanation(arrayListString, initialCards);

                //send card hand to client
                ///PrintStream p = new PrintStream(socket.getOutputStream());
                p.println(handWithInitial);

                try {
                        TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PokerServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //get card's indexes need to be changed?
                String tempindex = s.next();
                int val;
                ArrayList stringNewHand = new ArrayList();

                if (!"non".equals(tempindex)) {
                    val = 1;
                    index = stringtoArrayList(tempindex);
                    int how_many = index.size();

                    ArrayList changedCards = new ArrayList();
                    //new cards
                    changedCards = cardChange(how_many);
                    ArrayList newcards = objectsToString(changedCards);
                    String chngdCrds = stringClear(newcards.toString());

                    //player's final hand
                    ArrayList original = testobj;
                    ArrayList sringoriginal = objectsToString(original);
                    stringNewHand = creatingNewHand(index, sringoriginal, newcards);

                    //send changed cards
                    p.println(chngdCrds);
                } else {
                    stringNewHand = teststr;
                    val = 0;
                }
                
                //////////////////////////ffffffffffff/////////////////////////////
                //calculating points
                marks = giveMarks(stringNewHand, val);

                //send data to DB
                PointsDetails pd = new PointsDetails();
                pd.setId(clientNumber);
                pd.setName(client_name);
                pd.setPoints(marks);
                pd.setCombination(combination);
                refpt.addPoints(pd);

                //in case of a need - object array of final cards |V|
                //ArrayList objNewHand = stringToObjects(stringNewHand);     
                
                try {
                    if(clientNumber==0){
                        TimeUnit.SECONDS.sleep(14);
                    }else{
                        TimeUnit.SECONDS.sleep(7);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PokerServer.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Slect winner
                String winner = refpt.getHighestPoints();
                System.out.println("WINNER IS : " + winner);

                //send winner
                p.println(winner);
                ////////////////
                //System.out.println(remainCards.size());
                ////////////////

                ////////////////
//                System.out.println("Remain cards");
//                for (int i = 0; i < remainCards.size(); i++) {
//                    Cards a = (Cards) remainCards.get(i);
//                    System.out.print(a.getCtype());
//                    System.out.println(a.getCnumber());
//                }
                ////////////////
                //get values data from client
                //Scanner s = new Scanner(sims.getInputStream());
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

        void newGame() {
            //int no_of_players = 6;

            CardLogic l = new CardLogic();
            GameLogicCardDistribution g = new GameLogicCardDistribution();
            //GameLogicHandsofCards h = new GameLogicHandsofCards();

            //create 52 cards
            l.creatingCards();
            //randomizing 52 cards
            ArrayList cards = l.randomizingCards();

            /*for(int i=0;i<52;i++){
            Cards a = (Cards)cards.get(i);
            System.out.print(a.cnumber);
            System.out.println(a.ctype);
        }*/
            //Give - pair of initial cards to each player
            //g.initialCardsDistribution(RANDOMIZED_ARRAYLIST, NUMBER_OF_PLAYERS);
            initial = g.initialCardsDistribution(cards, number_of_Players);//here
            //Give - 3 cards to each player
            ArrayList normal = g.cardsDistrubution();

            //set remain cards to this instance
            remainCards = g.remaincards;

            ///
            System.out.println("initial card sets 2x2");
            for (int i = 0; i < initial.size(); i++) {
                Cards a = (Cards) initial.get(i);
                System.out.print(a.getCtype());
                System.out.println(a.getCnumber());
            }
            ///

            ///
            System.out.println("Card sets 3x3");
            for (int i = 0; i < normal.size(); i++) {
                Cards a = (Cards) normal.get(i);
                System.out.print(a.getCtype());
                System.out.println(a.getCnumber());
            }
            ///

            ///
            g.displaycard();
            ///

            ////////////////////////////////Hands///////////////////////////////
            //Original
            handClass.handsofCards(number_of_Players, initial, normal);//here

            //initilizing DEMO 
            //DemoImplimentation demo = new DemoImplimentation();
            //demo.printCardHands(no_of_players, initial, normal);
        }

        private void log(String message) {
            System.out.println(message);
        }

        int giveMarks(ArrayList hand, int eorn) {
            int mark = 0;
            if (eorn == 0) {
                RefereeCardCombinations ref = new RefereeCardCombinations();
                 boolean refeval = false;
                refeval = ref.royalFlush(hand);
                if (refeval == false) {
                    refeval = ref.straightFlush(hand);
                    if (refeval == false) {
                        refeval = ref.fourOfaKind(hand);
                        if (refeval == false) {
                            refeval = ref.fullHouse(hand);
                            if (refeval == false) {
                                refeval = ref.flush(hand);
                                if (refeval == false) {
                                    refeval = ref.straight(hand);
                                    if (refeval == false) {
                                        refeval = ref.threeOfaKind(hand);
                                        if (refeval == false) {
                                            refeval = ref.twoPairs(hand);
                                            if (refeval == false) {
                                                refeval = ref.onePair(hand);
                                                if (refeval == false) {
                                                    mark = mark + ref.highCard(hand);
                                                combination = "HighCard";
                                                } else if (refeval == true) {
                                                    combination = "OnePair";
                                                    mark = 20;
                                                }
                                            } else if (refeval == true) {
                                                combination = "TwoPairs";
                                                mark = 25;
                                            }
                                        } else if (refeval == true) {
                                            combination = "TreeOfaKind";
                                            mark = 30;
                                        }
                                    } else if (refeval == true) {
                                        combination = "Straight";
                                        mark = 35;
                                    }
                                } else if (refeval == true) {
                                    combination = "Flush";
                                    mark = 40;
                                }
                            } else if (refeval == true) {
                                combination = "FullHouse";
                                mark = 45;
                            }
                        } else if (refeval == true) {
                            combination = "FourOfaKind";
                            mark = 50;
                        }
                    } else if (refeval == true) {
                        combination = "StraightFlush";
                        mark = 55;
                    }
                } else if (refeval == true) {
                    combination = "RoyalFlush";
                    mark = 60;
                }
                return mark;
            } else {
                RefereeCardCombinations ref = new RefereeCardCombinations();
                boolean initialChanged = false;
                boolean refeval = false;
                for (int i = 0; i < index.size(); i++) {
                    int temp1 = (int) index.get(i);
                    if (temp1 == 1 || temp1 == 2) {
                        initialChanged = true;
                    }
                }
                refeval = ref.royalFlush(hand);
                if (refeval == false) {
                    refeval = ref.straightFlush(hand);
                    if (refeval == false) {
                        refeval = ref.fourOfaKind(hand);
                        if (refeval == false) {
                            refeval = ref.fullHouse(hand);
                            if (refeval == false) {
                                refeval = ref.flush(hand);
                                if (refeval == false) {
                                    refeval = ref.straight(hand);
                                    if (refeval == false) {
                                        refeval = ref.threeOfaKind(hand);
                                        if (refeval == false) {
                                            refeval = ref.twoPairs(hand);
                                            if (refeval == false) {
                                                refeval = ref.onePair(hand);
                                                if (refeval == false) {
                                                    mark = mark + ref.highCard(hand);
                                                    combination = "HighCard";
                                                } else if (refeval == true) {
                                                    combination = "OnePair";
                                                    mark = 20;
                                                }
                                            } else if (refeval == true) {
                                                combination = "TwoPairs";
                                                mark = 25;
                                            }
                                        } else if (refeval == true) {
                                            combination = "TreeOfaKind";
                                            mark = 30;
                                        }
                                    } else if (refeval == true) {
                                        combination = "Straight";
                                        mark = 35;
                                    }
                                } else if (refeval == true) {
                                    combination = "Flush";
                                    mark = 40;
                                }
                            } else if (refeval == true) {
                                combination = "FullHouse";
                                mark = 45;
                            }
                        } else if (refeval == true) {
                            combination = "FourOfaKind";
                            mark = 50;
                        }
                    } else if (refeval == true) {
                        combination = "StraightFlush";
                        mark = 55;
                    }
                } else if (refeval == true) {
                    combination = "RoyalFlush";
                    mark = 60;
                }
                if (initialChanged == true) {
                    mark = mark - 6;
                }
                return mark;
            }
        }

        ArrayList creatingNewHand(ArrayList indexes, ArrayList hand, ArrayList replacement) {
            for (int i = 0; i < indexes.size(); i++) {
                int indx = (int) indexes.get(i);
                for (int j = 0; j < hand.size(); j++) {
                    if (j == indx) {
                        String tmp = (String) replacement.get(0);
                        replacement.remove(0);
                        hand.set(j, tmp);
                    }
                }
            }
            return hand;
        }

        ArrayList cardChange(int x) {
            ArrayList chngCrds = new ArrayList();
            for (int y = 0; y < x; y++) {
                Cards tmp = (Cards) remainCards.get(y);
                chngCrds.add(tmp);
            }
            if (x == 1) {
                remainCards.remove(0);
            } else if (x == 2) {
                remainCards.remove(0);
                remainCards.remove(0);
            } else if (x == 3) {
                remainCards.remove(0);
                remainCards.remove(0);
                remainCards.remove(0);
            }
            return chngCrds;
        }

        ArrayList stringtoArrayList(String cards) {
            ArrayList temp = new ArrayList();
            for (int i = 0; i < cards.length(); i++) {
                if (i == cards.length() - 1) {
                    String tmp2 = cards.substring(i);
                    temp.add(Integer.parseInt(tmp2));
                } else {
                    String tmp1 = cards.substring(i, i + 1);
                    if (!",".equals(tmp1)) {
                        temp.add(Integer.parseInt(tmp1));
                    }
                }
            }
            return temp;
        }

        ArrayList objectsToString(ArrayList obj) {
            ArrayList converted = new ArrayList();
            String temp_type;
            String temp_no;
            String demo;
            for (int i = 0; i < obj.size(); i++) {
                Cards x = (Cards) obj.get(i);
                temp_type = x.getCtype();
                temp_no = Integer.toString(x.getCnumber());
                demo = temp_type + temp_no;
                converted.add(demo);
            }
            return converted;
        }

        String concatanation(String a, String c) {
            String b = ",";
            String fin = a.concat(b).concat(c);
            return fin;
        }

        ArrayList stringToObjects(ArrayList strng) {
            ArrayList converted = new ArrayList();
            String temp_type;
            int temp_no;
            String demo;
            for (int i = 0; i < strng.size(); i++) {
                demo = strng.get(i).toString();
                temp_type = demo.substring(0, 1);
                temp_no = Integer.parseInt(demo.substring(1));
                Cards crd = new Cards();
                crd.setCnumber(temp_no);
                crd.setCtype(temp_type);
                converted.add(crd);
            }
            return converted;
        }

        String stringClear(String temp) {
            temp = temp.replaceAll("\\s+", "");
            temp = temp.substring(1, (temp.length() - 1));
            return temp;
        }
    }

}
