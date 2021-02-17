package com.hewking.test.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Classname SyncThreadTest
 * @Description TODO
 * @Date 2021/2/17 4:13 下午
 * @Created by jianhao
 */
public class SyncThreadTest {

    private boolean stop;

    private synchronized void requestStop(){
        this.stop = true;
    }

    private synchronized boolean stopRequested(){
        return this.stop;
    }

    @Test
    public void test() throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested()) {
                    i ++;
                    System.out.println(String.format("i=%d",i));
                }
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1L);
        requestStop();

    }

}
