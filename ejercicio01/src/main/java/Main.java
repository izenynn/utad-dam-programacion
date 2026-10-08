/*
 * ejercicio01/src/main/java/Main.java
 *
 * Calculator with an interactive menu.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Signature every operator must have: two operands, one result.
 */
interface Operator {
    public int apply(int lhs, int rhs);
}

/**
 * One calculator operation: the symbol shown in the result line, the menu
 * text, and the operator that computes it.
 */
record Operation(char symbol, String label, Operator operator) {
}

/**
 * The arithmetic itself: one function per operation and the table listing
 * them in menu order.
 */
final class Calculator {
    public static final List<Operation> OPERATIONS = List.of(
            new Operation('+', "Sumar", Calculator::add),
            new Operation('-', "Restar", Calculator::subtract),
            new Operation('*', "Multiplicar", Calculator::multiply),
            new Operation('/', "Dividir", Calculator::divide));

    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int subtract(int lhs, int rhs) {
        return lhs - rhs;
    }
    public static int multiply(int lhs, int rhs) {
        return lhs * rhs;
    }
    public static int divide(int lhs, int rhs) {
        if (rhs == 0) {
            throw new ArithmeticException("Division by zero");
        }

        return lhs / rhs;
    }
}

/**
 * Interactive menu: lists the operations, reads the user's choice and the two
 * operands, prints the result, and repeats until the user exits.
 */
final class App {
    private static final List<Operation> OPERATIONS = Calculator.OPERATIONS;
    private static final int EXIT_OPTION = OPERATIONS.size() + 1;
    private BufferedReader reader;

    public void init() {
        reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    public void run() {
        boolean running = true;
        do {
            printMenu();
            int option = readOption();
            if (option == EXIT_OPTION) {
                running = false;
            } else if (option >= 1 && option <= OPERATIONS.size()) {
                operate(OPERATIONS.get(option - 1));
            } else {
                System.out.printf("Opcion no valida%n");
            }
        } while (running);
    }

    private void printMenu() {
        System.out.printf("%nCalculadora basica%n");
        for (int i = 0; i < OPERATIONS.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, OPERATIONS.get(i).label());
        }
        System.out.printf("%d. Salir%n", EXIT_OPTION);
    }

    private int readOption() {
        String text = readLine("Elige una opcion: ");
        if (text == null) {
            return EXIT_OPTION;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void operate(Operation operation) {
        int lhs = readInt("Introduce el primer numero: ");
        int rhs = readInt("Introduce el segundo numero: ");
        try {
            int result = operation.operator().apply(lhs, rhs);
            System.out.printf("Resultado: %d %c %d = %d%n", lhs,
                    operation.symbol(), rhs, result);
        } catch (ArithmeticException e) {
            System.out.printf("Error: no se puede dividir entre cero%n");
        }
    }

    private String readLine(String prompt) {
        System.out.printf("%s", prompt);
        try {
            String line = reader.readLine();
            return line == null ? null : line.trim();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private int readInt(String prompt) {
        while (true) {
            String text = readLine(prompt);
            if (text == null) {
                throw new IllegalStateException("Input closed");
            }
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.printf(
                        "Eso no es un numero entero, intentalo de nuevo%n");
            }
        }
    }

    public void quit() {
        System.out.printf("Gracias por usar la calculadora basica!%n");
        try {
            reader.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e); // que coño es esto
        }
    }
}

/**
 * Entry point: creates the {@link App} and drives its lifecycle.
 */
public final class Main {
    public static void main(String[] args) {
        App app = new App();

        app.init();
        app.run();
        app.quit();
    }
}
