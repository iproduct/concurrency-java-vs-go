package structured;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StructuredConcurrency08 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (ExecutorService executor = Executors.newVirtualThreadExecutor()) {

            // Submits a value-returning task and waits for the result
            Future<String> future = executor.submit(() -> "foo");
            String result = future.join();

            // Submits two value-returning tasks to get a Stream that is lazily populated
            // with completed Future objects as the tasks complete
            Stream<Future<String>> stream = executor.submit(List.of(() -> "foo", () -> "bar"));
            stream.filter(Future::isCompletedNormally)
                    .map(Future::join)
                    .forEach(System.out::println);

            // Executes two value-returning tasks, waiting for both to complete
            List<Future<String>> results1 = executor.invokeAll(List.of(() -> "foo", () -> "bar"));
            System.out.printf("All messages: '%s'%n",
                    results1.stream().map(Future::join).collect(Collectors.joining(", ")));

            // Executes two value-returning tasks, waiting for both to complete. If one of the
            // tasks completes with an exception, the other is cancelled.
            List<Future<String>> results2 = executor.invokeAll(List.of(() -> "foo", () -> "bar"), /*waitAll*/ false);
            System.out.printf("All messages [waitAll=false]: '%s'%n",
                    results2.stream().map(Future::join).collect(Collectors.joining(", ")));

            // Executes two value-returning tasks, returning the result of the first to
            // complete, cancelling the other.
            String first = executor.invokeAny(List.of(() -> "foo", () -> "bar"));
            System.out.printf("First message: '%s'%n", first);
        }

    }
}
