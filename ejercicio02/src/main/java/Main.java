/*
 * ejercicio02/src/main/java/Main.java
 *
 * Entry point for ejercicio02.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Hola, " + nombre);
    }
}
