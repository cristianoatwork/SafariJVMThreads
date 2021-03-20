package practice.runnables;

public class SimpleRunnable3 {
    public static void main(String[] args) {
        MyJob1 mj = new MyJob1();
        Thread t1 = new Thread(mj);
        // finally running the "run()" method asynchronously, invoking start() method of the Thread
        t1.start();
        System.out.println(Thread.currentThread().getName() + " thread - main() method ending...");
    }
}
