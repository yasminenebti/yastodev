package com.yastodev.app.threadPriorities;

public class Thread1 extends Thread{
    public void run()
    {
        // Printing the current running thread
        System.out.println("Running Thread : " + currentThread().getName());

        // Print and display the priority of current thread
        System.out.println("Running Thread Priority : " + currentThread().getPriority() + " of Thread : " + currentThread().getName());
    }
}

