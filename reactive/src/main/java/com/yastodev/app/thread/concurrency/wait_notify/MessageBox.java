package com.yastodev.app.thread.concurrency.wait_notify;

public class MessageBox {

    /*
    Without wait/notify
       Producer keeps checking while (hasMessage)
       Consumer keeps checking while (!hasMessage)
            👉 “busy waiting” (CPU spinning uselessly)
    wait() means:
       Release the lock and go to sleep until someone wakes me up
    notify() means:
       Wake up ONE thread that is waiting on this object's monitor

     object’s monitor :
       every object has an internal lock + waiting queue, called the monitor

     notifyAll() is safer:
        notify() wakes only ONE random waiting thread and JVM can wakes wrong thread
        consumer wakes up, !hasMessage , goes back to wait() but producer was NOT awakened :: livelock
        💥 Scenario: “stuck queue”
        Consumer A -> wait()
        Consumer B -> wait()
        producer notify();
        JVM wakes PRODUCER instead of CONSUMER
        Consumer is still sleeping , Producer went back to sleep , no progress

     synchorinized must be added :
     locks the specific object you are calling notify(), wait() on
     current thread can not be owner of object monitor


    */

    private volatile String message = null;
    private boolean hasMessage = false;

    public synchronized void produce(String message) throws InterruptedException {
        while (hasMessage) {
         System.out.println("*Waiting for consumer to consume message");
         wait();
        }
        this.message = message;
        this.hasMessage = true;
        System.out.println("*Producer put: " + message);
        notify();  //Hey consumer, I just produced something, you can wake up now
    }

    public synchronized String consume() throws InterruptedException {
        while(!hasMessage){
            System.out.println("+Waiting for producer to produce message");
            wait();
        }
        String message = this.message;
        this.hasMessage = false;
        System.out.println("+Consumer took: " + message);

        notify(); //Hey producer, I consumed it, you can produce again
        return message;

    }

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new MessageBox();
        String[] messages = {"Hello", "World", "Done"};

        Thread producer = new Thread( () -> {
            for(String m : messages) {
                try {
                    //Thread.sleep(9000);
                    messageBox.produce(m);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i< 3 ; i++) {
                try{
                    //Thread.sleep(3000);
                    messageBox.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Main thread finished!");
    }
}
