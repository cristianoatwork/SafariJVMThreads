package runnables;

public class Transact {
//  static volatile long counter = 0; // does not protect transactions!!!
  static long counter = 0;
  static class MyJob implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 100_000; i++) {
        synchronized (this) {
          counter++;
        }
      }
    }
  }

  public static void main(String[] args) throws Throwable {
    MyJob mj = new MyJob();
    Thread t1 = new Thread(mj);
    Thread t2 = new Thread(mj);
    t1.start();
    t2.start();
//    Thread.sleep(1000); // BAD, no happens-before (AND might not have finished
    t1.join(); // waits for thread to die, AND creates hb
    t2.join(); // waits for thread to die, AND creates hb
    System.out.println(counter);
  }
}
