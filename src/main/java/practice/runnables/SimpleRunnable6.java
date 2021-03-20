package practice.runnables;

public class SimpleRunnable6 {
    public static void main(String[] args) {
        MyJob2 mj = new MyJob2();
        // you can create two threads for running the same job/code
        Thread t1 = new Thread(mj);
        Thread t2 = new Thread(mj);
        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getName() + " thread - main() method ending...");
    }
}
