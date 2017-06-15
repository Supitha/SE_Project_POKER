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
public class GameLogicCardDistribution {
    
    public ArrayList remaincards;
    int no_ofplayers;
    
    //Giving pairs of initialcards to all players
    ArrayList initialCardsDistribution(ArrayList a,int players){
        ArrayList initial = new ArrayList();
        //We have to give one pair of initial cards to each player, 
        //therefore total number of initial cards = number_of_players * 2
        int no_ofinitialcrds = players*2;
        //assigning all the shuffeled cards to remaincards-ArrayList
        remaincards = a;
        //assigning number of palyers to no_ofplayers
        no_ofplayers = players;
            for(int x=0;x<no_ofinitialcrds;x++){
               Cards crd = (Cards)a.get(51-x);
               //adding initial cards to initial-ArrayList
               initial.add(crd);
               //removing given cards
               remaincards.remove(51-x);
            }
        return initial;
    }
    
    //Giving 3 card for each player - Now total number of cards in a hand is complete
    ArrayList cardsDistrubution(){
        //same as above, 3 cards for each player
        int no_ofnormalcrds = no_ofplayers*3;
        ArrayList cardset = new ArrayList();
        for(int i=0;i<no_ofnormalcrds;i++){
            Cards crd = (Cards)remaincards.get((remaincards.size()-1)-i);
            //adding cards to cardset-ArrayList
            cardset.add(crd);
            //removing given cards
            remaincards.remove((remaincards.size()-1)-i);
        }
      return cardset;  
    }
    
    ///
    void displaycard(){
        System.out.println("Number of remaining cards "+remaincards.size());
        /*for(int i=0;i<card.size();i++){
            Cards a = (Cards)card.get(i);
            System.out.print(a.cnumber);
            System.out.println(a.ctype);
        }*/
    }
    ///
    
}
