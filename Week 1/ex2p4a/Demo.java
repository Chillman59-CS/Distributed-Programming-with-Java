package ex2p4a;

public class Demo {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        new Producer(q);
        new Consumer(q);
    }
}
