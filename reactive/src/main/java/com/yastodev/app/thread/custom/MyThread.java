package com.yastodev.app.thread.custom;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread is running.");
        for (int i = 0; i < 5; i++) {
            System.out.println("MyThread count: " + i);
            try {
                Thread.sleep(500); // Pause for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println("MyThread interrupted.");
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
        System.out.println("MyThread finished.");
    }

    public static class ThreadExample1 {
        public static void main(String[] args) {
            MyThread thread = new MyThread();
            thread.start(); // Starts the execution of the thread
            System.out.println("Main thread continues...");
        }
    }
}
