package structured;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class StructuredConcurrency05 {
    public static void main(String[] args) throws InterruptedException {
        final var result = new AtomicLong();
        final var latch = new CountDownLatch(1_000_000);
        var start = System.nanoTime();
        for (int i = 1; i <= 10_000_000; i++) {
            final var index = i;
            Thread.ofVirtual().start(() -> {
                result.addAndGet(index);
                latch.countDown();
            });
        }
        var end = System.nanoTime();
        System.out.printf("Result: %s in %f ms%n", result.get(), (end-start) / 1000000.0);

        // wait for completion
        latch.await();

    }
}
