package practice.runnables;

public class Transact6 {
    // making counter back as not-volatile
    static long counter = 0;
    static class MyJob implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++) {
                synchronized (this) { // Mutual exclusion (transaction)
                                      // and happens-before relationships
                    counter++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyJob mj = new MyJob();
        Thread t1 = new Thread(mj);
        Thread t2 = new Thread(mj);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }
}


