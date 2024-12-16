package com.yastodev.app.daemonThread;

public class MyThreads {
    public static void main(String[] args) {
        MyDaemon t1 = new MyDaemon();
        MyDaemon t2 = new MyDaemon();
        MyDaemon t3 = new MyDaemon();
        t2.setDaemon(true);
        t1.start();
        t2.start();
        t3.start();


    }
}
