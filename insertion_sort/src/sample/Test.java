package sample;

import java.util.*;

/**
 * Created by Алексаелп on 14.05.2017.
 */
public abstract class Test {

    private int qweeeee = 5;

    void qwe(int qwe){
        qwe++;
        System.out.println(qwe);
    }
}
class A extends Test{
    void qwe(int qwe){
        super.qwe(5);
        qwe++;
        System.out.println("qweqeqweqwe");
    }

    public static void main(String[] args) {
        A a = new A();

        a.qwe(5);
    }
}

class B extends A{
    void qwe(int qwe){
        super.super.qwe(5);
    }
}