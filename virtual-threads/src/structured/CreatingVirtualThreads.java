package structured;

import java.util.concurrent.ThreadFactory;

public class CreatingVirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = Thread.startVirtualThread(() -> System.out.println("Hello, Loom!"));
        Thread t2 = Thread.ofVirtual().unstarted(() -> System.out.println("Hello, Loom!"));
        t2.start();
        ThreadFactory factory = Thread.ofVirtual().name("worker", 0).factory();
        Thread t3 =factory.newThread(() -> {
            System.out.println("Hello, Loom!");
        });
        t3.start();
        t3.join();
    }
}
