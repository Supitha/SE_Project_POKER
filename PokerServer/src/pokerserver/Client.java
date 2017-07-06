package pokerserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author supithaweerasinghe
 */
public class Client {

    private JFrame frame = new JFrame("Foker Client");

    Socket connectToServer() throws IOException {
        String serverAddress;
        serverAddress = JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Welcome to the Foker",
                JOptionPane.QUESTION_MESSAGE);

        Socket socket = new Socket(serverAddress, 9898);
        return socket;
    }

    String player_name() {
        String name;
        name = JOptionPane.showInputDialog(
                frame,
                "Enter your name :",
                "Welcome to the Foker",
                JOptionPane.QUESTION_MESSAGE);
        return name;
    }

    int number_of_players() {
        int number = Integer.parseInt(JOptionPane.showInputDialog(
                frame,
                "Enter number of players you want:",
                "First player privilage",
                JOptionPane.QUESTION_MESSAGE));
        return number;
    }

    public static void main(String args[]) throws IOException {
        Client client = new Client();
        Socket socket = client.connectToServer();

        String arrayListString;
        String changedCards;
        String cardsTobeChanged = "";

        //receving
        Scanner s2 = new Scanner(socket.getInputStream());
        String priority = s2.next();

        //gettting priority from server
        if ("FIRST".equals(priority)) {
            int x = client.number_of_players();
            //sending number of players
            PrintStream p = new PrintStream(socket.getOutputStream());
            p.println(x);
        } else if ("PLEASE_WAIT".equals(priority)) {
            System.out.println("Try again in later");
            System.exit(0);
        }

        String name = client.player_name();

        //sending name to server
        PrintStream p = new PrintStream(socket.getOutputStream());
        p.println(name);

        ////
        //s2 = new Scanner(socket.getInputStream());
        //String names = s2.next();
        //System.out.println(names);
        ////
        //receving hand of cards
        s2 = new Scanner(socket.getInputStream());
        arrayListString = s2.next();

        List<String> myList = new ArrayList<String>(Arrays.asList(arrayListString.split(",")));
        List<String> hand = new ArrayList();
        List<String> initial = new ArrayList();
        hand = myList.subList(0, 5);
        initial = myList.subList(5, myList.size());

        System.out.println("Your Hand");

        for (int i = 0; i < hand.size(); i++) {
            String crd = hand.get(i);
            System.out.println(crd);
        }

//        System.out.println("Initial cads");
//        
//        for (int i = 0; i < initial.size(); i++) {
//            String crd = initial.get(i);
//            System.out.println(crd);
//        }
        System.out.println("Others initial cards");

        List<String> demo = client.initialCards(hand, initial);
        for (int i = 0; i < demo.size(); i++) {
            String crd = demo.get(i);
            System.out.println(crd);
        }

        //Which cards need to be changed
        Scanner s = new Scanner(System.in);

        ////////////////////////////////////////////////////////////////////////
        System.out.println("Mow many cards need to be changed :");
        int temp = s.nextInt();

        if (temp == 0) {
            cardsTobeChanged = "non";
            p.println(cardsTobeChanged);
        } else {
            System.out.println("Enter cards need to be changed :");
            ArrayList chng = new ArrayList();
            for (int i = 0; i < temp; i++) {
                chng.add(s.nextInt() - 1);
            }

            ////////////////////////////////////////////////////////////////////////
            cardsTobeChanged = client.stringClear(chng.toString());

            ////////////////////////////////////////////////////////////////////////
            //send how many cards need to be changed?
            //p.println(Integer.toString(chng.size()));
            p.println(cardsTobeChanged);

            //Get changed cards
            changedCards = s2.next();
            List<String> chndCrd = new ArrayList<String>(Arrays.asList(changedCards.split(",")));

            for (int i = 0; i < chndCrd.size(); i++) {
                String crd = chndCrd.get(i);
                System.out.println(crd);
            }
        }

        //Get winner
        String winner = s2.next();
        System.out.println(winner);

        //sending number to server
        //PrintStream p = new PrintStream(socket.getOutputStream());
        //p.println(number);
        //int tmp = s2.nextInt();
        //System.out.println(tmp);
    }

    String stringClear(String temp) {
        temp = temp.replaceAll("\\s+", "");
        temp = temp.substring(1, (temp.length() - 1));
        return temp;
    }

    ArrayList initialCards(List<String> hand, List<String> initial) {
        ArrayList fin = new ArrayList();

        for (int x = 0; x < hand.size(); x++) {
            for (int y = 0; y < initial.size(); y++) {
                if (hand.get(x).equals(initial.get(y))) {
                    initial.set(y, "null");
                }
            }
        }

        for (int z = 0; z < initial.size(); z++) {
            if ("null".equals(initial.get(z))) {
                z++;
            } else {
                fin.add(initial.get(z));
            }
        }
        return fin;
    }

}
