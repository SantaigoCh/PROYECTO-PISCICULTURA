/* Este será el main del proyecto de piscicultura trabajado en Java, La carpeta principal del proyecto tiene el nombre de ProyectoPiscicultura,
el motor de bases de datos trabajado será PostgreSQL. */

package com.mycompany.proyectopiscicultura;

import java.util.Scanner;

public class ProyectoPiscicultura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==== Sistema de Piscicultura ====");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> MenuRegistro.mostrarMenuRegistroGUI();
                case 2 -> MenuLogin.mostrarMenuLoginGUI();
                case 3 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }
}
