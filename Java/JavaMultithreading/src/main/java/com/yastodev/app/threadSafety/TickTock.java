package com.yastodev.app.threadSafety;

import java.util.concurrent.atomic.AtomicInteger;

public class TickTock {
//    We have two threads main thread and the thread t1. We are trying to access the count variable from both the threads.


   int count;

//    he operation count++ is not atomic( not indivisible and cannot be interrupted )
//    it involves the following steps at the CPU level: read increment write
//
//    When synchronized a thread enters the increment() method, it acquires a lock on the TickTock object (tt).
//    Other threads trying to call increment() must wait until the first thread releases the lock.

    public synchronized void increment(){
        count++;
    }

    // alternative approach to synchronize the increment method way performant
    private final AtomicInteger atomicCount = new AtomicInteger();

    public void incrementAtomic() {
        atomicCount.incrementAndGet();
    }


    public static void main(String[] args) throws InterruptedException {
        TickTock tt = new TickTock();
        long startSync = System.nanoTime();

        Thread syncT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    tt.increment();
                }
            }
        });

        Thread syncT2 = new Thread(new Runnable() {
            // Method
            // To begin the execution of thread
            public void run()
            {

                // Expression
                for (int i = 0; i < 10000; i++) {

                    // Calling object of above class
                    // in main() method
                    tt.increment();
                }
            }
        });

        //    The syncT1 thread is started using syncT1.start(). it doesn't immediately execute. The timing of when the thread is executed depends on the thread scheduler.
        //    The main thread  It prints the value of tt.count.  syncT1 has likely not yet had a chance to increment the count variable.
        syncT1.start();
        syncT2.start();
        System.out.println("Count: " + tt.count);

        syncT1.join(); // Wait for syncT1 to finish
        syncT2.join(); // Wait for syncT2 to finish

        long endSync = System.nanoTime();

        System.out.println("Count: " + tt.count);
        System.out.println("Synchronized Execution Time: " + (endSync - startSync) / 1_000_000.0 + " ms");


        // Reset for AtomicInteger test
        TickTock ttAtomic = new TickTock();
        long startAtomic = System.nanoTime();
        Thread t1Atomic = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                ttAtomic.incrementAtomic();
            }
        });

        Thread t2Atomic = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                ttAtomic.incrementAtomic();
            }
        });

        t1Atomic.start();
        t2Atomic.start();

        t1Atomic.join();
        t2Atomic.join();
        long endAtomic = System.nanoTime();
        System.out.println("Final Atomic Count: " + ttAtomic.atomicCount.get());
        System.out.println("Atomic Execution Time: " + (endAtomic - startAtomic) / 1_000_000.0 + " ms");

    }
}
