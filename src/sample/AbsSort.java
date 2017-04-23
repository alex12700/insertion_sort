package sample;

import javafx.scene.control.ListView;


public abstract class AbsSort {
    public abstract void insert(Cell cell);

    public abstract void display(ListView list);

    public abstract void sort();

    public abstract void sortById();
}
