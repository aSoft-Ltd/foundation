package unit;

import expect.BasicExpectation;
import expect.Expect;
import org.junit.jupiter.api.Test;

import java.util.List;

import static expect.Expect.*;

public class ExpectationJavaTest {
    @Test
    public void toBe_should_behave_the_same() {
        BasicExpectation<Integer> exp = Expect.expect(5);
        ((BasicExpectation<Integer>) expect(1 + 1)).toBe(2);
    }

    @Test
    public void toBe_of_collection_behave_as_intended() {
        expectCollection(List.of(1, 2, 3)).toBeInstanceOf(List.class);
        expectCollection(List.of(4, 5, 6)).toContain(5);
    }

    @Test
    public void should_fail() {
        expectFunction(() -> {
            throw new RuntimeException("Not implemented");
        }).toFail();
    }
}
