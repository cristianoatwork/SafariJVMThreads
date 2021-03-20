package practice.runnables;

public class Stopper1 {
    static boolean stop = false;

    //nested class
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
        stop = true;
        System.out.println("stop set to true, main exiting ...");
    }

}
