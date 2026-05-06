package com.yastodev.app.thread.concurrency.advanced_locking;

import java.util.concurrent.locks.*;
import java.util.HashMap;
import java.util.Map;

public class CachedConfig {
    private final Map<String, String> config = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock  = rwLock.readLock();   // shared — many threads
    private final Lock writeLock = rwLock.writeLock();  // exclusive — one thread

    // READ — multiple threads can read simultaneously
    public String get(String key) {
        readLock.lock();                   // many threads can hold this at once
        try {
            String val = config.get(key);
            System.out.println(Thread.currentThread().getName()
                    + " READ  " + key + " = " + val);
            return val;
        } finally {
            readLock.unlock();
        }
    }

    // WRITE — blocks until ALL readers and writers release
    public void set(String key, String value) {
        writeLock.lock();                  // exclusive — all readers must finish first
        try {
            System.out.println(Thread.currentThread().getName()
                    + " WRITE " + key + " = " + value);
            config.put(key, value);
            Thread.sleep(200);             // simulate slow write
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CachedConfig cfg = new CachedConfig();
        cfg.set("db.url", "localhost:5432");
        cfg.set("db.pool", "10");

        // 5 reader threads + 1 writer thread
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    cfg.get("db.url");
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                }
            }, "Reader-" + id).start();
        }

        Thread writer = new Thread(() -> {
            try { Thread.sleep(150); }
            catch (InterruptedException e) {}
            cfg.set("db.url", "prod-server:5432"); // all readers pause for this
        }, "Writer");
        writer.start();
    }
}