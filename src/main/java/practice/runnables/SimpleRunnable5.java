package practice.runnables;

public class SimpleRunnable5 {
    public static void main(String[] args) {
        MyJob1 mj = new MyJob1();
        // you can create two threads for running the same job/code
        Thread t1 = new Thread(mj);
        Thread t2 = new Thread(mj);
        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getName() + " thread - main() method ending...");
    }
}

