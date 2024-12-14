package com.yastodev.app.functionalInterfaces.consumer;

import java.util.function.Consumer;

public class Sample {

    public static void main(String args[])
    {
        //Consumer<String> consumer = (s) -> System.out.println(s);
        Consumer<String> consumer = System.out::println;

        consumer.accept("Hello World");
    }
}
