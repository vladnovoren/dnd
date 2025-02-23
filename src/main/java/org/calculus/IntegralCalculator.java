package org.calculus;

import java.util.function.Function;

public class IntegralCalculator {
    public static double trapezoidalRule(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.5 * (f.apply(a) + f.apply(b));
        for (int i = 1; i < n; i++) {
            sum += f.apply(a + i * h);
        }
        return sum * h;
    }

    public static double midpointRule(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += f.apply(a + (i + 0.5) * h);
        }
        return sum * h;
    }

    public static double simpsonsRule(Function<Double, Double> f, double a, double b, int n) {
        if (n % 2 == 1) n++;
        double h = (b - a) / n;
        double sum = f.apply(a) + f.apply(b);
        for (int i = 1; i < n; i++) {
            sum += (i % 2 == 0 ? 2 : 4) * f.apply(a + i * h);
        }
        return sum * h / 3;
    }

    public static double monteCarloMethod(Function<Double, Double> f, double a, double b, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            double x = a + Math.random() * (b - a);
            sum += f.apply(x);
        }
        return (b - a) * sum / n;
    }
}