package com.yastodev.app.RunnableMethod;

public class MyThreads {
    public static void main(String[] args) {

        // Creating reference of Runnable
        Runnable thread1 = new Thread1();

        // Creating reference of thread class
        // by passing object of Runnable in constructor of
        // Thread class
        Thread t1 = new Thread(thread1);

        t1.start();
    }
}
