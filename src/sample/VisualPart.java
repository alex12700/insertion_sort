package sample;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class VisualPart {
    private int[] value = new int[10];

    private void pause(int dSec) {
        try {
            Thread.sleep(dSec * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //рандомный массив для показательной сортировки
    private void randomMass() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            value[i] = rand.nextInt(100);
        }
    }

    //сортировка
    public void sort(Rectangle[] rect1, Text[] text1, Rectangle[] rect2, Text[] text2, Button btn) {
        randomMass();

        for (int i = 0; i < value.length; i++) {
            text1[i].setText(String.valueOf(value[i]));
            rect1[i].setFill(Color.GRAY);
        }

        new Thread(() -> {
            btn.setDisable(true);
            pause(10);
            for (int i = 1; i < value.length; i++) {
                //получаем нужное число для вставки
                int key = value[i];
                rect1[i].setFill(Color.rgb(0, 0, 255));
                pause(5);
                //вырываем это значение
                rect1[i].setFill(Color.GRAY);
                rect2[i].setVisible(true);
                rect2[i].setFill(Color.rgb(0, 0, 255));
                text2[i].setVisible(true);
                text2[i].setText(Integer.toString(key));
                pause(5);
                int j = i - 1;
                while (j >= 0 && value[j] > key) {
                    //проверяем первое слева число
                    checkThisCell(rect1, rect2, text2, key, j);

                    //успешно найдена ячека которую можно пройти
                    rect1[j].setFill(Color.rgb(0, 255, 0));
                    pause(5);
                    //сдвиг ячейки вправо
                    //ее прежнее место становится серым и значени 'X'
                    rect1[j].setFill(Color.GRAY);
                    rect1[j + 1].setFill(Color.rgb(0, 255, 0));
                    text1[j + 1].setText(Integer.toString(value[j]));
                    text1[j].setText("X");
                    value[j + 1] = value[j];
                    j--;
                    pause(5);
                    //закрашиваем перединутую ячейку в серый
                    rect1[j + 2].setFill(Color.GRAY);
                }
                //проверка для конец строки обработка ошибки в части кода
                if (j >= 0) {
                    //все так же проверка ячейки с движением как описано выше
                    //но если j<0 то идет обработка ошибки
                    checkThisCell(rect1, rect2, text2, key, j);
                    rect1[j].setFill(Color.rgb(255, 0, 0));
                    pause(5);
                    rect1[j].setFill(Color.GRAY);
                    pause(5);


                    rect2[j].setVisible(false);
                    text2[j].setVisible(false);
                    backToStay(rect1, rect2, text1, text2, key, j);
                } else {
                    //то она просто встает в оставшееся место
                    backToStay(rect1, rect2, text1, text2, key, j);
                }
                value[j + 1] = key;
            }
            btn.setDisable(false);
        }).start();
    }

    //2 блока для сортировки(визуальная часть)
    private void backToStay(Rectangle[] rect1, Rectangle[] rect2, Text[] text1, Text[] text2, int key, int j) {
        //возвращаемся на ячейку назад
        rect2[j + 1].setVisible(true);
        rect2[j + 1].setFill(Color.rgb(0, 0, 255));
        text2[j + 1].setVisible(true);
        text2[j + 1].setText(Integer.toString(key));
        pause(5);
        //стираем старое место положения
        rect2[j + 1].setVisible(false);
        text2[j + 1].setVisible(false);
        //вставляем в нужное место
        text1[j + 1].setText(Integer.toString(key));
        rect1[j + 1].setFill(Color.rgb(0, 0, 255));
        pause(5);
        rect1[j + 1].setFill(Color.GRAY);
    }

    private void checkThisCell(Rectangle[] rect1, Rectangle[] rect2, Text[] text2, int key, int j) {
        //ставим под подозрение ячейку и двигаемся под нее
        rect1[j].setFill(Color.YELLOW);
        rect2[j].setVisible(true);
        rect2[j].setFill(Color.rgb(0, 0, 255));
        text2[j].setVisible(true);
        text2[j].setText(Integer.toString(key));
        //стираем старое место положения
        rect2[j + 1].setVisible(false);
        text2[j + 1].setVisible(false);
        pause(5);
    }
}