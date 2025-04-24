/*Menu de login para usuarios admin/piscicultores */
package com.mycompany.proyectopiscicultura;

import java.util.Scanner;

public class MenuLogin {

    public static void mostrarMenuLogin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Inicio de Sesión ====");

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        String rol = Autenticador.autenticar(correo, contrasena);

        if (rol != null) {
            System.out.println("✅ Autenticación exitosa. Rol: " + rol);

            if (rol.equalsIgnoreCase("administrador")) {
                System.out.println("Accediendo como administrador...");
                
            } else if (rol.equalsIgnoreCase("piscicultor")) {
                System.out.println("Accediendo como piscicultor...");
              
            }

        } else {
            System.out.println("❌ Correo o contraseña incorrectos.");
        }
    }
}
