package useblocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UseABlockingQueue {
  public static void delay() {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    BlockingQueue<int[]> queue = new ArrayBlockingQueue<>(10);

    Runnable prod = () -> {
      try {
        for (int i = 0; i < 10_000; i++) {
          int[] data = {i, 0}; // transactionally invalid
          if (i < 100) delay();
          data[1] = i; // transactionally correct...
          if (i == 5_000) data[0] = -1; // test the test
          queue.put(data);
        }
      } catch (InterruptedException ie) {
        System.out.println("shutdown...");
      }
    };

    Runnable cons = () -> {
      try {
        for (int i = 0; i < 10_000; i++) {
          int [] data = queue.take();
          if (data[0] != i || data[0] != data[1]) {
            System.out.println("ERROR AT INDEX " + i);
          }
          if (i > 9_900) delay();
        }
      } catch (InterruptedException ie) {
        System.out.println("shudown...");
      }
    };

    new Thread(prod).start();
    new Thread(cons).start();
    System.out.println("Setup complete");
  }
}
