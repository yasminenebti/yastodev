package com.yastodev.app;

public class ThreadLifecycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread is running...");
            try {
                Thread.sleep(1000); // Blocked state
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread finished!");
        });

        System.out.println("Thread State: " + t1.getState()); // NEW
        t1.start();
        System.out.println("Thread State: " + t1.getState()); // RUNNABLE

        Thread.sleep(500); // Give time for the thread to run
        System.out.println("Thread State: " + t1.getState()); // TIMED_WAITING or RUNNABLE

        t1.join(); // Wait for the thread to finish
        System.out.println("Thread State: " + t1.getState()); // TERMINATED
    }
}
