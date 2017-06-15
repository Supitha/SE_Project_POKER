/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerserver;

import java.util.ArrayList;

/**
 *
 * @author supithaweerasinghe
 */
public class GameLogicHandsofCards {
    public ArrayList player1 = new ArrayList();
    public ArrayList player2 = new ArrayList();
    public ArrayList player3 = new ArrayList();
    public ArrayList player4 = new ArrayList();
    public ArrayList player5 = new ArrayList();
    public ArrayList player6 = new ArrayList();
    
    
    void handsofCards(int no_of_players, ArrayList initial,ArrayList normal){
        logic_initial(no_of_players, initial);
        logic_normal(no_of_players, normal);
        
        ////////
//            for(int i=0;i<player6.size();i++){
//                Cards a = (Cards)player6.get(i);
//                System.out.print(a.getCnumber());
//                System.out.println(a.getCtype());
//            }
        //////////
    }
    
    void logic_initial(int players,ArrayList initial){
        int no_of_cards = players*2;
        for(int i=0;i<no_of_cards;i++){
            switch (players) {
                case 2:
                    if(i==0||i==1){
                        player1.add(initial.get(i));
                    }else if (i==2||i==3){
                        player2.add(initial.get(i));
                    }   break;
                case 3:
                    if(i==0||i==1){
                        player1.add(initial.get(i));
                    }else if (i==2||i==3){
                        player2.add(initial.get(i));
                    }else if (i==4||i==5){
                        player3.add(initial.get(i));
                    }   break;
                case 4:
                    if(i==0||i==1){
                        player1.add(initial.get(i));
                    }else if (i==2||i==3){
                        player2.add(initial.get(i));
                    }else if (i==4||i==5){
                        player3.add(initial.get(i));
                    }else if (i==6||i==7){
                        player4.add(initial.get(i));
                    }   break;
                case 5:
                    if(i==0||i==1){
                        player1.add(initial.get(i));
                    }else if (i==2||i==3){
                        player2.add(initial.get(i));
                    }else if (i==4||i==5){
                        player3.add(initial.get(i));
                    }else if (i==6||i==7){
                        player4.add(initial.get(i));
                    }else if (i==8||i==9){
                        player5.add(initial.add(i));
                    }   break;
                case 6:
                    if(i==0||i==1){
                        player1.add(initial.get(i));
                    }else if (i==2||i==3){
                        player2.add(initial.get(i));
                    }else if (i==4||i==5){
                        player3.add(initial.get(i));
                    }else if (i==6||i==7){
                        player4.add(initial.get(i));
                    }else if (i==8||i==9){
                        player5.add(initial.add(i));
                    }else if (i==10||i==11){
                        player6.add(initial.add(i));
                    }   break;
                default:
                    break;
            }
        }
    }
    
    void logic_normal(int players,ArrayList normal){
        int no_of_cards = players*3;
        for(int i=0;i<no_of_cards;i++){
            switch (players) {
                case 2:
                    if(i==0||i==1||i==2){
                        player1.add(normal.get(i));
                    }else if (i==3||i==4||i==5){
                        player2.add(normal.get(i));
                    }   break;
                case 3:
                    if(i==0||i==1||i==2){
                        player1.add(normal.get(i));
                    }else if (i==3||i==4||i==5){
                        player2.add(normal.get(i));
                    }else if (i==6||i==7||i==8){
                        player3.add(normal.get(i));
                    }   break;
                case 4:
                    if(i==0||i==1||i==2){
                        player1.add(normal.get(i));
                    }else if (i==3||i==4||i==5){
                        player2.add(normal.get(i));
                    }else if (i==6||i==7||i==8){
                        player3.add(normal.get(i));
                    }else if (i==9||i==10||i==11){
                        player4.add(normal.get(i));
                    }   break;
                case 5:
                    if(i==0||i==1||i==2){
                        player1.add(normal.get(i));
                    }else if (i==3||i==4||i==5){
                        player2.add(normal.get(i));
                    }else if (i==6||i==7||i==8){
                        player3.add(normal.get(i));
                    }else if (i==9||i==10||i==11){
                        player4.add(normal.get(i));
                    }else if (i==12||i==13||i==14){
                        player5.add(normal.add(i));
                    }   break;
                case 6:
                    if(i==0||i==1||i==2){
                        player1.add(normal.get(i));
                    }else if (i==3||i==4||i==5){
                        player2.add(normal.get(i));
                    }else if (i==6||i==7||i==8){
                        player3.add(normal.get(i));
                    }else if (i==9||i==10||i==11){
                        player4.add(normal.get(i));
                    }else if (i==12||i==13||i==14){
                        player5.add(normal.add(i));
                    }else if (i==15||i==16||i==17){
                        player6.add(normal.add(i));
                    }   break;
                default:
                    break;
            }
        }
    }
}
