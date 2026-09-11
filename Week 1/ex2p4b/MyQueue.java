package ex2p4b;

public class MyQueue {
    int n;
    boolean valueSet = false;
    synchronized int get() {
        if (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {}

        }
        System.out.printf("Got: %d%n", n);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {}
        valueSet = false;
        notify();
        return n;
    }
    synchronized void put(int n) {
        if (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.n = n;
        valueSet = true;
        System.out.printf("Put: %d%n", n);
        notify();
    }
}
