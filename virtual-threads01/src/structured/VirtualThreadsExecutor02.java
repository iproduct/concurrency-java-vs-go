package structured;

import java.time.Instant;
import java.util.concurrent.*;

public class VirtualThreadsExecutor02 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var deadline = Instant.now().plusSeconds(2);
        ExecutorService e = Executors.newVirtualThreadExecutor(deadline);
        Future<String> f = e.submit(() -> "Hello, Loom!"); // spawns a new virtual thread
        String result = f.get(); // joins the virtual thread
        System.out.printf("Result: %s%n", result);
    }
}
