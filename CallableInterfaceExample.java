package org.example;

import java.util.concurrent.*;

public class CallableInterfaceExample
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Hello Callable is getting executed");
                Thread.sleep(20000);
                return 120;
            }
        };

        ExecutorService executorService= Executors.newFixedThreadPool(1);

        Future<Integer> future=executorService.submit(callable);

        Integer x=future.get();
        System.out.println(x);
        System.out.println("Hello this is after callable execution");

        executorService.shutdown();
    }
}
