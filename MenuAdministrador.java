/*Menu para el usuario administrador o super usuario donde se podran gestionar usuarios y demás configuraciones futuras.*/

package com.mycompany.proyectopiscicultura;


import java.sql.*;
import java.util.Scanner;

public class MenuAdministrador {

    public static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==== Menu Administrador ====");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Ver reportes");
            System.out.println("3. Configuraciones");
            System.out.println("4. Integrar datos externos"); 
            System.out.println("5. Cerrar sesion");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

        switch (opcion) {
            case 1 -> gestionarUsuarios();
            case 2 -> System.out.println("Funcionalidad de reportes (por implementar)");
            case 3 -> System.out.println("Funcionalidad de configuraciones (por implementar)");
            case 4 -> {
                System.out.println("\n Integrando datos desde fuente externa...");
                IntegradorDatosExternos.integrarCapturaPesquera2020();
                System.out.println("\n Integración de datos completada exitosamente.\n");
            }
            case 5 -> System.out.println("Cerrando sesión...");
            default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void gestionarUsuarios() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==== Gestion de Usuarios ====");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Modificar datos de usuario");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> listarUsuarios();
                case 2 -> eliminarUsuario();
                case 3 -> modificarUsuario();
                case 4 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void listarUsuarios() {
        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nombre, correo, rol FROM usuario ORDER BY id")) {

            System.out.println("\nListado de Usuarios:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Rol: " + rs.getString("rol"));
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
    }

    private static void eliminarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuario WHERE id = ?")) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado exitosamente.");
            } else {
                System.out.println("No se encontro un usuario con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    private static void modificarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del usuario a modificar: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Nuevo correo: ");
        String nuevoCorreo = sc.nextLine();

        System.out.print("Nueva contrasena (se almacenara en SHA-256): ");
        String nuevaContrasena = sc.nextLine();
        String contrasenaHasheada = HashUtil.sha256(nuevaContrasena);

        System.out.print("Nuevo rol (administrador/piscicultor): ");
        String nuevoRol = sc.nextLine();

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE usuario SET nombre = ?, correo = ?, contrasena = ?, rol = ? WHERE id = ?")) {

            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevoCorreo);
            stmt.setString(3, contrasenaHasheada);
            stmt.setString(4, nuevoRol);
            stmt.setInt(5, id);

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario modificado exitosamente.");
            } else {
                System.out.println("No se encontro un usuario con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar usuario: " + e.getMessage());
        }
    }
}
