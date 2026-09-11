package ex2p4b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main(String[] args) {
        System.out.printf("Press Ctrl-C to stop.%n");
        ExecutorService service = Executors.newFixedThreadPool(2);

        MyQueue q = new MyQueue();

        service.execute(new Producer(q));
        service.execute(new Consumer(q));
    }
}
