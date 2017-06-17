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

public class PokerServer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int no_of_players = 4;
        CardLogic l = new CardLogic();
        GameLogicCardDistribution g = new GameLogicCardDistribution();
        GameLogicHandsofCards h = new GameLogicHandsofCards();
        
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
        ArrayList initial = g.initialCardsDistribution(cards, no_of_players);
        //Give - 3 cards to each player
        ArrayList normal = g.cardsDistrubution();
        
        ///
        System.out.println("initial card sets 2x2");
        for(int i=0;i<initial.size();i++){
            Cards a = (Cards)initial.get(i);
            System.out.print(a.getCnumber());
            System.out.println(a.getCtype());
        }
        ///
        
        ///
        System.out.println("Card sets 3x3");
        for(int i=0;i<normal.size();i++){
            Cards a = (Cards)normal.get(i);
            System.out.print(a.getCnumber());
            System.out.println(a.getCtype());
        }
        ///
        
        ///
        g.displaycard();
        ///
        
        ////////////////////////////////Hands///////////////////////////////
        //Original
        h.handsofCards(no_of_players, initial, normal);
        
        //initilizing DEMO 
        //demo.printCardHands(no_of_players, initial, normal);
        
//        System.out.println("\n--------player 1's cards set--------");
//        System.out.println("\nPlayer 1's initial two cards");
//        for(int i=0;i<h.player1.size();i++){
//            Cards a = (Cards)h.player1.get(i);
//            System.out.print(a.getCnumber());
//            System.out.println(a.getCtype());
//            if(i==1){
//                System.out.println("Player 1's normal cards");
//            }
//        }
//        System.out.println("\n--------player 2's cards set--------");
//        System.out.println("\nPlayer 2's initial two cards");
//        for(int i=0;i<h.player5.size();i++){
//            Cards a = (Cards)h.player5.get(i);
//            System.out.print(a.getCnumber());
//            System.out.println(a.getCtype());
//            if(i==1){
//                System.out.println("Player 2's normal cards");
//            }
//        }
    }
    
}
