/*
 * ejercicio01/src/main/java/Main.java
 *
 * Entry point for ejercicio01.
 */

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
