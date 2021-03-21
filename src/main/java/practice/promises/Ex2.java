package practice.promises;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Ex2 {
    static void delay() {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
        }
    }

    public static void main(String[] args) {

        Supplier<String> headOfPipe = () -> {
            System.out.println("head, thread is " + Thread.currentThread().getName());
            return "Initial Value";
        };

        Function<String, String> middleOfPipe = s -> {
            delay();
            System.out.println("middle, thread is " + Thread.currentThread().getName());
            return s.toUpperCase();
        };

        Consumer<String> endOfPipe = s -> {
            System.out.println("end, thread is " + Thread.currentThread().getName());
            System.out.println("value at the end of the pipe is " + s);
        };

        CompletableFuture<String> cfs = CompletableFuture
                .supplyAsync(headOfPipe)
                .thenApplyAsync(middleOfPipe);
        CompletableFuture<Void> cfv = cfs
                .thenAcceptAsync(endOfPipe);

        CompletableFuture<Void> cfv2 = cfs
                .thenApplyAsync(s-> "Another pipe with " + s)
                .thenAcceptAsync(System.out::println);

        System.out.println("Pipeline assembled ...");

        cfv.join();
        cfv2.join();
        System.out.println("Join returned.");
    }
}
