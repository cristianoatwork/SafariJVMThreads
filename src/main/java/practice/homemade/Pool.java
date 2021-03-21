package practice.homemade;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class MyPoolWorker implements Runnable {
    private BlockingQueue<Runnable> jobQueue; // thread safe

    public MyPoolWorker(BlockingQueue<Runnable> jobQueue) {
        this.jobQueue = jobQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                jobQueue.take()
                        .run();
            }
            System.out.println(Thread.currentThread().getName() + " thread - Worker thread ending !!!");
        } catch (InterruptedException ie) {
            System.out.println(Thread.currentThread().getName() + " thread - Worker shutting down !!!");
        }
    }
}

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

public class Pool {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> theQueue = new ArrayBlockingQueue<>(10);
        MyPoolWorker mpw = new MyPoolWorker(theQueue);
        final int THREAD_COUNT = 2;
        Thread[] workers = new Thread[THREAD_COUNT];
        for (int i=0; i<THREAD_COUNT; i++) {
            workers[i] = new Thread(mpw);
            workers[i].start();
        }

        theQueue.put(new SimpleTask());
        theQueue.put(new SimpleTask());
        theQueue.put(new SimpleTask());
        theQueue.put(new SimpleTask());
        theQueue.put(new SimpleTask());
        System.out.println(Thread.currentThread().getName() + " thread - Jobs submitted");
        Thread.sleep(10_000); // You need to wait enough time to complete tasks
                                    // before shutting down the workers
        for (int i=0; i<THREAD_COUNT; i++){
            workers[i].interrupt();
        }
    }
}
