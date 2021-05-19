package structured;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class StructuredConcurrency10 {
    static final ScopeLocal<String> sv = ScopeLocal.forType(String.class);

    void foo() {
        ScopeLocal.where(sv, "A").run(() -> {
            bar();
            baz();
            bar();
        });
    }

    void bar() {
        System.out.println(sv.get());
    }

    void baz() {
        ScopeLocal.where(sv, "B").run(() -> {
            bar();
        });
    }

    public static void main(String[] args) {
        new StructuredConcurrency10().foo();
    }
}
