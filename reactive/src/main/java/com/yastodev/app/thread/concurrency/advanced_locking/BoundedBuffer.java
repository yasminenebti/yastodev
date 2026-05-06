package com.yastodev.app.thread.concurrency.advanced_locking;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<T> {
    private final Object[] items;
    private int head, tail, count;

    private final ReentrantLock lock = new ReentrantLock();
    // TWO separate conditions on the same lock — this is the key advantage
    private final Condition notFull  = lock.newCondition(); // producers wait here
    private final Condition notEmpty = lock.newCondition(); // consumers wait here

    public BoundedBuffer(int capacity) {
        items = new Object[capacity];
    }

    // Producer: put item — waits if buffer is full
    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                System.out.println(Thread.currentThread().getName()
                        + " WAITING — buffer full (" + count + "/" + items.length + ")");
                notFull.await();           // releases lock, waits for notFull signal
            }
            items[tail] = item;
            tail = (tail + 1) % items.length;
            count++;
            System.out.println(Thread.currentThread().getName()
                    + " PUT: " + item + " | size=" + count);
            notEmpty.signal();             // wake ONE waiting consumer
        } finally {
            lock.unlock();
        }
    }

    // Consumer: take item — waits if buffer is empty
    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println(Thread.currentThread().getName()
                        + " WAITING — buffer empty");
                notEmpty.await();          // releases lock, waits for notEmpty signal
            }
            T item = (T) items[head];
            items[head] = null;
            head = (head + 1) % items.length;
            count--;
            System.out.println(Thread.currentThread().getName()
                    + " TOOK: " + item + " | size=" + count);
            notFull.signal();              // wake ONE waiting producer
            return item;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(3); // capacity 3

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 6; i++) {
                try { Thread.sleep(100); buffer.put(i); }
                catch (InterruptedException e) {}
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try { Thread.sleep(300); buffer.take(); }
                catch (InterruptedException e) {}
            }
        }, "Consumer");

        producer.start(); consumer.start();
    }
}