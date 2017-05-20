package sample;

import javafx.scene.control.ListView;

public class SortStack extends AbsSort {
    private Cell start;
    private int size = 0;

    public void takeIt(int index) {
        takeIt(start, index);
    }

    public void delete(int index) {
        if (start == null)
            return;
        if (start.value == index) {
            start = start.next;
            size--;
            return;
        }
        delete(start, index);
    }

    private Cell takeIt(Cell head, int index) {
        if (head.value == index) {
            return head;
        } else {
            if (head.next != null)
                takeIt(head.next, index);
        }
        System.out.print(head.value + " ");
        return null;
    }

    private Cell delete(Cell head, int index) {
        if (head.next != null) {
            if (head.next.value == index) {
                head.next = head.next.next;
                size--;
            } else {
                delete(head.next, index);
            }
        } else if (head.value == index) {
            start = null;
            size--;
        } else
            System.out.println("эл нет");

        return null;
    }

    public void add(Cell cell) {
        cell.next = start;
        start = cell;
        size++;
    }

    public void clear() {
        start = null;
        size = 0;
    }

    public int[] asArray() {
        int[] cells = new int[size];

        int iter = 0;
        Cell temp = start;
        while (temp != null) {
            cells[iter] = temp.value;
            temp = temp.next;
            iter++;
        }

        return cells;
    }

    public void cloneArray(int[] cells) {
        clear();
        for (int x : cells) {
            add(new Cell(x));
        }
    }

    @Override
    public void insert(int x) {
        add(new Cell(x));
    }

    @Override
    public void display(ListView list) {
        list.getItems().clear();

        Cell temp = start;
        while (temp != null) {
            Cell key = temp;
            temp = temp.next;

            list.getItems().add(key.value);
        }
    }

    @Override
    public void sort() {
        int[] cells = asArray();

        for (int i = 1; i < cells.length; i++) {
            int key = cells[i];
            int j = i - 1;
            while (j >= 0 && cells[j] < key) {
                cells[j + 1] = cells[j];
                j--;
            }
            cells[j + 1] = key;
        }

//        НЕРЕАЛИЗОВАННЫЙ ПЛАН ПО ЗАХВАТУ МИРА \"/

//        Cell temp = start;
//        while (temp != null) {
//            Cell key = temp;
//            temp = temp.next;
//
//            key.next = start;
//            Cell temper = start;
//            while (temper.next != key || temper.value > key.value) {
//                key.next = temper.next;
//                temper.next = key;
//                temper = temper.next;
//            }
//        }

        cloneArray(cells);
    }

    @Override
    public void rSort() {
        int[] cells = asArray();

        for (int i = 1; i < cells.length; i++) {
            int key = cells[i];
            int j = i - 1;
            while (j >= 0 && cells[j] > key) {
                cells[j + 1] = cells[j];
                j--;
            }
            cells[j + 1] = key;
        }

        cloneArray(cells);
    }
}
