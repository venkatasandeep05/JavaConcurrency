package org.example;

public class DaemonThreadExample
{
    public static void main( String[] args )
    {
        Runnable r=()->{
          while(true)
          {
              int i=0;
              i++;
          }
        };
        Thread t=new Thread(r);
        t.setDaemon(true);
        t.start();
    }
}
