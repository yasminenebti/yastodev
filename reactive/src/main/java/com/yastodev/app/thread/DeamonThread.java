package com.yastodev.app.thread;

public class DeamonThread {

    public static void main(String[] args) throws InterruptedException {

        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("daemon: tick...");
                try { Thread.sleep(300); } catch (InterruptedException e) { break; }
            }
        });
        daemon.setDaemon(true); // must be set BEFORE start()
        daemon.start();

        Thread nonDaemon = new Thread(() -> {
            try { Thread.sleep(6000); } catch (InterruptedException e) {}
            System.out.println("non-daemon: done — JVM will exit now");
        });
        nonDaemon.start();

        System.out.println("main: exiting (JVM stays alive for nonDaemon)");
        // main ends here — JVM waits for nonDaemon, then kills daemon abruptly
    }

}
