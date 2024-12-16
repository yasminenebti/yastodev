package com.yastodev.app.threadPriorities;

public class MyThreads {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.setName("Thread 1");
        t1.setPriority(Thread.MIN_PRIORITY);

        Thread1 t2 = new Thread1();
        t2.setName("Thread 2");
        t2.setPriority(Thread.NORM_PRIORITY);

        Thread1 t3 = new Thread1();
        t3.setName("Thread 3");
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
