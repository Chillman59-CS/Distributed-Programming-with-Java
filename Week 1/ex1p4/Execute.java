package ex1p4;

public class Execute {
    public static void main(String[] args) throws InterruptedException {
        // create the task
        Runnable task = new DisplayNumbers();

        // wrap it in a Thread and start (do NOT call run() directly)
        Thread t = new Thread(task);
        t.start();
    }
}
