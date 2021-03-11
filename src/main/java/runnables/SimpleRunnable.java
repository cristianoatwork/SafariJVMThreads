package runnables;

class MyJob implements Runnable {
  int i = 0;
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " starting...");
    for (; i < 10_000; i++) {
      System.out.println(Thread.currentThread().getName() + " i is " + i);
    }
    System.out.println(Thread.currentThread().getName() + " ending...");
  }
}


public class SimpleRunnable {
  public static void main(String[] args) {
    MyJob mj = new MyJob();
//    mj.run();

    Thread t1 = new Thread(mj);
    Thread t2 = new Thread(mj);
//    t1.setDaemon(true);
    t1.start();
    t2.start();
    System.out.println(Thread.currentThread().getName() + " main method ending...");
  }
}
