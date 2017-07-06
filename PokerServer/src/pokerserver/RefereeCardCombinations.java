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
public class RefereeCardCombinations {

    boolean royalFlush(ArrayList hand) {
        int tmpint = 0;
        ArrayList spade = new ArrayList();
        for (int x = 10; x <= 14; x++) {
            String tmp = Integer.toString(x);
            spade.add("s" + tmp);
        }
        ArrayList clubs = new ArrayList();
        for (int x = 10; x <= 14; x++) {
            String tmp = Integer.toString(x);
            clubs.add("c" + tmp);
        }
        ArrayList diamonds = new ArrayList();
        for (int x = 10; x <= 14; x++) {
            String tmp = Integer.toString(x);
            diamonds.add("d" + tmp);
        }
        ArrayList hearts = new ArrayList();
        for (int x = 10; x <= 14; x++) {
            String tmp = Integer.toString(x);
            hearts.add("h" + tmp);
        }
        for (int crdtyp = 0; crdtyp < 4; crdtyp++) {
            tmpint = 0;
            if (crdtyp == 0) {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) spade.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        }
                    }
                }
                if (tmpint == 5) {
                    return true;
                }
            } else if (crdtyp == 1) {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) clubs.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        }
                    }
                }
                if(tmpint==5){
                    return true;
                }
            } else if (crdtyp == 2) {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) diamonds.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        }
                    }
                }
                if(tmpint==5){
                    return true;
                }
            } else {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) hearts.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        } 
                    }
                }
                if(tmpint==5){
                    return true;
                }
            }

        }

        return false;
    }

    boolean straightFlush(ArrayList hand) {
        int tmpint = 0;
        ArrayList spade = new ArrayList();
        for (int x = 6; x <= 10; x++) {
            String tmp = Integer.toString(x);
            spade.add("s" + tmp);
        }
        ArrayList clubs = new ArrayList();
        for (int x = 6; x <= 10; x++) {
            String tmp = Integer.toString(x);
            clubs.add("c" + tmp);
        }
        ArrayList diamonds = new ArrayList();
        for (int x = 6; x <= 10; x++) {
            String tmp = Integer.toString(x);
            diamonds.add("d" + tmp);
        }
        ArrayList hearts = new ArrayList();
        for (int x = 6; x <= 10; x++) {
            String tmp = Integer.toString(x);
            hearts.add("h" + tmp);
        }

        for (int crdtyp = 0; crdtyp < 4; crdtyp++) {
            tmpint = 0;
            if (crdtyp == 0) {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) spade.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        }
                    }
                }
                if(tmpint==5){
                    return true;
                }
            } else if (crdtyp == 1) {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) clubs.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        }
                    }
                }
                if(tmpint==5){
                    return true;
                }
            } else if (crdtyp == 2) {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) diamonds.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        }
                    }
                }
                if(tmpint==5){
                    return true;
                }
            } else {
                for (int s = 0; s < hand.size(); s++) {
                    String spd = (String) hearts.get(s);
                    for (int i = 0; i < hand.size(); i++) {
                        String hnd = (String) hand.get(i);
                        if (spd.equals(hnd)) {
                            tmpint++;
                        } 
                    }
                }
                if(tmpint==5){
                    return true;
                }
            }

        }

        return false;
    }

    boolean fourOfaKind(ArrayList hand) {
        boolean bool = false;
        int s = 0, d = 0, h = 0, c = 0;
        for (int i = 0; i < hand.size(); i++) {
            String tmp = (String) hand.get(i);
            String type = tmp.substring(0, 1);
            if ("s".equals(type)) {
                s++;
            } else if ("d".equals(type)) {
                d++;
            } else if ("c".equals(type)) {
                c++;
            } else if ("h".equals(type)) {
                h++;
            }
        }
        if (s == 4) {
            return true;
        } else if (d == 4) {
            return true;
        } else if (h == 4) {
            return true;
        } else if (c == 4) {
            return true;
        }
        return bool;
    }

    
    /////remake->
    boolean fullHouse(ArrayList hand) {
        boolean bool = false;
        int s = 0, d = 0, h = 0, c = 0;
        for (int i = 0; i < hand.size(); i++) {
            String tmp = (String) hand.get(i);
            String type = tmp.substring(0, 1);
            if ("s".equals(type)) {
                s++;
            } else if ("d".equals(type)) {
                d++;
            } else if ("c".equals(type)) {
                c++;
            } else if ("h".equals(type)) {
                h++;
            }
        }
        if (h == 2 && c == 3) {
            return true;
        } else if (h == 2 && c == 3) {
            return true;
        } else if (h == 2 && s == 3) {
            return true;
        } else if (h == 2 && d == 3) {
            return true;
        } else if (c == 2 && s == 3) {
            return true;
        } else if (s == 2 && d == 3) {
            return true;
        } else if (h == 3 && c == 2) {
            return true;
        } else if (h == 3 && c == 2) {
            return true;
        } else if (h == 3 && s == 2) {
            return true;
        } else if (h == 3 && d == 2) {
            return true;
        } else if (c == 3 && s == 2) {
            return true;
        } else if (s == 3 && d == 2) {
            return true;
        }
        return bool;
    }

    boolean flush(ArrayList hand) {
        boolean bool = false;
        int num = 0;
        for (int i = 0; i < hand.size(); i++) {
            String tmp1 = (String) hand.get(i);
            String tmp2 = tmp1.substring(0, 1);
            for (int j = 0; j < hand.size(); j++) {
                String tmp3 = (String) hand.get(j);
                String tmp4 = tmp3.substring(0, 1);
                if (tmp2.equals(tmp4)) {
                    num++;
                }
            }
        }
        if (num == 25) {
            return true;
        }
        return bool;
    }

    boolean straight(ArrayList hand) {
        boolean bool = false;
        int num = 0;
        ArrayList strgt = new ArrayList();
        for (int x = 6; x <= 10; x++) {
            String tmp = Integer.toString(x);
            strgt.add(tmp);
        }
        for (int i = 0; i < hand.size(); i++) {
            String tmp1 = (String) hand.get(i);
            String tmp2 = tmp1.substring(1);
            for (int j = 0; j < hand.size(); j++) {
                String tmp3 = (String) strgt.get(j);
                if (tmp2.equals(tmp3)) {
                    num++;
                }
            }
        }
        System.out.println(num);
        if (num == 5) {
            return true;
        }
        return bool;
    }

    boolean threeOfaKind(ArrayList hand) {
        boolean bool = false;
        int s = 0, d = 0, h = 0, c = 0;
        for (int i = 0; i < hand.size(); i++) {
            String tmp = (String) hand.get(i);
            String type = tmp.substring(0, 1);
            if ("s".equals(type)) {
                s++;
            } else if ("d".equals(type)) {
                d++;
            } else if ("c".equals(type)) {
                c++;
            } else if ("h".equals(type)) {
                h++;
            }
        }
        if (s == 3) {
            return true;
        } else if (d == 3) {
            return true;
        } else if (h == 3) {
            return true;
        } else if (c == 3) {
            return true;
        }
        return bool;

    }

    boolean twoPairs(ArrayList hand) {
        boolean bool = false;
        int s = 0, d = 0, h = 0, c = 0;
        for (int i = 0; i < hand.size(); i++) {
            String tmp = (String) hand.get(i);
            String type = tmp.substring(0, 1);
            if ("s".equals(type)) {
                s++;
            } else if ("d".equals(type)) {
                d++;
            } else if ("c".equals(type)) {
                c++;
            } else if ("h".equals(type)) {
                h++;
            }
        }
        if (h == 2 && c == 2) {
            return true;
        } else if (h == 2 && c == 2) {
            return true;
        } else if (h == 2 && s == 2) {
            return true;
        } else if (h == 2 && d == 2) {
            return true;
        } else if (c == 2 && s == 2) {
            return true;
        } else if (s == 2 && d == 2) {
            return true;
        }
        return bool;
    }

    boolean onePair(ArrayList hand) {
        boolean bool = false;
        int s = 0, d = 0, h = 0, c = 0;
        for (int i = 0; i < hand.size(); i++) {
            String tmp = (String) hand.get(i);
            String type = tmp.substring(0, 1);
            if ("s".equals(type)) {
                s++;
            } else if ("d".equals(type)) {
                d++;
            } else if ("c".equals(type)) {
                c++;
            } else if ("h".equals(type)) {
                h++;
            }
        }
        if (s == 2) {
            return true;
        } else if (d == 2) {
            return true;
        } else if (h == 2) {
            return true;
        } else if (c == 2) {
            return true;
        }
        return bool;
    }

    int highCard(ArrayList hand) {
        int[] temp = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            String tmp1 = (String) hand.get(i);
            String tmp2 = tmp1.substring(1);
            temp[i] = Integer.parseInt(tmp2);
        }

        for (int j = 0; j < temp.length; j++) {
            for (int k = 0; k < temp.length; k++) {
                if (temp[j] > temp[k]) {
                    int tmp3 = temp[j];
                    temp[j] = temp[k];
                    temp[k] = tmp3;
                }
            }
        }
        return temp[0];
    }

    /////////////////////
    
    public static void main(String[] args) {
        RefereeCardCombinations ref = new RefereeCardCombinations();
        ArrayList demo = new ArrayList();
        demo.add("h10");
        demo.add("c10");
        demo.add("d10");
        demo.add("s6");
        demo.add("c8");
        int marks = ref.giveMarks(demo);
        System.out.println("Your marks : "+marks);
    }
    
    /////////////////////

    int giveMarks(ArrayList hand) {
        RefereeCardCombinations ref = new RefereeCardCombinations();
        int mark = 0;
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
                                        } else if (refeval == true) {
                                            mark = 20;
                                        }
                                    } else if (refeval == true) {
                                        mark = 25;
                                    }
                                } else if (refeval == true) {
                                    mark = 30;
                                }
                            } else if (refeval == true) {
                                mark = 35;
                            }
                        } else if (refeval == true) {
                            mark = 40;
                        }
                    } else if (refeval == true) {
                        mark = 45;
                    }
                } else if (refeval == true) {
                    mark = 50;
                }
            } else if (refeval == true) {
                mark = 55;
            }
        } else if (refeval == true) {
            mark = 60;
        }
        return mark;
    }

}
