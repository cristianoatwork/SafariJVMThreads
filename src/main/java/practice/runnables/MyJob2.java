package practice.runnables;

class MyJob2 implements Runnable {

    // i is not local to the method run()
    int i =0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting...");
        for (; i < 10_000; i++) {
            System.out.println(Thread.currentThread().getName() + " i is " + i);
        }
        System.out.println(Thread.currentThread().getName() + " ending...");
    }
}
