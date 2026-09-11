package ex2p4;

public class Main {
    public static void main(String[] args) {
        Storage sharedStorage = new Storage();

        new Counter(sharedStorage);
        new Printer(sharedStorage);
    }
}
