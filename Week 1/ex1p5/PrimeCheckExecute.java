package ex1p5;

public class PrimeCheckExecute {
    public static void main(String[] args) {
        Runnable t1Task = new PrimeCheckTask(18);
        Runnable t2Task = new PrimeCheckTask(36);
        Runnable t3Task = new PrimeCheckTask(37);
        Runnable t4Task = new PrimeCheckTask(1021);

        Thread t1 = new Thread(t1Task);
        Thread t2 = new Thread(t2Task);
        Thread t3 = new Thread(t3Task);
        Thread t4 = new Thread(t4Task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
