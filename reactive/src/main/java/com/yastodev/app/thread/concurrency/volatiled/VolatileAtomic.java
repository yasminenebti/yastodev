package com.yastodev.app.thread.concurrency.volatiled;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileAtomic {

    private AtomicInteger count = new AtomicInteger(0);


    public void increment(){
        // not atomic (atomic, lock-free)
        count.incrementAndGet();
    }

    public static void main(String[] args)
            throws InterruptedException
    {
        VolatileAtomic demo = new VolatileAtomic();
        Thread A = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                demo.increment();
        });
        Thread B = new Thread(() -> {
            for (int i = 0; i < 1000; i++)
                demo.increment();
        });
        A.start();
        B.start();
        A.join();
        B.join();
        System.out.println("Final count (VolatileAtomic): " + demo.count);
    }
}
