package ex2p4;

public class Printer implements Runnable {
    private Storage storage;

    public Printer(Storage storage) {
        this.storage = storage;
        new Thread(this, "Printer-Thread").start();
    }

    @Override
    public void run() {
        while (true) {
            storage.get();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
