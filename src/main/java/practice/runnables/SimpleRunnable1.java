package practice.runnables;

public class SimpleRunnable1 {
    public static void main(String[] args) {
        // my Runnable job
        MyJob1 mj = new MyJob1();
        // invoking run() method synchronously (not in a new thread, but in the same main thread)
        mj.run();
        System.out.println(Thread.currentThread().getName() + " thread - main() method ending...");
    }
}
