package practice.runnables;

public class Stopper2 {
    // VOLATILE is necessary for creating a HB relationship
    static volatile boolean stop = false;

    static class MyJob implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " thread - starting ...");
            while (! stop)
                ;
            System.out.println(Thread.currentThread().getName() + " thread - stopping ...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyJob mj = new MyJob();
        new Thread(mj).start();
        System.out.println("My job launched ...");
        Thread.sleep(1000);
        stop = true; // THIS WRITING A VOLATILE variable, create an Happen-Before relationship
                     // with the reading of the same variable in the while loop condition in the other thread
        System.out.println("stop set to true, main exiting ...");
    }

}
