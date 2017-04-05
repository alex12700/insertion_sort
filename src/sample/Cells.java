package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.util.Random;

public class Cells {

    @FXML
    private Rectangle rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10,
            rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10;

    @FXML
    private Text text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10,
            text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10;

    private double[]  value;
    private int numberValue;

    public int length() {
        return numberValue++;
    }

    private void pause(int dSec){
        try {
            Thread.sleep(dSec*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Cells(){
        value = new double[10];
        numberValue = 0;
    }

    public Cells(int length){
        value = new double[10];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            value[i] = rand.nextDouble()*100;
        }
        numberValue = length-1;
    }

    public void insert(double x){
        value[numberValue] = x;
        numberValue++;
    }

    public void display(Rectangle[] rect1, Text[] text1){
//        Rectangle[] rect1 = {rect1_1, rect1_2, rect1_3, rect1_4, rect1_5, rect1_6, rect1_7, rect1_8, rect1_9, rect1_10};
//        Rectangle[] rect2 = {rect2_1, rect2_2, rect2_3, rect2_4, rect2_5, rect2_6, rect2_7, rect2_8, rect2_9, rect2_10};
//        Text[] text1 = {text1_1, text1_2, text1_3, text1_4, text1_5, text1_6, text1_7, text1_8, text1_9, text1_10};
//        Text[] text2 = {text2_1, text2_2, text2_3, text2_4, text2_5, text2_6, text2_7, text2_8, text2_9, text2_10};

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < value.length; i++) {
                    //pause(2);
                    rect1[i].setFill(Color.rgb(255,0,0));
                    text1[i].setText(Double.toString(BigDecimal.valueOf(value[i]).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                    //pause(4);
                    rect1[i].setFill(Color.rgb(0,255,0));
                }
                pause(5);
                for (int i = 0; i < value.length; i++) {
                    rect1[i].setFill(Color.GRAY);
                }
            }
        }).start();

    }

    public void sorting(Rectangle[] rect1, Text[] text1,Rectangle[] rect2, Text[] text2){
        //сделать обработку повторяющихся запросов на поток(уничтожение предыдушего потока)
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < value.length; i++) {
                    //получаем нужное число для вставки
                    double key = value[i];
                    rect2[i].setVisible(true);
                    rect2[i].setFill(Color.rgb(0,0,255));
                    text2[i].setVisible(true);
                    text2[i].setText(Double.toString(BigDecimal.valueOf(key).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                    pause(5);
                    int j = i-1;
                    while (j >= 0 && value[j] > key){
                        //проверяем первое слева число
                        rect1[j].setFill(Color.YELLOW);
                        //попутно двигая ячейку со всттавкой
                        rect2[j].setVisible(true);
                        rect2[j].setFill(Color.rgb(0,0,255));
                        text2[j].setVisible(true);
                        text2[j].setText(Double.toString(BigDecimal.valueOf(key).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                        //стираем прежнее местоположение
                        rect2[j+1].setVisible(false);
                        text2[j+1].setVisible(false);
                        pause(5);
                        //успешно найдена ячека которую можно пройти
                        rect1[j].setFill(Color.rgb(0,255,0));
                        pause(5);
                        //сдвиг ячейки вправо
                        //ее прежнее место становится серым и значени 'X'
                        rect1[j].setFill(Color.GRAY);
                        rect1[j+1].setFill(Color.rgb(0,255,0));
                        text1[j+1].setText(Double.toString(BigDecimal.valueOf(value[j]).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                        text1[j].setText("X");
                        value[j+1]=value[j];
                        j--;
                        pause(5);
                        //закрашиваем перединутую ячейку в серый
                        rect1[j+2].setFill(Color.GRAY);
                    }
                    //проверка для конец строки обработка ошибки в части кода
                    try {
                        //все так же проверка ячейки с движением как описано выше
                        //но если j=0 то идет обработка ошибки
                        rect1[j].setFill(Color.YELLOW);
                        rect2[j].setVisible(true);
                        rect2[j].setFill(Color.rgb(0,0,255));
                        text2[j].setVisible(true);
                        text2[j].setText(Double.toString(BigDecimal.valueOf(key).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                        rect2[j+1].setVisible(false);
                        text2[j+1].setVisible(false);
                        pause(5);
                        rect1[j].setFill(Color.rgb(255,0,0));
                        pause(5);
                        rect1[j].setFill(Color.GRAY);
                        pause(5);
                        rect2[j].setVisible(false);
                        text2[j].setVisible(false);
                        rect2[j+1].setVisible(true);
                        rect2[j+1].setFill(Color.rgb(0,0,255));
                        text2[j+1].setVisible(true);
                        text2[j+1].setText(Double.toString(BigDecimal.valueOf(key).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                        pause(5);
                        rect2[j+1].setVisible(false);
                        text2[j+1].setVisible(false);
                        text1[j+1].setText(Double.toString(BigDecimal.valueOf(key).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                        rect1[j+1].setFill(Color.rgb(0,0,255));
                        pause(5);
                        rect1[j+1].setFill(Color.GRAY);
                    } catch (Exception e){
                        //то она просто встает в оставшееся место
                        rect2[j+1].setVisible(true);
                        rect2[j+1].setFill(Color.rgb(0,0,255));
                        text2[j+1].setVisible(true);
                        text2[j+1].setText(Double.toString(BigDecimal.valueOf(key).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                        pause(5);
                        rect2[j+1].setVisible(false);
                        text2[j+1].setVisible(false);
                        text1[j+1].setText(Double.toString(BigDecimal.valueOf(key).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                        rect1[j+1].setFill(Color.rgb(0,0,255));
                        pause(5);
                        rect1[j+1].setFill(Color.GRAY);
                    }
                    value[j+1]=key;
                }
            }
        }).start();
    }




























}
