package com.yastodev.app.threadStates;

public class MyThreadsState implements Runnable {
    public static Thread t1;
    public static MyThreadsState obj;

    public static void main(String[] args) {
        obj = new MyThreadsState();
        t1 = new Thread(obj);
        System.out.println("State of t1 after creating : " + t1.getState());
        t1.start();
        System.out.println("State of t1 after calling start() method on it : " + t1.getState());
    }
    @Override
    public void run() {
        MyThreadsState myThreadsState = new MyThreadsState();
        Thread t2 = new Thread(myThreadsState);

        System.out.println("State of t2 after creating : " + t2.getState());

        t2.start();
        System.out.println("State of t2 after calling start() method on it : " + t2.getState());

        try {
            Thread.sleep(202);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("State of t2 after calling sleep() method on it : " + t2.getState());

        try {
            t2.join();
            System.out.println("State of t2 when it has finished its execution : " + t2.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("State of t1 when t2 is completed : " + t1.getState());

    }
}
