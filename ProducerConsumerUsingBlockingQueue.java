package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingBlockingQueue 
{
    public static void main( String[] args )
    {
        BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<>(10);

        Producer producer=new Producer(blockingQueue);
        Consumer consumer=new Consumer(blockingQueue);

        Thread producerThread=new Thread(producer);
        Thread consumerThread=new Thread(consumer);

        consumerThread.start();

        producerThread.start();
    }
}

public class Producer implements Runnable
{
    private BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            try {
                blockingQueue.put(i);
                System.out.println("Produced : "+i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


public class Consumer implements Runnable
{
    private BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            try {
                int val=blockingQueue.take();
                System.out.println("Consumed : "+val);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
