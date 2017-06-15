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
public class DemoImplimentation {

    GameLogicHandsofCards h = new GameLogicHandsofCards();

    void printCardHands(int players,ArrayList initial,ArrayList normal) {
        h.handsofCards(players, initial, normal);
        ArrayList temp = new ArrayList();
        for (int x = 0; x < players; x++) {
            if(x==0){
                temp = h.player1;
            }else if (x==1){
                temp = h.player2;
            }else if (x==2){
                temp = h.player3;
            }else if (x==3){
                temp = h.player4;
            }else if (x==4){
                temp = h.player5;
            }else if (x==5){
                temp = h.player6;
            }else System.out.println("Invalid number of players");
            System.out.println("\n--------player "+(x+1)+"'s cards set--------");
            System.out.println("\nPlayer "+(x+1)+"'s initial two cards");
            for (int i = 0; i < temp.size(); i++) {
                Cards a = (Cards) temp.get(i);
                System.out.print(a.getCnumber());
                System.out.println(a.getCtype());
                if (i == 1) {
                    System.out.println("Player "+(x+1)+"'s normal cards");
                }
            }
        }
    }
}
