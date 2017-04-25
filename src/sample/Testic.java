package sample;

import java.util.*;

public class Testic {
    public static void main(String[] args) {
        List<Integer> mass = new Stack<>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            mass.add(rand.nextInt(100));
        }


        for (Integer x : mass) {
            System.out.print(x + "\t");
        }
        System.out.println();

        Comparator w = Comparator.naturalOrder();
        mass.sort(w);

        for (Integer x : mass) {
            System.out.print(x + "\t");
        }
    }
}