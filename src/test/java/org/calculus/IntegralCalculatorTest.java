package org.calculus;

import java.util.function.Function;
import org.junit.jupiter.api.Test;

import static org.calculus.IntegralCalculator.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntegralCalculatorTest {
    Function<Double, Double> f = x -> x * x;

    @Test
    public void testTrapezoidalRule() {
        assertEquals(9.0, trapezoidalRule(f, 0, 3, 1000), 0.1);
    }

    @Test
    public void testMidpointRule() {
        assertEquals(9.0, midpointRule(f, 0, 3, 1000), 0.1);
    }

    @Test
    public void testSimpsonsRule() {
        assertEquals(9.0, simpsonsRule(f, 0, 3, 1000), 0.1);
        assertEquals(9.0, simpsonsRule(f, 0, 3, 10001), 0.1);
    }

    @Test
    public void testMonteCarloMethod() {
        assertEquals(9.0, monteCarloMethod(f, 0, 3, 10000), 0.5);
    }
}