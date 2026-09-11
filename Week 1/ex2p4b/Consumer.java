package ex2p4b;

public class Consumer implements Runnable {
    MyQueue q;

    Consumer(MyQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            q.get();
        }
    }
}
