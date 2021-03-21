package practice.executorsvc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SimpleTask implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " thread - Job Starting");
            Thread.sleep((long) (Math.random() * 2000L) + 1000); // sleep for 1-3 sec
            System.out.println(Thread.currentThread().getName() + " thread - Job Finishing ...");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " thread - Job interrupted !!!");
            Thread.currentThread().interrupt();
            return;
        }
    }
}

public class UseExecutor1 {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        es.submit(new SimpleTask()); // submitting Runnable
        es.submit(new SimpleTask());
        es.submit(new SimpleTask());
        es.submit(new SimpleTask());
        es.submit(new SimpleTask());
        System.out.println(Thread.currentThread().getName() + " thread - Jobs submitted");
        //Thread.sleep(10_000); // we don't need it any longer
        System.out.println(Thread.currentThread().getName() + " thread - Shutdown of Executor");
        es.shutdown(); // stop accepting new tasks and wait for last task submitted to finish before shutdown
        System.out.println(Thread.currentThread().getName() + " thread - end of main()");

    }
}

