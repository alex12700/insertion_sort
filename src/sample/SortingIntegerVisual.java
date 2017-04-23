package sample;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class SortingIntegerVisual {

    private int[] value = new int[10];

    private int numberValue = 0;

    public void clearMass() {
        numberValue = 0;
    }

    public int length() {
        return numberValue;
    }

    private void pause(int dSec) {
        try {
            Thread.sleep(dSec * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //рандомный массив для показательной сортировки
    public void randomMass(int length) {
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            value[i] = rand.nextInt(100);
        }

        numberValue = length;
    }


    //вставка для отсорированного массива
    public void insert(Rectangle[] rect1, Rectangle[] rect2, Text[] text1, Text[] text2, int x, boolean minMax) {
        if (numberValue >= 10) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("некуда вставлять");
            alert.showAndWait();
            return;
        }
        value[numberValue] = x;
        numberValue++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (minMax)
                    visualInsert(rect1, rect2, text1, text2, x, (numberValue - 2));
                else
                    visualInsert_Revers(rect1, rect2, text1, text2, x, (numberValue - 2));
            }
        }).start();
    }


    //просто вставка в конец массива
    public void insert(Text[] text1, int x) {
        if (numberValue >= 10) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("некуда вставлять");
            alert.showAndWait();
            return;
        }
        value[numberValue] = x;
        numberValue++;
        text1[numberValue - 1].setText(String.valueOf(x));
    }


    //удаление эл. со сдвигом эл. влево
    public void delete(Text[] text1, int data) {
        boolean deleted = false;
        int numberDel = 0;

        for (int i = 0; i < numberValue; i++) {
            if (value[i] == data) {
                deleted = true;
                numberDel = i;
                break;
            }
        }

        if (deleted) {
            for (int i = numberDel; i < numberValue - 1; i++) {
                value[i] = value[i + 1];
                text1[i].setText(String.valueOf(value[i]));
            }
            value[numberValue - 1] = 0;
            text1[numberValue - 1].setText("X");
            numberValue--;
//            System.out.println("удалено");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText(null);
            alert.setContentText("нету такого элемента");
            alert.showAndWait();
        }
    }


    //перестановка элемента при сортировке
    private void visualInsert(Rectangle[] rect1, Rectangle[] rect2, Text[] text1, Text[] text2, int key, int j) {
        while (j >= 0 && value[j] > key) {
            //проверяем первое слева число
            //private checkThisCell !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
            //private checkThisCell !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            checkThisCell(rect1, rect2, text2, key, j);
            rect1[j].setFill(Color.rgb(255, 0, 0));
            pause(5);
            rect1[j].setFill(Color.GRAY);
            pause(5);


            rect2[j].setVisible(false);
            text2[j].setVisible(false);
            //момент в метод private void backToStay!!!!!!!!!!!!!!!!!!
            backToStay(rect1, rect2, text1, text2, key, j);
        } else {
            //то она просто встает в оставшееся место
            //момент в метод private void backToStay!!!!!!!!!!!!!!!!!!
            backToStay(rect1, rect2, text1, text2, key, j);
        }
        value[j + 1] = key;
    }


    //обратная перестановка элемента при сортировке
    private void visualInsert_Revers(Rectangle[] rect1, Rectangle[] rect2, Text[] text1, Text[] text2, int key, int j) {
        while (j >= 0 && value[j] < key) {
            //проверяем первое слева число
            //private checkThisCell !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
            //private checkThisCell !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            checkThisCell(rect1, rect2, text2, key, j);
            rect1[j].setFill(Color.rgb(255, 0, 0));
            pause(5);
            rect1[j].setFill(Color.GRAY);
            pause(5);


            rect2[j].setVisible(false);
            text2[j].setVisible(false);
            //момент в метод private void backToStay!!!!!!!!!!!!!!!!!!
            backToStay(rect1, rect2, text1, text2, key, j);
        } else {
            //то она просто встает в оставшееся место
            //момент в метод private void backToStay!!!!!!!!!!!!!!!!!!
            backToStay(rect1, rect2, text1, text2, key, j);
        }
        value[j + 1] = key;
    }


    //отображение массива
    public void display(Rectangle[] rect1, Text[] text1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                displayWithOutThread(rect1, text1);
            }
        }).start();
    }

    private void displayWithOutThread(Rectangle[] rect1, Text[] text1) {
        for (int i = 0; i < value.length; i++) {
            if (i >= numberValue) {
                text1[i].setText("X");
            } else {
                pause(2);
                rect1[i].setFill(Color.rgb(255, 0, 0));
                text1[i].setText(Integer.toString(value[i]));
                pause(4);
                rect1[i].setFill(Color.rgb(0, 255, 0));
            }
        }
        pause(5);
        for (int i = 0; i < value.length; i++) {
            rect1[i].setFill(Color.GRAY);
        }
    }


    //сортировка
    public void sort(Rectangle[] rect1, Text[] text1, Rectangle[] rect2, Text[] text2) {
        if (value[0] == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText("hello)");
            alert.setContentText("DOOOOOOOM");
            alert.showAndWait();
            return;
        }
        //сделать обработку повторяющихся запросов на поток(уничтожение предыдушего потока)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < numberValue; i++) {
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
                    visualInsert(rect1, rect2, text1, text2, key, j);
                }

                //просто красивая анимация для отсортированного массива
                visualReady(rect1);
            }
        }).start();
    }


    //обратная сортировка
    public void reverseSort(Rectangle[] rect1, Text[] text1, Rectangle[] rect2, Text[] text2) {
        if (value[0] == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ooooops noi is gone");
            alert.setHeaderText("hello)");
            alert.setContentText("DOOOOOOOM");
            alert.showAndWait();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < numberValue; i++) {
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
                    visualInsert_Revers(rect1, rect2, text1, text2, key, j);
                }

                //просто красивая анимация для отсортированного массива
                visualReady(rect1);
            }
        }).start();
    }

    //ненужный метод чтобы показать окончание сортировки
    private void visualReady(Rectangle[] rect1) {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 10; j++) {
                    rect1[j].setFill(Color.AQUAMARINE);
                    pause(1);
                }
            } else {
                for (int j = 0; j < 10; j++) {
                    rect1[j].setFill(Color.GRAY);
                    pause(1);
                }
            }
        }
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
