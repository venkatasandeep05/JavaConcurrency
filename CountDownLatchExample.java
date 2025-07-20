package org.example;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchExample
{
    public static void main( String[] args )
    {
        CountDownLatch count=new CountDownLatch(3);
        try
        {
            for(int i=0;i<3;i++)
                new Thread(new Worker(count)).start();
            System.out.println("Main Thread");
            count.await();
            System.out.println("Locks are released by latch");

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

public class Worker implements Runnable
{
    CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(5000);
            System.out.println("Worker thread is completed . decrementing the counter");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            System.out.println("Thread is interrupted");
        }
        finally {
            latch.countDown();
        }
    }
}

