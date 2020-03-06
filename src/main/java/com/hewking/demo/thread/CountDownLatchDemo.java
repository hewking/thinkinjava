package com.hewking.demo.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private int count;

    /**
     * 需求： 模拟一个场景，跑步比赛裁判一声令下，n名运动员开始比赛。
     * 比赛完了只有统一成绩。
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo demo = new CountDownLatchDemo();
        CountDownLatch stargSign = new CountDownLatch(1);
        CountDownLatch endSign = new CountDownLatch(10);
        for (int i = 0 ; i < 10 ; i ++) {
            demo.new PlayerTask(i + 1,stargSign,endSign).start();
        }
        stargSign.countDown();
        endSign.await();
        System.out.println("courcse: " + demo.count);
    }

    private class PlayerTask extends Thread{

        private CountDownLatch startSign;
        private CountDownLatch doneSign;

        PlayerTask(int id,CountDownLatch startSign,CountDownLatch doneSign){
            setName("Player#" + id);
            this.startSign = startSign;
            this.doneSign = doneSign;
        }

        @Override
        public void run() {
            try {
                startSign.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.doWork();
            doneSign.countDown();
        }

        private void doWork(){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int course = (int) (Math.random() * 100);
            System.out.println(getName() + " cource: " + course);
            count += course;
        }
    }



}
