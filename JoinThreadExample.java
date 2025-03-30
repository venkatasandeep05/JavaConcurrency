package org.example;

public class JoinThreadExample
{
    public static void main( String[] args ) throws InterruptedException
    {
        Runnable runnable=()->{
            while (true)
            {}
        };
        Thread t=new Thread(runnable);
        t.start();
        Thread.currentThread().join();
    }
}
