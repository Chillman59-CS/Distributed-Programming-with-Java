package ex1p2;

public class TaskRun {
    public static void main(String[] args) {
        Runnable r1 = new AnotherTask("Task 1", 5);
        Runnable r2 = new AnotherTask("Task 2", 5);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }
}
