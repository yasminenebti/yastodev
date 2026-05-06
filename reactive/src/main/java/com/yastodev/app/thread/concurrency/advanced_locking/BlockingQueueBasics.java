package com.yastodev.app.thread.concurrency.advanced_locking;

import java.util.concurrent.*;

public class BlockingQueueBasics {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3); // capacity 3

        // ── 4 ways to add an item ──────────────────────────────────────
        queue.put("A");            // BLOCKS if full — waits indefinitely
        queue.offer("B");          // returns false if full — no blocking
        queue.offer("C", 500,
                TimeUnit.MILLISECONDS);// waits up to 500ms, then returns false
        // queue.add("D");         // throws IllegalStateException if full

        System.out.println("Queue: " + queue); // [A, B, C]

        // ── 4 ways to remove an item ──────────────────────────────────
        String a = queue.take();   // BLOCKS if empty — waits indefinitely
        String b = queue.poll();   // returns null if empty — no blocking
        String c = queue.poll(500,
                TimeUnit.MILLISECONDS);// waits up to 500ms, then returns null
        // queue.remove();         // throws NoSuchElementException if empty

        System.out.println("Took: " + a + ", " + b + ", " + c); // A, B, C

        // ── peek without removing ─────────────────────────────────────
        queue.put("X");
        System.out.println("Peek: " + queue.peek()); // X — not removed
        System.out.println("Size: " + queue.size()); // still 1

        // ── drain all items at once ───────────────────────────────────
        queue.put("Y");
        queue.put("Z");
        java.util.List<String> batch = new java.util.ArrayList<>();
        int drained = queue.drainTo(batch);         // atomic drain — very useful for batching
        System.out.println("Drained " + drained + ": " + batch); // [X, Y, Z]
    }
}
