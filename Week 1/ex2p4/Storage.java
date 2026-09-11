package ex2p4;

public class Storage {
    private int value;
    private boolean valueSet = false;

    public synchronized void put(int value) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        this.value = value;
        this.valueSet = true;
        System.out.println("Counter (Cất vào): " + value);

        notify();
    }

    public synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int temp = this.value;
        this.valueSet = false;
        System.out.println("Printer (In ra)  : " + temp);
        notify();

        return temp;
    }
}
