package com.yastodev.app.thread.concurrency.synchronizedd;

class Inventory {
    private int stock = 100;
    private final Object lock = new Object();

    public void processOrder(String item, int qty) {
        String thread = Thread.currentThread().getName();

        System.out.println(thread + " validating order for " + item);
        try { Thread.sleep(50); } catch (InterruptedException e) {}

        // Only THIS critical section is locked
        synchronized (lock) {
            System.out.println(thread + " LOCKED | stock=" + stock);
            if (stock >= qty) {
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                stock -= qty;
                System.out.println(thread + " reserved " + qty
                        + " | remaining=" + stock);
            } else {
                System.out.println(thread + " REJECTED — insufficient stock");
            }
        }

        // This part also runs in parallel
        System.out.println(thread + " sending confirmation for " + item);


    }

    public static void main(String[] args) throws InterruptedException {
        Inventory inv = new Inventory();

        Thread t1 = new Thread(() -> inv.processOrder("Laptop", 60), "T1");
        Thread t2 = new Thread(() -> inv.processOrder("Monitor", 60), "T2");
        Thread t3 = new Thread(() -> inv.processOrder("Keyboard", 20), "T3");

        t1.start(); t2.start(); t3.start();
        t1.join();  t2.join();  t3.join();
    }
}