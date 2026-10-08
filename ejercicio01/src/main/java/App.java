/*
 * ejercicio01/src/main/java/App.java
 *
 * Interactive menu of the calculator.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Interactive menu: lists the operations, reads the user's choice and the two
 * operands, prints the result, and repeats until the user exits.
 */
public final class App {
    private static final List<Calculator.Operation> OPERATIONS =
            Calculator.OPERATIONS;
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
        System.out.printf("Elige una opcion: ");
        String text;
        try {
            text = reader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        if (text == null) {
            return EXIT_OPTION;
        }
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void operate(Calculator.Operation operation) {
        int lhs = readOperand("Introduce el primer numero: ");
        int rhs = readOperand("Introduce el segundo numero: ");
        try {
            int result = operation.operator().apply(lhs, rhs);
            System.out.printf("Resultado: %d %c %d = %d%n", lhs,
                    operation.symbol(), rhs, result);
        } catch (ArithmeticException e) {
            System.out.printf("Error: %s%n", e.getMessage());
        }
    }

    private int readOperand(String prompt) {
        while (true) {
            System.out.printf("%s", prompt);
            String text;
            try {
                text = reader.readLine();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            if (text == null) {
                throw new IllegalStateException("Input closed");
            }
            try {
                return Integer.parseInt(text.trim());
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
