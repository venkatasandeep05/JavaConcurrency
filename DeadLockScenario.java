package org.example;

public class DeadLockScenario
{
    public static void main( String[] args )
    {
        Object obj1=new Object();
        Object obj2=new Object();

        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                synchronized (obj1){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (obj2)
                    {
                        System.out.println("Hello World");
                    }
                }
            }
        };

        Runnable runnable2=new Runnable() {
            @Override
            public void run() {
                synchronized (obj2)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (obj1)
                    {
                        System.out.println("Hello");
                    }
                }
            }
        };

        Thread thread1=new Thread(runnable1);
        Thread thread2=new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}
