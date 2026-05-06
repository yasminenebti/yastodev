package com.yastodev.app.thread.exexutor_framework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable {

    //Unlike Runnable, which cannot return a result, Callable allows you to return a value and even throw checked exceptions.

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Task result";
        };

        Future<String> future = executor.submit(task);

        System.out.println("Waiting for result...");
        String result = future.get(); // Blocks until result is available
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}
