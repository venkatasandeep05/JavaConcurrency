package org.example;

public class ThreadLocalExample
{
    static ThreadLocal<Integer> variable=new ThreadLocal<>();
    public static void main( String[] args ) {

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                variable.set(100);
                try {
                    testMethod();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                variable.set(26);
                testMethod2();
            }
        };

        Thread thread1=new Thread(runnable1);
        Thread thread2=new Thread(runnable2);

        thread1.start();
        thread2.start();
    }

    private static void testMethod2() {
        System.out.println("Printing thread local variable from a different thread"+variable.get());
    }

    private static void testMethod() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Printing thread local variable value"+variable.get());
    }
}
