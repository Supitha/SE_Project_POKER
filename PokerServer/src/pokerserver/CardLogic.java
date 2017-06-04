/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author supithaweerasinghe
 */
public class CardLogic {
    //creating an array list
    ArrayList cards = new ArrayList();
    
    void creatingCards(){
        
        for(int i=0;i<4;i++){
            //there are 4 types of cards spades,clubs,diamods,hearts
            //adding 14 cards belongs to spade
            if(i==0){
                String type = "spade";
                for(int x=2;x<=14;x++){
                    Cards a = new Cards();
                    a.cnumber = x;
                    a.ctype = type;
                    cards.add(a);
                }
            //adding 14 cards belongs to clubs
            }else if(i==1){
                String type = "clubs";
                for(int x=2;x<=14;x++){
                    Cards a = new Cards();
                    a.cnumber = x;
                    a.ctype = type;
                    cards.add(a);
                }
            //adding 14 cards belongs to diamonds
            }else if(i==2){
                String type = "diamonds";
                for(int x=2;x<=14;x++){
                    Cards a = new Cards();
                    a.cnumber = x;
                    a.ctype = type;
                    cards.add(a);
                }
            //adding 14 cards belongs to hearts
            }else{
                String type = "hearts";
                for(int x=2;x<=14;x++){
                    Cards a = new Cards();
                    a.cnumber = x;
                    a.ctype = type;
                    cards.add(a);
                }
            }
        }
        
        //printing all the cards
        /*for(int i=0;i<52;i++){
            Cards a = (Cards)cards.get(i);
            System.out.print(a.cnumber);
            System.out.println(a.ctype);
        }*/
        
    }
    
    ArrayList randomizingCards(){
        //randomizing arraylist - cards / shuffling
        long seed = System.nanoTime();
        Collections.shuffle(cards,new Random(seed));
        
        //printing all the randomized cards
        ////
        /*
        for(int i=0;i<52;i++){
            Cards a = (Cards)cards.get(i);
            System.out.print(a.cnumber);
            System.out.println(a.ctype);
        }
        */
        ////
        return cards;
    }
}
