package sample;

import javafx.scene.control.ListView;


public abstract class AbsSort {
    public abstract void insert(int x);

    public abstract void delete(int x);

    public abstract void display(ListView list);

    public abstract void sort();

    public abstract void rSort();

    public abstract void clear();
}