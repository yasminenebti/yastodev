package com.yastodev.app.thread.concurrency.volatiled;

class Server {

    // Without volatile, worker thread may NEVER see running=false
    // because it caches the value in its CPU register
    private volatile boolean running = true;

    // Also useful: volatile guarantees visibility of the counter
    // but NOT atomicity — still need AtomicInteger for increment
    private volatile int requestCount = 0;

    public void start() {
        Thread worker = new Thread(() -> {
            System.out.println("Server started");
            while (running) { // reads fresh value every iteration
                // simulate handling a request
                requestCount++; // visible but NOT atomic — ok here, only 1 writer
                System.out.println("Server request count: " + requestCount);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
            System.out.println("Server stopped. Handled " + requestCount + " requests");
        }, "worker");
        worker.start();
    }

    public void stop() {
        //print curretn thread here
        System.out.println("Current thread: " + Thread.currentThread().getName());
        System.out.println("Sending stop signal...");
        running = false; // immediately visible to worker thread
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.start();

        Thread.sleep(6000); // let it run for 6 second
        server.stop();
        Thread.sleep(6000);  // let worker notice and exit
    }
}