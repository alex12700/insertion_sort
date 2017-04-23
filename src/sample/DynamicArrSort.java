package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DynamicArrSort extends AbsSort {
    private List<Cell> arrrr = new ArrayList<>();

    public void insert(Cell cell) {
        arrrr.add(cell);
    }

    public void display(ListView list) {
        list.getItems().clear();
        for (Cell x : arrrr) {
            String str = x.getId() + " : " + x.getName()
                    + "\f score: " + Math.round(x.getScore());
            list.getItems().add(str);
        }
    }

    public void sort() {
        if (arrrr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("список пуст");
            alert.showAndWait();
            return;
        }

        Cell[] cells = arrrr.toArray(new Cell[arrrr.size()]);

        for (int i = 1; i < cells.length; i++) {
            Cell key = cells[i];
            int j = i - 1;
            while (j >= 0 && cells[j].getScore() < key.getScore()) {
                cells[j + 1] = cells[j];
                j--;
            }
            cells[j + 1] = key;
        }

        arrrr = Arrays.asList(cells);
    }

    public void sortById() {
        if (arrrr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("список пуст");
            alert.showAndWait();
            return;
        }

        Cell[] cells = arrrr.toArray(new Cell[arrrr.size()]);

        for (int i = 1; i < cells.length; i++) {
            Cell key = cells[i];
            int j = i - 1;
            while (j >= 0 && cells[j].getId() > key.getId()) {
                cells[j + 1] = cells[j];
                j--;
            }
            cells[j + 1] = key;
        }

        arrrr = Arrays.asList(cells);
    }
}
