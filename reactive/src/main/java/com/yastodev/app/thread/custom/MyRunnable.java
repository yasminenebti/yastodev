package com.yastodev.app.thread.custom;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable is running.");
        for (int i = 0; i < 5; i++) {
            System.out.println("MyRunnable count: " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("MyRunnable interrupted.");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("MyRunnable finished.");
    }

    public static class ThreadExample2 {
        public static void main(String[] args) {
            MyRunnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable); // Pass the Runnable instance
            thread.start();
            System.out.println("Main thread continues...");
        }
    }

}
