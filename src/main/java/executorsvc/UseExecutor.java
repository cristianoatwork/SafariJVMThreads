package executorsvc;

import java.util.concurrent.*;

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

class MyCallable implements Callable<String> {
  @Override
  public String call() throws Exception {
    return "Callable finished";
  }
}

public class UseExecutor {
  public static void main(String[] args) throws Throwable {
    ExecutorService es = Executors.newFixedThreadPool(2);

    es.submit(new SimpleTask()); // submit can accept a Runnable OR a "Callable"
    es.submit(new SimpleTask());
    es.submit(new SimpleTask());
    es.submit(new SimpleTask());
    es.submit(new SimpleTask());
    Future<String> handle = es.submit(new MyCallable());

    System.out.println("Jobs submitted");
    System.out.println("Shutdown of ES");
    es.shutdown();

//    if (handle.isDone()) {
    // blocking unless handle.isDone() is true.
      String result = handle.get(); // throws Excpetions!!!
      System.out.println("callable returned " + result);
//    }

    System.out.println("end of main");
  }
}


