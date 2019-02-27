package com.hewking.demo.test;

/**
 * 一个线程打印1，3，5...
 * 一个线程打印2,4,6...
 * 最终打印到 1，2，3，4，5。。。
 */
public class TwoThreadPrint {
    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(() -> {

            synchronized (lock) {
                for (int i = 0 ; i < 50 ; i++) {
                    System.out.println("number : " + (2 * i + 1));
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }


        }).start();

        new Thread(() -> {

            synchronized (lock) {

            for (int i = 1; i <= 50 ; i++) {
                System.out.println("number : " + 2 * i);
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }

        }).start();

    }
}
