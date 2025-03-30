package org.example;

import java.util.Scanner;

public class PrimeNumberFindingUsingConcurrency
{
    public static void main(String[] args )
    {
        while(true) {
            System.out.println("Enter the value of n to find the th prime number:");
            Scanner sc = new Scanner(System.in);
            int n=sc.nextInt();
            if(n==0)
                break;
            Runnable runnable = () -> {
                int prime = PrimeNumberUtil.calculatePrime(n);
                System.out.println(n + "th prime number is " + prime);
            };
            Thread t=new Thread(runnable);
            t.start();
        }

    }
}

public class PrimeNumberUtil
{
    public static int calculatePrime(int n)
    {
        int number;
        int numberOfPrimesFound;
        int i;
        number=1;
        numberOfPrimesFound=0;
        while(numberOfPrimesFound<n)
        {
            number++;
            for(i=2;i<=number && number%i!=0;i++)
            {
            }
            if(i==number)
                numberOfPrimesFound++;
        }
        return number;
    }
}
