package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuterServiceExample implements Runnable
{
    static int count=0;

    @Override
    public void run()
    {
        this.printHelloWorld();
    }

    private void printHelloWorld()
    {
        synchronized (this)
        {
            count++;
            System.out.println("Hello World");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecuterServiceExample app=new ExecuterServiceExample();
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        for(int i=1;i<=10;i++)
        {
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
          executorService.execute(app);
        }

        executorService.shutdown();

        while(true) {
            if(executorService.isTerminated()) {
                System.out.println("Count of Hello worlds which will get printed to console : "+count);
                break;
            }
        }
    }
}
