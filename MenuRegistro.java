/*Menu de registro por consola para usuarios piscicultores*/
package com.mycompany.proyectopiscicultura;

import java.util.Scanner;
public class MenuRegistro {

    public static void mostrarMenuRegistro() {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Registro de Usuario ====");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        boolean registrado = RegistradorUsuario.registrar(nombre, correo, contrasena);

        if (registrado) {
            System.out.println("✅ Usuario registrado con éxito.");
        } else {
            System.out.println("❌ Error al registrar el usuario.");
        }
    }
}
