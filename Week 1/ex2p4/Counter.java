package ex2p4;

public class Counter implements Runnable {
    private Storage storage;

    public Counter(Storage storage) {
        this.storage = storage;
        new Thread(this, "Counter-Thread").start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            storage.put(i++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
