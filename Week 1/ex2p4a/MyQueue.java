package ex2p4a;

public class MyQueue {
    int n;
    synchronized int get() {
        System.out.printf("Got: " + n);
        return n;
    }
    synchronized void put(int n) {
        this.n = n;
        System.out.printf("Put: " + n);
    }
}
