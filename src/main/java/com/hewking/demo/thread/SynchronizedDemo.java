package com.hewking.demo.thread;

public class SynchronizedDemo {

    public static void main(String[] args) {

        new SynchronizedDemo().new A().start();

        new Thread(runnable).start();
    }

    private class A extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }

    private static Runnable runnable = () -> {
        String lock = null;
        synchronized (lock) {

        }
    };



}
