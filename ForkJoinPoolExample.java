package org.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample 
{
    public static void main( String[] args )
    {
        int arr[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        ForkJoinPool forkJoinPool=ForkJoinPool.commonPool();
        SumTask sumTask=new SumTask(arr,0,arr.length-1);
        int result=forkJoinPool.invoke(sumTask);

        System.out.println(result);
    }
}


public class SumTask extends RecursiveTask<Integer>
{
    private final int array[];
    private final int start;
    private final int end;

    public SumTask(int[] array, int start, int end)
    {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute()
    {
        if(start==end)
            return array[start];

            int mid=(start+end)/2;
            SumTask sumTask1=new SumTask(array,start,mid);
            SumTask sumTask2=new SumTask(array,mid+1,end);

            sumTask1.fork();
            sumTask2.fork();

            return sumTask1.join()+sumTask2.join();

    }
}
