/*Menu de login para usuarios admin/piscicultores */
package com.mycompany.proyectopiscicultura;

import java.util.Scanner;

public class MenuLogin {

    public static void mostrarMenuLogin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Inicio de SesiÃ³n ====");

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("ContraseÃ±a: ");
        String contrasena = sc.nextLine();

        String rol = Autenticador.autenticar(correo, contrasena);

       if (rol != null) {
                System.out.println(" Autenticacion exitosa. Rol: " + rol);

                if (rol.equalsIgnoreCase("administrador")) {
                    MenuAdministrador.mostrarMenu();
                } else if (rol.equalsIgnoreCase("piscicultor")) {
                    MenuPiscicultor.mostrarMenuPiscicultor();
                } else {
                    System.out.println("ï¸ Rol desconocido, acceso denegado.");
                        }

                    } else {
                        System.out.println(" Correo o contraseÃ±a incorrectos.");
                    }
    }
}
