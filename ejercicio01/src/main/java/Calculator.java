import java.util.List;

/**
 * The arithmetic itself: one function per operation and the table listing
 * them in menu order.
 */
public final class Calculator {
    /**
     * Signature every operator must have: two operands, one result.
     */
    public interface Operator {
        int apply(int lhs, int rhs);
    }

    /**
     * One calculator operation: the symbol shown in the result line, the menu
     * text, and the operator that computes it.
     */
    public record Operation(char symbol, String label, Operator operator) {
    }

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
            throw new ArithmeticException("No se puede dividir entre cero");
        }

        return lhs / rhs;
    }
}
