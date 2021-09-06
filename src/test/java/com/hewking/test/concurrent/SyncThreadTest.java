package com.hewking.test.concurrent;

import org.junit.Test;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    /**
     * effective java 79条避免过度同步示例
     */
    @Test
    public void testObserver(){
        ObservableSet<Integer> set =new ObservableSet<>(new HashSet<>());
//        set.addObserver((s, e) -> System.out.println(e));
        set.addObserver(new SetObserver<Integer>() {

            @Override
            public void added(ObservableSet<Integer> set, Integer e) {
                ExecutorService exec = Executors.newSingleThreadExecutor();
                try {
                    exec.submit(()-> {
                        System.out.println(e);
                        if (e == 23)
                            set.removeObserver(this);
                    }).get();
                } catch (InterruptedException | ExecutionException interruptedException) {
                    interruptedException.printStackTrace();
                } finally {
                    exec.shutdown();
                }
            }
        });
        for (int i = 0; i < 100; i++)
            set.add(i);
    }

}
