package org.calculus;

import java.util.Scanner;
import java.util.function.Function;

import static org.calculus.IntegralCalculator.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter integration limits (a and b): ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        System.out.println("Enter the number of partitions (n): ");
        int n = scanner.nextInt();

        Function<Double, Double> f = x -> x * x; // Example: f(x) = x^2

        System.out.println("Trapezoidal method: " + trapezoidalRule(f, a, b, n));
        System.out.println("Midpoint method: " + midpointRule(f, a, b, n));
        System.out.println("Simpson's method: " + simpsonsRule(f, a, b, n));
        System.out.println("Monte Carlo method: " + monteCarloMethod(f, a, b, n * 10));

        scanner.close();
    }
}