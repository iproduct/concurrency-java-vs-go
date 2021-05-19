package structured;

public class StructuredConcurrency02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.ofVirtual().name("VirtualThread_01")
                .start(() -> System.out.printf("Hello from thread %s%n", Thread.currentThread().getName()));
        thread.join();
    }
}
