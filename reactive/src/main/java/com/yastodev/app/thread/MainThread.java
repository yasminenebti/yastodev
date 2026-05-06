package com.yastodev.app.thread;

import java.util.Arrays;

public class MainThread {

    public static void main(String[] args) {
        //Even before you create any threads, the JVM already has several daemon threads running — GC, Finalizer, and others.

        Thread t = Thread.currentThread();

        System.out.println("Name     : " + t.getName());
        System.out.println("ID       : " + t.getId());
        System.out.println("Priority : " + t.getPriority());
        System.out.println("State    : " + t.getState());
        System.out.println("Daemon?  : " + t.isDaemon());

        // All threads alive in the JVM right now
        System.out.println("\n--- All live threads ---");

        Thread.getAllStackTraces().keySet()
                .forEach(th -> System.out.println(
                        th.getName() + " | daemon=" + th.isDaemon()
                ));

        /*
        * Common-Cleaner | daemon=true
        Notification Thread | daemon=true
        Signal Dispatcher | daemon=true
        Reference Handler | daemon=true
        main | daemon=false
        Attach Listener | daemon=true
        Finalizer | daemon=true
        */

    }
}
