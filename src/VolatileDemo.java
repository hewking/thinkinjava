public class VolatileDemo {

    private volatile int count = 0;

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();
        new Thread(demo.new PrintRunnable()).start();

        System.out.println(" main count : " + demo.count);

    }

    private class PrintRunnable implements Runnable{
        @Override
        public void run() {
            for (int i = 0 ; i < 10 ; i ++) {
                System.out.println("count : " + ++count);
            }
        }
    }
}
