package ex1p1;

public class YourTask implements Runnable {
    private String taskName;
    private int counter;

    public YourTask(String taskName, int counter) {
        this.taskName = taskName;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            System.out.println(taskName + " #" + i);
        }
    }
}
