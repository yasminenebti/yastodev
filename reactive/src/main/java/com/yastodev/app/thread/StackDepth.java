package com.yastodev.app.thread;

public class StackDepth {
    public static void main(String[] args) {

        // Walk the current call stack
        System.out.println("--- Call stack ---");
        for (StackTraceElement frame : Thread.currentThread().getStackTrace()) {
            System.out.println("  " + frame);
        }

        // How deep until StackOverflow?
        System.out.println("\n--- Depth test ---");
        try {
            recurse(0);
        } catch (StackOverflowError e) {
            System.out.println("Overflow! Default stack is ~512KB-1MB");
        }
    }

    static void recurse(int d) {
        if (d % 1000 == 0) System.out.println("depth: " + d);
        recurse(d + 1); // no base case — will overflow
    }
}
