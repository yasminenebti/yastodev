package com.yastodev.app.thread.concurrency.advanced_locking;

import java.util.concurrent.locks.ReentrantLock;

public class SafeCounter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();               // acquire — blocks if another thread holds it
        try {
            count++;               // critical section
        } finally {
            lock.unlock();         // ALWAYS unlock in finally — or you leak the lock!
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    // "reentrant" demo — same thread can lock again without deadlocking
    public void doubleIncrement() {
        lock.lock();               // lock count = 1
        try {
            increment();           // calls lock() again → count = 2, no deadlock
            increment();           // lock count still 2
        } finally {
            lock.unlock();         // count = 1, still held
        }
        // after returning: count = 0, fully released
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCounter counter = new SafeCounter();

        Thread t1 = new Thread(() -> { for (int i=0;i<100_000;i++) counter.increment(); });
        Thread t2 = new Thread(() -> { for (int i=0;i<100_000;i++) counter.increment(); });
        t1.start(); t2.start();
        t1.join();  t2.join();

        SafeCounter counter2 = new SafeCounter();


        Thread t3 = new Thread(() -> { for (int i=0;i<100_000;i++) counter2.doubleIncrement(); });
        Thread t4 = new Thread(() -> { for (int i=0;i<100_000;i++) counter2.doubleIncrement(); });
        t3.start(); t4.start();
        t3.join();  t4.join();

        System.out.println("Count: " + counter.getCount()); // always 200000
        System.out.println("Count 2: " + counter2.getCount()); // always 400000

    }
}