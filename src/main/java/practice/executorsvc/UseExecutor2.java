package practice.executorsvc;

import java.util.concurrent.*;

class SimpleTask2 implements Runnable {

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

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "Callable finished";
    }
}

public class UseExecutor2 {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        es.submit(new SimpleTask2()); // submit can accept a Runnable or a Callable
        es.submit(new SimpleTask2());
        es.submit(new SimpleTask2());
        es.submit(new SimpleTask2());
        es.submit(new SimpleTask2());
        // submitting a Callable
        Future<String> handle = es.submit(new MyCallable());

        System.out.println(Thread.currentThread().getName() + " thread - Jobs submitted");
        //Thread.sleep(10_000); // we don't need it any longer
        System.out.println(Thread.currentThread().getName() + " thread - Shutdown of Executor");
        es.shutdown(); // stop accepting new tasks and wait for last task submitted to finish before shutdown

        // getting a Callable result
        // handle.get() is blocking until handle.isDone() is true
        String result = handle.get(); // can throw an ExecutionException
        System.out.println("Callable returned: " + result);

        System.out.println(Thread.currentThread().getName() + " thread - end of main()");

    }
}

