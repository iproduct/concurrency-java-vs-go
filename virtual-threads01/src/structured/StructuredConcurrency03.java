package structured;

import java.time.Duration;
import java.util.concurrent.SynchronousQueue;

public class StructuredConcurrency03 {
    public static void main(String[] args) throws InterruptedException {
        var queue = new SynchronousQueue<String>();

        Thread.ofVirtual().start(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(2));
                queue.put("done");
            } catch (InterruptedException e) { }
        });

        String msg = queue.take();
        System.out.printf("Message from Queue: '%s'%n", msg);
    }
}
