package ex1p1;

public class TaskExecute {
    public static void main( String[] args) {
        Runnable r1 = new YourTask("Print Task", 20);
        Runnable r2 = new YourTask("Distribute Task", 23);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }
}
