package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierExample
{
    public static void main( String[] args )
    {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(2);

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("Executing threads");
                    cyclicBarrier.await();
                    System.out.println("Barrier threashold is reached");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        Thread thread=new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread thread2=new Thread(runnable);

        thread2.start();

    }
}
