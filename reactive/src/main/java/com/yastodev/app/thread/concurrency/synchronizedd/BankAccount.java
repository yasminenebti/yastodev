package com.yastodev.app.thread.concurrency.synchronizedd;

class BankAccount {
    private double balance = 1000;

    public synchronized void withdraw(double amount) {
        String thread = Thread.currentThread().getName();
        System.out.println(thread + " wants to withdraw " + amount
                + " | balance=" + balance);

        if (balance >= amount) {
            // simulate processing delay — another thread might sneak in
            // if NOT synchronized
            try { Thread.sleep(10000); } catch (InterruptedException e) {}
            balance -= amount;
            System.out.println(thread + " withdrew " + amount + " | new balance=" + balance);
        } else {
            System.out.println(thread + " REFUSED — not enough funds");
        }
    }

    public synchronized double getBalance() { return balance; }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();

        // Two threads try to withdraw 700 at the same time
        Thread t1 = new Thread(() -> account.withdraw(700), "Alice");
        Thread t2 = new Thread(() -> account.withdraw(700), "Bob");

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Final balance: " + account.getBalance());
    }

}

