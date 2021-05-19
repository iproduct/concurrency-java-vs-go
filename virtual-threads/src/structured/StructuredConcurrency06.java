package structured;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StructuredConcurrency06 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Executor scheduler = (task) -> {
            pool.execute(task);
            Thread vthread = ((Thread.VirtualThreadTask) task).thread();
            System.out.println(vthread);
        };
        Thread thread = Thread.ofVirtual()
                .name("VirtualThread_01")
                .scheduler(scheduler)
                .start(() -> System.out.printf("Hello from thread %s%n", Thread.currentThread().getName()));
        thread.join();
    }
}
