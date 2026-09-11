package ex1p3;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ComputationExecutor {
    static void main(String[] args) throws Exception {
        Callable<Long> call = new ComputationTask("Long last computation");
        FutureTask<Long> task = new FutureTask<>(call);
        new Thread(task).start();

        Long result = task.get();
        System.out.println("Computation result: " + result);
    }
}
