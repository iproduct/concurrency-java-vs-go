package structured;

public class StructuredConcurrency01 {
    public static void main(String[] args) throws InterruptedException {
        var thread = Thread.startVirtualThread(() -> {
            System.out.println("Hello, Loom!");
        });
        thread.join();
    }
}
