package org.example;

public class SynchronizationExample
{
    public static void main( String[] args )
    {
       Counter counter=new Counter();

       new Thread(counter,"Thread one").start();
       new Thread(counter,"Thread two").start();
       new Thread(counter,"Thread three").start();
       new Thread(counter,"Thread four").start();
    }
}

public class Counter implements Runnable
{
    private int value=0;
    @Override
    public void run()
    {
        synchronized (this) {  // Remove the synchronized block to see how the output behave without synchronization
            this.increment();
            System.out.println(Thread.currentThread() + " increment operation: " + this.getValue());
            this.decrement();
            System.out.println(Thread.currentThread() + " decrement operation: " + this.getValue());
        }
    }

    private int getValue()
    {
        return value;
    }

    private void decrement()
    {
        value--;
    }

    private void increment()
    {
        value++;
    }
}

