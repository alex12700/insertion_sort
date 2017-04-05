package sample;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.math.BigDecimal;

public class Controller {
    Cells cell;

    @FXML
    private Rectangle rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10,
            rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10;

    @FXML
    private Text text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10,
            text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10;

    private void pause(int decSec) {
        try {
            Thread.sleep(decSec * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void randomSortingLine() {
        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
        Rectangle[] rect2 = {rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10};
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};
        Text[] text2 = {text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10};
        cell = new Cells(10);
        cell.display(rect1,text1);
    }

    public void sorting() {
        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
        Rectangle[] rect2 = {rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10};
        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};
        Text[] text2 = {text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10};

        cell.sorting(rect1,text1,rect2,text2);
    }
}






















