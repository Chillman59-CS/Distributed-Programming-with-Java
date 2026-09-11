package ex2p1;

public class YourTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread t = new Thread(new AnotherTask("Another Task", 5));

            for (int i = 0; i < 8; i++) {
                System.out.printf("YourTask#" + i + "\n");
                if (i == 4) {
                    t.start(); //start another task
                    t.join(); //join thread
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
