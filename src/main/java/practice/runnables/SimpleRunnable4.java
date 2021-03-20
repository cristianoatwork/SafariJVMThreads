package practice.runnables;

public class SimpleRunnable4 {
    public static void main(String[] args) {
        MyJob1 mj = new MyJob1();
        Thread t1 = new Thread(mj);
        t1.setDaemon(true); // Daemon thread, JVM won't wait for its end
        t1.start();

        //System.exit(0); // it would stop the main thread too

        // if I don't pause the main thread its end would determine the end of Daemon thread as well.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException x) {}

        System.out.println(Thread.currentThread().getName() + " thread - main() method ending...");
    }
}
