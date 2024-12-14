package com.yastodev.app.functionalInterfaces;

import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

class Test {
    public static void main(String args[])
    {
        // create anonymous inner class object
        new Thread(new Runnable() {
            @Override public void run()
            {
                System.out.println("New thread created");
            }
        }).start();


    }
}
