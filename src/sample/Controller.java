package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Controller {
    private VisualPart sortingInteger = new VisualPart();
    private SortStack sortStack = new SortStack();
    private SortIntTen sortIntTen = new SortIntTen();

    @FXML
    private Rectangle rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10,
            rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10;

    @FXML
    private Text text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10,
            text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10;

    @FXML
    private Button btn;

    @FXML
    private ListView<String> listD;

    @FXML
    private ListView<String> list;

    @FXML
    private TextField writeFieldD;

    @FXML
    private TextField writeField;

    @FXML
    private TextField deleteFieldD;

    @FXML
    private TextField deleteField;

    public void visualSort() {
        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
        Rectangle[] rect2 = {rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10};
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};
        Text[] text2 = {text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10};

        sortingInteger.sort(rect1, text1, rect2, text2, btn);
    }

    public void insertD() {
        int data;
        try {
            data = Integer.parseInt(writeFieldD.getText());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("Вы ввели не число, попробуйте еще раз");
            alert.showAndWait();
            return;
        }
        sortStack.insert(data);
        sortStack.display(listD);
    }

    public void insert() {
        int data;
        try {
            data = Integer.parseInt(writeField.getText());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("Вы ввели не число, попробуйте еще раз");
            alert.showAndWait();
            return;
        }
        sortIntTen.insert(data);
        sortIntTen.display(list);
    }

    public void deleteD() {
        int data;
        try {
            data = Integer.parseInt(deleteFieldD.getText());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("Вы ввели не число, попробуйте еще раз");
            alert.showAndWait();
            return;
        }
        sortStack.delete(data);
        sortStack.display(listD);
    }

    public void delete() {
        int data;
        try {
            data = Integer.parseInt(deleteField.getText());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("Вы ввели не число, попробуйте еще раз");
            alert.showAndWait();
            return;
        }
        sortIntTen.delete(data);
        sortIntTen.display(list);
    }

    public void sortD() {
        sortStack.sort();
        sortStack.display(listD);
    }

    public void sort() {
        sortIntTen.sort();
        sortIntTen.display(list);
    }

    public void sortRD() {
        sortStack.rSort();
        sortStack.display(listD);
    }

    public void sortR() {
        sortIntTen.rSort();
        sortIntTen.display(list);
    }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Открыть");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = new Stage();

        File file = fileChooser.showOpenDialog(stage);

        try {
            Scanner scan = new Scanner(new FileReader(file));

            int num = 0;

            sortIntTen.clear();

            while (scan.hasNextInt() && num < 10) {
                sortIntTen.insert(scan.nextInt());
                num++;
            }
            scan.close();
        } catch (Exception e) {
            //не найден или не выбран файлик
        }
        sortIntTen.display(list);
    }

    public void openFileD(){
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Открыть");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = new Stage();

        File file = fileChooser.showOpenDialog(stage);

        try {
            Scanner scan = new Scanner(new FileReader(file));

            int num = 0;

            sortStack.clear();

            while (scan.hasNextInt() && num < 10) {
                sortStack.insert(scan.nextInt());
                num++;
            }
            scan.close();
        } catch (Exception e) {
            //не найден или не выбран файлик
        }
        sortStack.display(listD);
    }
}