import koncurrent.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;

import static expect.ExpectBuilders.*;

public class FewApiTestApiJava {

    private final Executor executor = MockExecutors.create();

    @Test
    void should_be_able_to_create_a_few() {
        FewBuilder.<Integer>few(collector -> {
            System.out.println("Emitting 0");
            collector.emit(0);
        }, executor).collect(it -> {
            System.out.println("Collecting " + it);
            expect(it).toBe(0);
            System.out.println("Collected " + it);
        });
    }

    @Test
    void should_be_able_to_collect_multiple_few() {
        var name = FewBuilder.<String>few(it -> {
            System.out.println("Emitting A");
            it.emit("A");
            System.out.println("Emitting N");
            it.emit("N");
            System.out.println("Emitting D");
            it.emit("D");
            System.out.println("Emitting Y");
            it.emit("Y");
            System.out.println("Finished emitting");
        }, executor);
        System.out.println("Haven't began collection, so this should run first");
        final var collectedName = new StringBuilder();
        name.collect(it -> {
            System.out.println("Collecting " + it);
            collectedName.append(it);
        });
        System.out.println("Collected: " + collectedName);
    }

    @Test
    void should_be_able_to_interpect_intermediate_values() {
        var name = FewBuilder.<String>few(it -> {
            it.emit("A");
            it.emit("N");
            it.emit("D");
            it.emit("Y");
        }, executor).map(it -> "'" + it + "'");
        final var collectedName = new StringBuilder();
        name.collect(it -> {
            System.out.println("Collecting " + it);
            collectedName.append(it);
        });
        System.out.println("Collected: " + collectedName);
    }

    @Test
    void should_be_constructable_easily_from_java() {
        FewBuilder.<Integer>few(it -> {
            it.emit(5);
        }, executor);
    }
}
