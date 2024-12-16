package com.yastodev.app.daemonThread;

public class MyDaemon extends Thread{
    public void run()
    {

        if (Thread.currentThread().isDaemon()) {
            System.out.println("I am daemon thread and I am working");
        }

        else {
            System.out.println("I am user thread and I am working");
        }
    }
}
