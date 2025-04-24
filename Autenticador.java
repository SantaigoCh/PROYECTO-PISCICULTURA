/* clase para la autenticación de usuarios*/
package com.mycompany.proyectopiscicultura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Autenticador {

    public static String autenticar(String correo, String contrasena) {
        String sql = "SELECT rol FROM Usuario WHERE correo = ? AND contrasena = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String hashContrasena = HashUtil.sha256(contrasena);

            stmt.setString(1, correo);
            stmt.setString(2, hashContrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("rol"); // Retorna el rol si se autenticó correctamente
            } else {
                return null; // Falló la autenticación
            }

        } catch (Exception e) {
            System.err.println("Error al autenticar: " + e.getMessage());
            return null;
        }
    }
}
