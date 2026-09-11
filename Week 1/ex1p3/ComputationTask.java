package ex1p3;
import java.util.concurrent.Callable;

public class ComputationTask implements Callable<Long> {
    private String taskName;

    public ComputationTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public Long call() throws Exception {
        Long result = 0L;
        for (int i = 0; i < 1000; i++) {
            result += i; //simple for testing purposes
            System.out.printf(taskName + " #" + i + "\n");
            Thread.sleep(10); //simulate some work
        }
        return result;
    }
}
