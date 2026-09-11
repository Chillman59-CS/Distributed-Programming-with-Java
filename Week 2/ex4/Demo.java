package ex4;

public class Demo {
    static void main() throws InterruptedException {
        SafeCounterWithoutLock counter = new SafeCounterWithoutLock();

        int NO_THREADS = 10;
        int NO_THREADS_PER_THREAD = 1000;

        Thread[] threads = new Thread[NO_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NO_THREADS_PER_THREAD; j++) {
                    counter.increment();
                }
            });

            threads[i].start();
        }

        for (Thread thread: threads) {
            thread.join();
        }

        System.out.printf("Counter: " + counter.getValue() + "\n");
        System.out.println("Expected: " + (NO_THREADS * NO_THREADS_PER_THREAD));
    }
}
