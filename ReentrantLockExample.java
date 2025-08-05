package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class ReentrantLockExample
{
    public static Lock lock=new ReentrantLock();
    public static void main( String[] args )
    {
        Thread thread=new Thread(ReentrantLockExample::FirstMethod);
        thread.start();
    }

    public static void FirstMethod()
    {
        try {
            lock.lock();
            System.out.println("First Method is executing");
            secondMethod();
            System.out.println("First Method execution resumed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    private static void secondMethod()
    {
        try {
            lock.lock();
            System.out.println("Second method is executing");
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
