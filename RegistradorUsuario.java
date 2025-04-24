/*Clase para registrar al usuario piscicultor*/
package com.mycompany.proyectopiscicultura;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistradorUsuario {

    public static boolean registrar(String nombre, String correo, String contrasena) {
        String sql = "INSERT INTO Usuario (nombre, correo, contrasena, rol) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                       
            String contrasenaHasheada = HashUtil.sha256(contrasena);

            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setString(3, contrasenaHasheada); 
            stmt.setString(4, "piscicultor"); 

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}
