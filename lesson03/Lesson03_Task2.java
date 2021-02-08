import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Arrays;

public class Lesson03_Task2 {

    public static void main(String[] args) {

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();

        addApple(appleBox1, 5);
        addApple(appleBox2, 3);
        addOrange(orangeBox1, 2);
        System.out.println("appleBox1 & appleBox2 have the same weight: " + appleBox1.compare(appleBox2));
        System.out.println("appleBox2 & orangeBox1 have the same weight: " + appleBox2.compare(orangeBox1));

        System.out.println("appleBox1 now contains " + appleBox1.content.size() + " apples");
        System.out.println("appleBox2 now contains " + appleBox2.content.size() + " apples");
        System.out.println("Moving...");
        appleBox1.moveFruits(appleBox2, 3);
        System.out.println("appleBox1 now contains " + appleBox1.content.size() + " apples");
        System.out.println("appleBox2 now contains " + appleBox2.content.size() + " apples");
        System.out.println("Moving...");
        appleBox1.moveFruits(appleBox2);
        System.out.println("appleBox1 now contains " + appleBox1.content.size() + " apples");
        System.out.println("appleBox2 now contains " + appleBox2.content.size() + " apples");
        appleBox1.moveFruits(appleBox2, 2);

//        Ну и эти ребята закоментированны, потому что, как и полагается, выдают ошибку.
//        addApple(orangeBox1, 5);
//        appleBox2.moveSomeFruits(orangeBox1, 3);

    }

    protected static void addApple(Box<Apple> box, int quantity){
        Apple apple = new Apple();
        for (int i = 0; i < quantity; i++) {
            box.add(apple);
        }
    }

    protected static void addOrange(Box<Orange> box, int quantity){
        Orange orange = new Orange();
        for (int i = 0; i < quantity; i++) {
            box.add(orange);
        }
    }
}
