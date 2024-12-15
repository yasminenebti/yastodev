package com.yastodev.app.threadMethod;

public class MyThreads {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        //start() method starts the thread and lookup for their run()
        //thread transcend from runnable to run state by the scheduler, and output on the console is justified.
        t1.start();

        //thread is only created but not put to run state by the scheduler as its corresponding run() method was missing
        //only set to runnable state blocked by the scheduler
        t2.start();

        // When t2.show() is called, it behaves like a normal method
        // and executes on the main thread instead of a separate thread.
        t2.show();

    }
}
