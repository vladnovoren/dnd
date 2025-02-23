package org.calculus;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testMain() {
        String input = "0\n3\n1000\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        Scanner scanner = new Scanner(out.toString());
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("Trapezoidal method:")) {
                double result = Double.parseDouble(line.split(": ")[1]);
                assertEquals(9.0, result, 0.1);
            } else if (line.startsWith("Midpoint method:")) {
                double result = Double.parseDouble(line.split(": ")[1]);
                assertEquals(9.0, result, 0.1);
            } else if (line.startsWith("Simpson's method:")) {
                double result = Double.parseDouble(line.split(": ")[1]);
                assertEquals(9.0, result, 0.01);
            } else if (line.startsWith("Monte Carlo method:")) {
                double result = Double.parseDouble(line.split(": ")[1]);
                assertEquals(9.0, result, 0.5);
            }
        }
        scanner.close();
    }
}
