package com.yastodev.app.threadSafety;

public class BankSynchronization {
    private int balance = 0;
    public synchronized void deposit(int amount){
        balance += amount;
    }
    public synchronized void withdraw(int amount){
        balance -= amount;
    }
    public int getBalance(){
        return balance;
    }

    public static void main(String[] args) throws InterruptedException {
        BankSynchronization bankAccount = new BankSynchronization();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                bankAccount.deposit(1);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                bankAccount.deposit(1);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final balance: " + bankAccount.getBalance());


    }
}
