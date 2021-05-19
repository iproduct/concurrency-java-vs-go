package structured;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class VirtualThreadsExecutor {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadFactory tf = Thread.ofVirtual().factory();
        var deadline = Instant.now().plusSeconds(2);
        ExecutorService e = Executors.newThreadExecutor(tf, deadline);
        Future<String> f = e.submit(() -> "Hello, Loom!"); // spawns a new virtual thread
        String result = f.get(); // joins the virtual thread
        System.out.printf("Result: %s%n", result);
    }
}
