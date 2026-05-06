package com.yastodev.app.thread.exexutor_framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println("Running task in thread: " + Thread.currentThread().getName());
        };

        for (int i = 0; i < 5; i++) {
            executor.submit(task); // Submits task to thread pool
        }

        executor.shutdown(); // Initiates graceful shutdown
    }
}
