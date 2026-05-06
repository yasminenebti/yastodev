package com.yastodev.app.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        //Only 3 thread names ever appear. Tasks 4–8 wait in a queue until a thread is free. This is exactly how a server handles requests.
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 8; i++) {
            final int id = i;
            pool.submit(() -> {
                System.out.println("Task " + id
                        + " -> " + Thread.currentThread().getName());
                try { Thread.sleep(6000); } catch (InterruptedException e) {}
                System.out.println("Task " + id + " done");
                System.out.println("--------");
            });
        }

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("All done");
    }
}
