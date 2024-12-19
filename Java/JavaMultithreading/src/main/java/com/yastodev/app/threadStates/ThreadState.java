package com.yastodev.app.threadStates;

public class ThreadState implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(102);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The state of t1 after it invoked join method() on thread t2 - " + MyThreadsState.t1.getState());

        try {
            Thread.sleep(202);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
