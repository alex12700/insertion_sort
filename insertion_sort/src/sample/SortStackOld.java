package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;


public class SortStackOld extends AbsSort {
    private List<Integer> quer = new ArrayList<>();

    @Override
    public void insert(int x) {
        quer.add(x);
    }

    @Override
    public void delete(int temp) {
        Integer x = temp;
        if (quer.contains(x))
            quer.remove(x);
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("Not found");
            alert.showAndWait();
        }
    }

    @Override
    public void display(ListView list) {
        list.getItems().clear();
        for (int x : quer) {
            list.getItems().add(x);
        }
    }

    @Override
    public void sort() {
        for (int i = 1; i < quer.size(); i++) {
            int key = quer.get(i);
            int j = i - 1;
            while (j >= 0 && quer.get(j) < key) {
                quer.set(j + 1, quer.get(j));
                j--;
            }
            quer.set(j + 1, key);
        }
    }

    @Override
    public void rSort() {
        for (int i = 1; i < quer.size(); i++) {
            int key = quer.get(i);
            int j = i - 1;
            while (j >= 0 && quer.get(j) > key) {
                quer.set(j + 1, quer.get(j));
                j--;
            }
            quer.set(j + 1, key);
        }
    }

    @Override
    public void clear() {
        quer.clear();
    }
}