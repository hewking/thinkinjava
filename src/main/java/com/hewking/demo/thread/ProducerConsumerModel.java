package com.hewking.demo.thread;

/**
 * 生产者消费者模型
 * 1.生产者和消费者共享同一空间
 * 2.空间已满，生产者停止生产
 * 3.空间已空，消费者停止取出
 */
public class ProducerConsumerModel {

    private int count = 0;
    private static final int FULL = 10;
    public String LOCK = "lock";

    public static void main(String[] args) {
        ProducerConsumerModel model = new ProducerConsumerModel();
        new Thread(model.new Producer()).start();
        new Thread(model.new Consumer()).start();
        new Thread(model.new Producer()).start();
        new Thread(model.new Consumer()).start();
        new Thread(model.new Producer()).start();
        new Thread(model.new Consumer()).start();
        new Thread(model.new Producer()).start();
        new Thread(model.new Consumer()).start();

    }

    private class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (LOCK) {
                    while (count == FULL) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("生产者 " + Thread.currentThread().getName() + " 生产 ：" + count);
                    LOCK.notifyAll();
                }
            }

        }
    }

    private class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费者 " + Thread.currentThread().getName() + " 消费 ：" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

}
