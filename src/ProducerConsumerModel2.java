import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerModel2 {

    private volatile AtomicInteger count = new AtomicInteger();
    private final int  FULL = 10;
    private String LOCK = "lock";

    public static void main(String[] args) {
        ProducerConsumerModel2 mode = new ProducerConsumerModel2();
        new Thread(mode.new Producer()).start();
        new Thread(mode.new Coumser()).start();
        new Thread(mode.new Producer()).start();
        new Thread(mode.new Coumser()).start();
        new Thread(mode.new Producer()).start();
        new Thread(mode.new Coumser()).start();
        new Thread(mode.new Producer()).start();
        new Thread(mode.new Coumser()).start();
        new Thread(mode.new Producer()).start();
        new Thread(mode.new Coumser()).start();
    }

    private class Producer implements Runnable{
        @Override
        public void run() {
            for (int i = 0 ; i < 10 ; i ++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count.get() == FULL) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count.getAndIncrement();
                    System.out.println("生产者 " + Thread.currentThread().getName() + "生产 " + count.get());
                    LOCK.notifyAll();
                }


            }
        }
    }

    private class Coumser implements Runnable {
        @Override
        public void run() {
            for (int i = 0 ; i < 10 ; i ++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    if (count.get() == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count.getAndDecrement();
                    System.out.println("消费者 " + Thread.currentThread().getName() + "消费 " + count.get());
                    LOCK.notifyAll();

                }

            }
        }
    }


}
