package practice.runnables;

public class SimpleRunnable2 {
    public static void main(String[] args) {
        MyJob1 mj = new MyJob1();
        // create an instance of Thread class
        Thread t1 = new Thread(mj);
        // still invoking the run() method synchronously
        t1.run();
        System.out.println(Thread.currentThread().getName() + " thread - main() method ending...");
    }
}
