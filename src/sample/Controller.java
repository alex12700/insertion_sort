package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Controller {
    private SortingIntegerVisual sortingInteger = new SortingIntegerVisual();
    private DynamicArrSort sortingD = new DynamicArrSort();
    private boolean sorted = false;
    private boolean minMax = false;

    @FXML
    private Rectangle rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10,
            rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10;

    @FXML
    private ListView<String> list;

    @FXML
    private Text text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10,
            text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10;

    @FXML
    private TextField writeField;

    @FXML
    private TextField deleteField;

    public void openFileExel() {
        sortingD = new DynamicArrSort();

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Открыть");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("exel files)", "*.xlsx", "*.xls");

        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = new Stage();

        File file = fileChooser.showOpenDialog(stage);

        try {
            FileInputStream fin = new FileInputStream(file);
            Workbook wb = new XSSFWorkbook(fin);

            int n = 2;
            int countStudent = 28;
            while (true) {
                int id = (int) wb.getSheetAt(0).getRow(n).getCell(0).getNumericCellValue();
                String name = wb.getSheetAt(0).getRow(n).getCell(1).getStringCellValue();
                float score = (float) wb.getSheetAt(0).getRow(n).getCell(8).getNumericCellValue();

                Cell cell = new Cell(id, name, score);

                if (n == countStudent + n || cell.getId() == 0) {
                    break;
                }

                sortingD.insert(cell);

                n++;
            }

            sortingD.display(list);

        } catch (Exception e) {
//            могут быть ошибки если не выбрать фалик
        }
    }

    public void sortExel() {
        sortingD.sort();
        sortingD.display(list);
    }

    public void sortExelById() {
        sortingD.sortById();
        sortingD.display(list);
    }

    public void randomSortingLine() {
        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};

        sortingInteger.randomMass(10);
        sortingInteger.display(rect1, text1);
        sorted = false;
    }

    public void sorting() {
        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
        Rectangle[] rect2 = {rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10};
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};
        Text[] text2 = {text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10};

        sortingInteger.sort(rect1, text1, rect2, text2);
        sorted = true;
        minMax = true;
    }

    public void insert() {
        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
        Rectangle[] rect2 = {rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10};
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};
        Text[] text2 = {text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10};

        int data;

        try {
            //не может считать\\проблема как то решена:?
            //было решено заменой подключаемого класса с java.awt.* на javafx.scene.text
            data = Integer.parseInt(writeField.getText());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("Вы ввели не число, попробуйте еще раз");
            alert.showAndWait();
            return;
        }

        if (sorted) {
            sortingInteger.insert(rect1, rect2, text1, text2, data, minMax);
        } else
            sortingInteger.insert(text1, data);
    }

    public void delete() {
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};

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

        sortingInteger.delete(text1, data);
    }

    public void reveseSorting() {
        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
        Rectangle[] rect2 = {rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10};
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};
        Text[] text2 = {text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10};

        sortingInteger.reverseSort(rect1, text1, rect2, text2);
        sorted = true;
        minMax = false;
    }

    public void openFile() {
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};

        sorted = false;

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Открыть");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = new Stage();

        File file = fileChooser.showOpenDialog(stage);

        try {
            Scanner scan = new Scanner(new FileReader(file));

            int num = 0;

            sortingInteger.clearMass();
            for (Text text : text1) {
                text.setText("X");
            }
            while (scan.hasNextInt() && num < 10) {
                sortingInteger.insert(text1, scan.nextInt());
                num++;
            }
            scan.close();
        } catch (Exception e) {
            //не найден или не выбран файлик
        }
    }
}






















