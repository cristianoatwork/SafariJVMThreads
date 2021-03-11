package runnables;

public class Stopper {
  static boolean stop = false;

  static class MyJob implements Runnable {

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " starting...");
      while (! stop)
        ;
      System.out.println(Thread.currentThread().getName() + " stopping...");
    }
  }
  public static void main(String[] args) throws Throwable {
    MyJob my = new MyJob();
    new Thread(my).start();
    System.out.println("My Job launched...");
    Thread.sleep(1000);
    stop = true;
    System.out.println("stop set to true, main exiting...");
  }
}
