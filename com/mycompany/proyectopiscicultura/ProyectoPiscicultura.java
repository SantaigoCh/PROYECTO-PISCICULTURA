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
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
<<<<<<< HEAD:com/mycompany/proyectopiscicultura/ProyectoPiscicultura.java
                case 1 -> MenuRegistro.mostrarMenuRegistroGUI();
                case 2 -> MenuLogin.mostrarMenuLoginGUI();
=======
                case 1 -> MenuRegistro.mostrarMenuRegistro();
                
                case 2 -> {
                    MenuLogin.mostrarMenuLogin();
                }
                
>>>>>>> c24ea9d2ff01467cc50c9a710c2a6373b54692dc:ProyectoPiscicultura.java
                case 3 -> System.out.println("¡Hasta luego!");

                default -> System.out.println("Opcion no válida.");
            }
        } while (opcion != 3);

        sc.close();
    }
}
