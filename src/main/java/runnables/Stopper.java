package runnables;

public class Stopper {
  static /*volatile*/ boolean stop = false;

  static class MyJob implements Runnable {

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " starting...");
      int x = 0;
      while (! stop) {
//        if (x % 1000 == 0) System.out.print(".");
//        x++;
      }
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
