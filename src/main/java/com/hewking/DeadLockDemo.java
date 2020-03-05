package com.hewking;

public class DeadLockDemo {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                method2();
            }
        }).start();

    }

    private static void method2() {
        synchronized (Integer.class) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("想要获取 锁 String.class");
            synchronized (String.class) {
                System.out.println("获取到锁 String.class");
            }
        }
    }

    /**
     * 解决办法
     * private static void method2() {
     * synchronized (String.class) {
     * try {
     * Thread.sleep(3000);
     * } catch (InterruptedException e) {
     * e.printStackTrace();
     * }
     * System.out.println("想要获取 锁 String.class");
     * synchronized (Integer.class) {
     * System.out.println("获取到锁 String.class");
     * }
     * }
     * }
     */

    private static void method1() {
        synchronized (String.class) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("想要获取锁 Ingeger.class");
            synchronized (Integer.class) {
                System.out.println("获取到锁 Ingeger.class");
            }
        }
    }
}
