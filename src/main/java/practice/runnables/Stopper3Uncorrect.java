package practice.runnables;

public class Stopper3Uncorrect {
    // Non volatile (not creating an Hb relation)
    static boolean stop = false;

    static class MyJob implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " thread - starting ...");
            int x=0;
            while (! stop) {
                // Adding a print in the body could make the program working
                // But there is no hb relation can guarantee you it will always work.
                if (x % 1000 == 0) System.out.print(".");
                x++;
            }
            System.out.println(Thread.currentThread().getName() + " thread - stopping ...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyJob mj = new MyJob();
        new Thread(mj).start();
        System.out.println("My job launched ...");
        Thread.sleep(1000);
        stop = true;
        System.out.println("stop set to true, main exiting ...");
    }

}
