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
    
    Socket connectToServer() throws IOException{
        String serverAddress;
        serverAddress = JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Welcome to the Foker",
                JOptionPane.QUESTION_MESSAGE);

        Socket socket = new Socket(serverAddress, 9898);
        return socket;
    }
    
    String player_name(){
        String name;
        name = JOptionPane.showInputDialog(
            frame,
            "Enter your name :",
            "Welcome to the Foker",
            JOptionPane.QUESTION_MESSAGE);
        return name;
    }
    
    int number_of_players(){
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
        
        
        //receving
        Scanner s2 = new Scanner(socket.getInputStream());
        String priority = s2.next();
        
        //gettting priority from server
        if("FIRST".equals(priority)){
           int x = client.number_of_players();
           //sending number of players
           PrintStream p = new PrintStream(socket.getOutputStream());
           p.println(x);
        }else if("PLEASE_WAIT".equals(priority)){
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

        for (int i = 0; i < myList.size(); i++) {
            String crd = myList.get(i);
            System.out.println(crd);
        }
        
        //sending number to server
        //PrintStream p = new PrintStream(socket.getOutputStream());
        //p.println(number);

        //int tmp = s2.nextInt();
        //System.out.println(tmp);
    }

}
