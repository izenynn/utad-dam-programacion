import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

/**
 * Interactive random number guesser, low value is inclusive, high exclusive.
 */
public final class App {
    private static final int RANDOM_RANGE_LOW = 1; // Inclusive
    private static final int RANDOM_RANGE_HIGH = 101; // Exclusive
    private int randomNumber;
    private BufferedReader reader;

    public void init() {
        reader = new BufferedReader(new InputStreamReader(System.in,
                StandardCharsets.UTF_8));
        randomNumber = (int) (Math.random() * (RANDOM_RANGE_HIGH - 1)
                + RANDOM_RANGE_LOW);
    }

    public void run() {
        System.out.printf("Adivina el numero (%d-%d). Escribe 0 para rendirte"
                        + ".%n", RANDOM_RANGE_LOW,
                RANDOM_RANGE_HIGH - 1);

        boolean running = true;
        int totalTries = 0;
        do {
            ++totalTries;
            int guess = readGuess(totalTries);
            if (guess == 0) {
                System.out.printf("El numero era %d.%nTe has rendido despues "
                                + "de %d intento(s).%n",
                        randomNumber,
                        totalTries);
                running = false;
            } else if (guess == randomNumber) {
                System.out.printf("Correcto! El numero era %d.%nLo has "
                                + "adivinado en %d intento(s).%n",
                        randomNumber,
                        totalTries);
                running = false;
            } else if (guess > randomNumber) {
                System.out.printf("Demasiado alto.%n");
            } else if (guess < randomNumber) {
                System.out.printf("Demasiado bajo.%n");
            } else {
                System.out.printf("%n");
            }
        } while (running);
    }

    private int readGuess(int currentTry) {
        while (true) {
            System.out.printf("Intento %d: ", currentTry);

            String text;
            try {
                text = reader.readLine();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            if (text == null) {
                throw new IllegalStateException("Input closed");
            }

            int guess;
            try {
                guess = Integer.parseInt(text.trim());
            } catch (NumberFormatException e) {
                System.out.printf("Eso no es un numero entero, intentalo de "
                        + "nuevo.%n");
                continue;
            }

            if (guess == 0 || (guess >= RANDOM_RANGE_LOW
                    && guess < RANDOM_RANGE_HIGH)) {
                return guess;
            } else {
                System.out.printf("El numero no esta dentro del rango (%d-%d)"
                                + ", intentalo de nuevo.%n", RANDOM_RANGE_LOW,
                        RANDOM_RANGE_HIGH - 1);
                continue;
            }
        }
    }

    public void quit() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
