package ex3;

import java.util.concurrent.CountDownLatch;

public class Demo {
    private static final int PARTIES = 4;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch entryBarrier = new CountDownLatch(1);

        CountDownLatch exitBarrier = new CountDownLatch(PARTIES);

        for (int i = 0; i < PARTIES; i++) {
            Runnable task = new Worker(entryBarrier, exitBarrier);
            new Thread(task).start();
        }

        System.out.printf("Main thread: worker are ready, waitting before release...\n");
        Thread.sleep(1000);

        entryBarrier.countDown();
        System.out.printf("Main thread: workers released, waitting for results...\n");

        exitBarrier.await();
        System.out.printf("Main thread: all workers finish.\n");
    }
}
