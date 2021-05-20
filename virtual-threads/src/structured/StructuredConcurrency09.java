package structured;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StructuredConcurrency09 {
    public static void main(String[] args) throws InterruptedException {
        try (var e = Executors.newVirtualThreadExecutor()) {
            Stream<Future<String>> tasks = e.submit(List.of(
                    () -> {
                        throw new IOException("too lazy for work");
                    },
                    () -> {
                        Thread.sleep(10000);
                        return "a";
                    },
                    () -> {
                        Thread.sleep(1000);
                        return "b";
                    }
            ));

            String first = tasks
                    .filter(Future::isCompletedNormally)
                    .map(Future::join)
                    .findFirst()
                    .orElse(null);
            System.out.println("one result: " + first);
            e.shutdownNow();
        }

    }
}
