package com.yastodev.app.thread.concurrency.advanced_locking;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class TryLockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    // ── tryLock() — non-blocking attempt ──────────────────────────────
    public void nonBlockingWork() {
        if (lock.tryLock()) {           // returns true immediately if free
            try {
                System.out.println(Thread.currentThread().getName()
                        + " acquired lock, doing work...");
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()
                        + " interrupted nonBlockingWork ...");
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName()
                        + " interrupted nonBlockingWork finally ...");
                lock.unlock();
            }
        } else {
            // lock was busy — we don't block, we do something else
            System.out.println(Thread.currentThread().getName()
                    + " lock busy, skipping or using cached data");
        }
    }

    // ── tryLock(timeout) — wait up to N ms, then give up ─────────────
    public void timedWork() {
        try {
            if (lock.tryLock(200, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName()
                            + " got lock within timeout");
                    Thread.sleep(100);
                } finally {
                    System.out.println(Thread.currentThread().getName()
                            + " interrupted timedWork finally...");

                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName()
                        + " TIMEOUT — gave up after 200ms");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()
                    + " interrupted timedWork ...");
            Thread.currentThread().interrupt();
        }
    }

    // ── lockInterruptibly() — can be cancelled via interrupt ──────────
    public void interruptibleWork() throws InterruptedException {
        lock.lockInterruptibly();       // throws InterruptedException if interrupted while waiting
        try {
            System.out.println(Thread.currentThread().getName() + " working...");
            Thread.sleep(1000);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLockDemo demo = new TryLockDemo();

        // Two threads: T1 holds lock 50ms, T2 tries with 200ms timeout
        Thread t1 = new Thread(demo::nonBlockingWork, "T1");
        Thread t2 = new Thread(demo::timedWork, "T2");

        t1.start();
        Thread.sleep(50); // T1 gets the lock first
        t2.start();

        Thread t3 = new Thread(demo::nonBlockingWork, "T3");
        Thread t4 = new Thread(() -> {
            try {
                demo.interruptibleWork();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }, "T4");

        t3.start();
        Thread.sleep(50); // T1 gets the lock first
        t4.start();

        t3.join(); t4.join();
    }
}