/**
 * Name: Wang Hongyanyan
 * Matric. No: Ao239012R
 */

import java.util.*;
public class Friends {
    public static void main(String args[]) {
        Kattio io = new Kattio(System.in);
        int numShops = io.getInt();
        Long timeStay = io.getLong();
        ArrayList<String> shops = new ArrayList<String>();
        int maxMax = 0;
        for (int i=0; i<numShops;i++){
            String cafe = io.getWord();
            int visitors = io.getInt();
            ArrayList<Long> arrival = new ArrayList<Long>();
            ArrayList<Long> exit = new ArrayList<Long>();
            for (int j = 0; j<visitors; j++){
                arrival.add(io.getLong());
                exit.add(io.getLong());
            }
            Collections.sort(arrival);
            Collections.sort(exit);
            int counter = 1, curMax = 0, idx1 = 1, idx2 = 0;
            for (idx1 =1;idx1 < visitors;idx1++) {
                counter++;//每进入一个人就可能 add new one friend 能不能加上看离开时间
                while (arrival.get(idx1) > exit.get(idx2) + timeStay) { //not possible to meet next person before leaving of one guy assuming entering the shop exactly when this guy leaves
                    counter--;
                    idx2++;
                }
                curMax = Math.max(curMax, counter);
            }
            if (curMax>maxMax) {
                maxMax = curMax;
                shops.clear();
                shops.add(cafe);
            }
            else if (curMax == maxMax) {
                shops.add(cafe);
            }
        }
        System.out.println(maxMax);
        Collections.sort(shops);
        for (int i =0; i< shops.size();i++){
            System.out.println(shops.get(i));
        }
        io.close();
    }
}