package structured;

import java.time.Duration;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;

public class StructuredConcurrency04 {
    public static void main(String[] args) throws InterruptedException {
        var queue = new SynchronousQueue<String>();
        ThreadFactory factory = Thread.ofVirtual().name("worker", 0).factory();
        factory.newThread(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(2));
                queue.put("done");
            } catch (InterruptedException e) { }
        }).start();

        String msg = queue.take();
        System.out.printf("Message from Queue: '%s'%n", msg);
    }
}
