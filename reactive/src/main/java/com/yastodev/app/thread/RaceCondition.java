package com.yastodev.app.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        //counter[0]++ looks like one step but is actually 3:
        // READ value, INCREMENT it, WRITE back.
        // Two threads can both READ the same value before either writes back — one increment gets lost.

        int[] counter = {0};
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) counter[0]++;
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) counter[0]++;
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Broken : " + counter[0]); // rarely 200000

        System.out.println("============");

        // FIXED — atomic operation
        AtomicInteger safe = new AtomicInteger(0);
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) safe.incrementAndGet();
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) safe.incrementAndGet();
        });
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("Fixed  : " + safe.get()); // always 200000


    }
}
