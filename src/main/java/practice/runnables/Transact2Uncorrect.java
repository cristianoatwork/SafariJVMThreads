package practice.runnables;

public class Transact2Uncorrect {
    static long counter = 0;
    static class MyJob implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++) {
                counter++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyJob mj = new MyJob();
        Thread t1 = new Thread(mj);
        t1.start();
        // sleep to wait for the thread to complete
        Thread.sleep(1000); // bad, there is no happens-before relationship which guarantees the thread finishing
        System.out.println(counter);
    }
}
