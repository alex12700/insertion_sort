package sample;

import javafx.scene.control.ListView;

public class SortIntTen extends AbsSort {
    private int[] qwant = new int[10];
    private int length = 0;

    @Override
    public void insert(int x) {
        if (length == 10) return;
        qwant[length] = x;
        length++;
    }

    @Override
    public void delete(int x) {
//        Integer x = temp;
        for (int i = 0; i < length; i++) {
            if (qwant[i] == x) {
                for (int j = i; j < length; j++) {
                    qwant[j] = qwant[j == 9 ? j : j + 1];
                }
                qwant[length - 1] = 0;
                length--;
                break;
            }
        }
    }

    @Override
    public void display(ListView list) {
        list.getItems().clear();
        for (int x : qwant) {
            if (x == 0) break;
            list.getItems().add(x);
        }
    }

    @Override
    public void sort() {
        for (int i = 1; i < length; i++) {
            int key = qwant[i];
            int j = i - 1;
            while (j >= 0 && qwant[j] < key) {
                qwant[j + 1] = qwant[j];
                j--;
            }
            qwant[j + 1] = key;
        }
    }

    @Override
    public void rSort() {
        for (int i = 1; i < length; i++) {
            int key = qwant[i];
            int j = i - 1;
            while (j >= 0 && qwant[j] > key) {
                qwant[j + 1] = qwant[j];
                j--;
            }
            qwant[j + 1] = key;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            qwant[i] = 0;
        }
        length = 0;
    }
}