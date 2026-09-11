package ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Demo {
    public static void main(String[] args) {
        Account account = new Account("123", "John Doe", 100.0D);

        Callable<Double> withdrawTask = () -> {
            return account.withdraw(1.0D);
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Double>> futures = new ArrayList<Future<Double>>();

        // Kich hoat 100 luon rut tien dong thoi
        for (int i = 0; i < 100; i++) {
            futures.add(executorService.submit(withdrawTask));
        }

        // Ngung nhan task moi, doi task cu hoan thanh
        executorService.shutdown();

        double total = 0.0D;

        // Duyet danh sach de lay ket qua tu cac task da hoan thanh
        for (Future<Double> future: futures) {
            try {
                total += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Total withdrawn: " + total + "\n");
        System.out.printf("Balance: " + account.getBalance());
    }
}
