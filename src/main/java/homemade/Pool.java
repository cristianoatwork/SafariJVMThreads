package homemade;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class MyPoolWorker implements Runnable {
  private BlockingQueue<Runnable> jobQueue;

  public MyPoolWorker(BlockingQueue<Runnable> jobQueue) {
    this.jobQueue = jobQueue;
  }

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        jobQueue.take().run();
      }
    } catch (InterruptedException ie) {
      System.out.println("Worker shutting down");
    }
  }
}

class SimpleTask implements Runnable {
  @Override
  public void run() {
    try {
      System.out.println("Job starting");
      Thread.sleep((int) (Math.random() * 2000) + 1000);
      System.out.println("Job finishing...");
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
      return;
    }
  }
}

public class Pool {
  public static void main(String[] args) throws Throwable {
    BlockingQueue<Runnable> theQueue = new ArrayBlockingQueue<>(10);
    MyPoolWorker mpw = new MyPoolWorker(theQueue);
    final int THREAD_COUNT = 2;
    Thread [] workers = new Thread[THREAD_COUNT];
    for (int i = 0; i < THREAD_COUNT; i++) {
      workers[i] = new Thread(mpw);
      workers[i].start();
    }

    theQueue.put(new SimpleTask());
    theQueue.put(new SimpleTask());
    theQueue.put(new SimpleTask());
    theQueue.put(new SimpleTask());
    theQueue.put(new SimpleTask());
    System.out.println("Jobs submitted");
    Thread.sleep(10_000);
    for (int i = 0; i < THREAD_COUNT; i++) {
      workers[i].interrupt();
    }
  }
}
