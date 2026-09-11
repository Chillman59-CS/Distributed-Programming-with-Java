package ex2p4b;

public class Producer implements Runnable {
    MyQueue q;

    public Producer(MyQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }

}
