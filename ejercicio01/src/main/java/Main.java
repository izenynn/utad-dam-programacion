/**
 * Entry point: creates the {@link App} and drives its lifecycle.
 */
public final class Main {
    static void main(String[] args) {
        App app = new App();

        app.init();
        app.run();
        app.quit();
    }
}
