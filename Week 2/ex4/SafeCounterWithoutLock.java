package ex4;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounterWithoutLock {
    private final AtomicInteger count = new AtomicInteger(0);
    public void increment() {
        count.incrementAndGet();
    }
    public int getCount() {
        return count.get();
    }

    public int getValue() {
        return count.get();
    }
}
