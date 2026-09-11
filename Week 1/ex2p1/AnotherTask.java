package ex2p1;

public class AnotherTask implements Runnable {
    private String taskName;
    private int count;

    public AnotherTask(String taskName, int count) {
        this.taskName = taskName;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.printf(taskName + "#" + i + "\n");
        }
    }
}
