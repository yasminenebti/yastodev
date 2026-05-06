package com.yastodev.app.thread;

public class SpawnThreads {

    public static void main(String[] args) throws InterruptedException {
        // the Thread-1..5 order is different each run. That's the OS scheduler being non-deterministic.
        System.out.println("main starts: " + Thread.currentThread().getName());

        for (int i = 1; i <= 5; i++) {
            final int id = i;
            Thread t = new Thread(() -> {
                System.out.println("Thread-" + id
                        + " on: " + Thread.currentThread().getName());
            });
            t.setName("worker-" + id);
            t.start();
        }

        Thread.sleep(200);
        System.out.println("main ends");
    }
}
