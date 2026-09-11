package ex1p5;

public class PrimeCheckTask implements Runnable {
    private final int x;

    public PrimeCheckTask(int x) {
        this.x = x;
    }

    @Override
    public void run() {
        boolean isPrime = true;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println(x + " is prime.");
        } else {
            System.out.println(x + " is not prime.");
        }
    }
}
