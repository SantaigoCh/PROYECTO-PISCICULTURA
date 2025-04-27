package com.mycompany.proyectopiscicultura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/Repositorio Piscicultura"; /* Aquí debe ir el nombre de la base de datos creada en el servidor*/
    private static final String USER = "santiagoh"; /* Aqui debe ir el usuario del servidor de la base de datos*/
    private static final String PASSWORD = "30083591";  /* Aqui debe ir la contraseña del servidor de la base de datos*/

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
