package practice.runnables;

class MyJob1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting...");
        // i variable is local
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " i is " + i);
        }
        System.out.println(Thread.currentThread().getName() + " ending...");
    }
}
