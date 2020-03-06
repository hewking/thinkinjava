package com.hewking.demo.thread;

public class ThreadStateDemo {

    private static final int MAX_COUNT = 100;

    private int count;
    private Object lock = new Object();
    private volatile boolean mQuit;


    public static void main(String[] args) {
        new ThreadStateDemo().test();
    }

    private void test() {
        for (int i = 0 ; i < 5 ; i ++) {
            new Thread(producer).start();
            new Thread(consumer).start();
        }
    }


    private Runnable producer = new Runnable() {
        @Override
        public void run() {
            while (!mQuit) {
                synchronized (lock) {
                    while (count >= MAX_COUNT) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count ++;
                    lock.notifyAll();
                    System.out.println("producer: count " + count);
                }
            }
        }
    };

    private Runnable consumer = () -> {
            synchronized (lock) {
                while (count <= 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count --;
                lock.notifyAll();
                System.out.println("consumer: count " + count);
            }
    };

    private void quit() {
        this.mQuit = true;
    }

}
