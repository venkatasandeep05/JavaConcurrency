package org.example;

public class ProducerConsumerProblem
{
    public static void main( String[] args ) {
        Buffer buffer=new Buffer(10);
        Producer producer=new Producer(buffer);
        Consumer consumer=new Consumer(buffer);

        Thread producerThread=new Thread(producer);
        Thread consumerThread=new Thread(consumer);

       


        producerThread.start();
        consumerThread.start();
        thread.start();
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

public class Buffer
{
    public int size;
    public int[] storageBuffer;
    public int count;


    public Buffer(int size) {
        this.size = size;
        this.storageBuffer = new int[size];
        this.count = 0;
    }

    public synchronized void produce(int value) {
        try {
            while (count == size) {
                wait();
            }
            storageBuffer[count] = value;
            count++;
            System.out.println("Produced: " + value);
            notifyAll();
        }
        catch (InterruptedException e) {
            System.out.println("Producer Thread is interrupted");
            e.printStackTrace();
        }
    }

    public synchronized void consume()
    {
        try
        {
            while (count==0)
            {
                wait();
            }
            System.out.println("consumed: "+storageBuffer[--count]);
            notifyAll();
        }
        catch (InterruptedException e)
        {
            System.out.println("Consumer Thread is Interrupted");
        }
    }
}




public class Producer implements Runnable
{
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++)
        {
            try {
                buffer.produce(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}



public class Consumer implements Runnable
{
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            try {
                buffer.consume();
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
