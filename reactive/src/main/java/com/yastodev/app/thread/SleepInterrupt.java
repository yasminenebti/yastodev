package com.yastodev.app.thread;

public class SleepInterrupt {

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            System.out.println("worker: starting...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("worker: interrupted, stopping cleanly");
                return;
            }
            System.out.println("worker: finished naturally");
        });

        worker.start();
        System.out.println("main: waiting 500ms for worker...");
        worker.join(500); // wait at most 500ms

        if (worker.isAlive()) {
            System.out.println("main: too slow! sending interrupt...");
            worker.interrupt();
        }

        worker.join(); // wait for it to actually exit
        System.out.println("main: all done");
    }
}
