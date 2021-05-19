package structured;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StructuredConcurrency07 {
    public static void main(String[] args) {
        var demo = new StructuredConcurrency07();
        demo.top();
    }

    void top() {
        var deadline = Instant.now().plusSeconds(2);
        try (ExecutorService executor = Executors.newVirtualThreadExecutor(deadline)) {
            executor.submit(List.of(() -> foo().join(), () -> bar().join()))
                    .filter(Future::isCompletedNormally)
                    .map(Future::join)
                    .forEach(System.out::println);
            ;
        }
    }

    Future<String> foo() {
        try (ExecutorService executor = Executors.newVirtualThreadExecutor()) {
            return executor.submit(() -> "foo");
        }
    }

    Future<String> bar() {
        try (ExecutorService executor = Executors.newVirtualThreadExecutor()) {
            return executor.submit(() -> "bar");
        }
    }
}
