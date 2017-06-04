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
    ///
    ///
    ArrayList remaincards;
    int no_ofplayers;
    
    ArrayList initialCardsDistribution(ArrayList a,int players){
        ArrayList initial = new ArrayList();
        int no_ofinitialcrds = players*2;
        remaincards = a;
        no_ofplayers = players;
            for(int x=0;x<no_ofinitialcrds;x++){
               Cards crd = (Cards)a.get(51-x);
               initial.add(crd);
               remaincards.remove(51-x);
            }
        return initial;
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
    
    ArrayList cardsDistrubution(){
        //int no_ofhands = 
        int no_ofinitialcrds = no_ofplayers*3;
        ArrayList cardset = new ArrayList();
        for(int i=0;i<no_ofinitialcrds;i++){
            Cards crd = (Cards)remaincards.get((remaincards.size()-1)-i);
            cardset.add(crd);
            remaincards.remove((remaincards.size()-1)-i);
        }
      return cardset;  
    }
    
}
