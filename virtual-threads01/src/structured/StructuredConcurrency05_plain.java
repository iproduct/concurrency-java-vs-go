package structured;

public class StructuredConcurrency05_plain {
    public static void main(String[] args) throws InterruptedException {
        var start = System.nanoTime();
        long result = 0;
        for (int i = 1; i <= 10_000_000; i++) {
            result += i;
        }
        var end = System.nanoTime();
        System.out.printf("Result: %s in %f ms%n", result, (end-start) / 1000000.0);
    }
}
