package com.yastodev.app.thread.concurrency.wait_notify;

class WorkQueue {
    private final java.util.Queue<String> jobs = new java.util.LinkedList<>();
    private static final int MAX = 3;

    public synchronized void addJob(String job) throws InterruptedException {
        while (jobs.size() == MAX) {
            System.out.println("Queue full — producer waiting");
            wait();
        }
        jobs.add(job);
        System.out.println("Added: " + job + " | size=" + jobs.size());
        notifyAll(); // wake ALL waiting consumers (any one of them can take it)
    }

    public synchronized String takeJob(String workerName) throws InterruptedException {
        while (jobs.isEmpty()) {
            System.out.println(workerName + " waiting — queue empty");
            wait();
        }
        String job = jobs.poll();
        System.out.println(workerName + " took: " + job + " | size=" + jobs.size());
        notifyAll(); // wake producer in case it was waiting for space
        return job;
    }

    public static void main(String[] args) throws InterruptedException {
        WorkQueue queue = new WorkQueue();

        // 1 producer
        Thread producer = new Thread(() -> {
            String[] work = {"Job-A","Job-B","Job-C","Job-D","Job-E"};
            for (String j : work) {
                try { Thread.sleep(100); queue.addJob(j); }
                catch (InterruptedException ignored) {}
            }
        }, "Producer");

        // 3 consumers competing for jobs
        for (int i = 1; i <= 50; i++) {
            final String name = "Worker-" + i;
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    try { Thread.sleep(300); queue.takeJob(name); }
                    catch (InterruptedException e) {}
                }
            }, name).start();
        }

        Thread producer1 = new Thread(() -> {
            int i = 1;

            while (true) {
                String job = "Job-" + i++;

                try {
                    Thread.sleep(100); // production speed
                    queue.addJob(job);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "Producer1");

        Thread producer2 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                String job = "Job-" + i;

                try {
                    Thread.sleep(10); // fast production
                    queue.addJob(job);
                } catch (InterruptedException ignored) {}
            }
        }, "Producer2");


        producer.start();
    }
}

