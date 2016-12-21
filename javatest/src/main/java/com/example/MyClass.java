package com.example;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {
        System.out.println("123");
        Persion persion = new  Persion();
        persion.name = "siqing";
        persion.age = 29;

        Persion persion1 = new Persion();
        persion1.name = "jipeng";
        persion1.age = 29;
        List<Persion> data = new ArrayList<>();
        data.add(persion);
        data.add(persion1);

        data.remove(persion1);

        System.out.println("345");

        int b = 1 << 30;
        System.out.println(b);

    }


    static class Persion{
        String name;
        int age;
    }
}
