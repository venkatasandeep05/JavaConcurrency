package org.example;

public class PrintNumbersAndCharactersSequential {
    private final Object lock = new Object();
    private boolean numberTurn = true;

    public static void main(String[] args) {
        App app = new App();

        Thread t1 = new Thread(() -> app.printNumber());
        Thread t2 = new Thread(() -> app.printCharacter());
        t1.start();
        t2.start();
    }

    private void printCharacter() {
        for (char c = 'A'; c <= 'Z'; c++) {
            synchronized (lock) {
                while (numberTurn) {  // Corrected: Wait if it is number's turn
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println(c);
                numberTurn = true;
                lock.notify();
            }
        }
    }

    private void printNumber() {
        for (int i = 1; i <= 26; i++) {
            synchronized (lock) {
                while (!numberTurn) {  // Corrected: Wait if it is character's turn
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(i);
                numberTurn = false;
                lock.notify();
            }
        }
    }
}
