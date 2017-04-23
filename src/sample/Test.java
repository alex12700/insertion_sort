package sample;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        int[] cells = new int[10];

        for (int i = 0; i < cells.length; i++) {
            Random rand = new Random();
            cells[i] = rand.nextInt(100);
        }

        for (int cell1 : cells) {
            System.out.print(cell1 + "   ");
        }
        System.out.println();

//        for (int i = 1; i < cells.length; i++) {
//            int key = cells[i];
//            int j = i-1;
//            while (j >= 0 && cells[j] > key){
//                cells[j+1]=cells[j];
//                j--;
//            }
//            cells[j+1]=key;
//        }

        for (int i = 1; i < cells.length; i++) {
            int key = cells[i];
            int j = i - 1;
            while (j >= 0 && cells[j] < key) {
                cells[j + 1] = cells[j];
                j--;
            }
            cells[j + 1] = key;
        }

        for (int cell : cells) {
            System.out.print(cell + "   ");
        }
    }
}
