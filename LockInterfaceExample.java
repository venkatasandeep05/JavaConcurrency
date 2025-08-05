package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterfaceExample
{
    public static void main( String[] args )
    {
        BankAccount bankAccount=new BankAccount(100);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                bankAccount.withdraw(50);
            }
        };

        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}

public class BankAccount
{
    Lock lock=new ReentrantLock();
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount)
    {
        try {
            lock.lock();
            if (amount <= balance) {
                System.out.println("Intiating withdraw activity");
                balance = balance - amount;
                System.out.println("Amount withdraw completed. remaining balance is " + balance);
            } else {
                System.out.println("Insufficient Balance");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
