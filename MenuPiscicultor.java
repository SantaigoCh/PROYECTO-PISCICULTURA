package com.mycompany.proyectopiscicultura;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuPiscicultor {

    public static void mostrarMenuPiscicultor() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== Menu Piscicultor ===");
            System.out.println("1. Seleccionar area de interes");
            System.out.println("2. Consultar registros");
            System.out.println("3. Filtrar registros");
            System.out.println("4. Filtrar por ubicacion y fecha");
            System.out.println("5. Cerrar sesión");
            System.out.print("Ingrese una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    seleccionarAreaDeInteres();
                    break;
                case 2:
                    consultarRegistros();
                    break;
                case 3:
                    filtrarRegistros();
                    break;
                case 4:
                    filtrarPorUbicacionYFecha();
                    break;
                case 5:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 5);
    }

     private static void seleccionarAreaDeInteres() {
        System.out.println("\n--- Áreas de interés disponibles ---");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            String sql = "SELECT DISTINCT tipo FROM variable ORDER BY tipo";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            int contador = 1;
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                System.out.println(contador + ". " + tipo);
                contador++;
            }

            if (contador == 1) {
                System.out.println("No hay áreas de interés registradas.");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar áreas de interés: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

 private static void consultarRegistros() {
    System.out.println("\n--- Todos los registros disponibles ---");

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Conexion.getConexion();
        String sql = "SELECT r.id, v.nombre AS variable_nombre, r.valor, r.observacion, r.fecha " +
                     "FROM registro r " +
                     "JOIN variable v ON r.variable_id = v.id " +
                     "ORDER BY r.fecha DESC";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        boolean hayRegistros = false;
        while (rs.next()) {
            int id = rs.getInt("id");
            String variableNombre = rs.getString("variable_nombre");
            Double valorNumerico = rs.getObject("valor") != null ? rs.getDouble("valor") : null;
            String observacion = rs.getString("observacion");
            String fecha = rs.getString("fecha");

            System.out.println("ID: " + id);
            System.out.println("Variable: " + variableNombre);
            if (valorNumerico != null) {
                System.out.println("Valor: " + valorNumerico);
            }
            if (observacion != null && !observacion.isEmpty()) {
                System.out.println("Observación: " + observacion);
            }
            System.out.println("Fecha: " + fecha);
            System.out.println("---------------------------");

            hayRegistros = true;
        }

        if (!hayRegistros) {
            System.out.println("No hay registros en la base de datos.");
        }

    } catch (SQLException e) {
        System.out.println("Error al consultar registros: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}

    private static void filtrarRegistros() {
    System.out.println("\n--- Filtrar registros por tipo de variable ---");

    Scanner scanner = new Scanner(System.in);
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Conexion.getConexion();

        // Mostrar tipos disponibles
        String tiposSQL = "SELECT DISTINCT tipo FROM variable ORDER BY tipo";
        stmt = conn.prepareStatement(tiposSQL);
        rs = stmt.executeQuery();

        int contador = 1;
        List<String> tipos = new ArrayList<>();
        System.out.println("Tipos disponibles:");
        while (rs.next()) {
            String tipo = rs.getString("tipo");
            System.out.println(contador + ". " + tipo);
            tipos.add(tipo);
            contador++;
        }

        if (tipos.isEmpty()) {
            System.out.println("No hay tipos de variables registrados.");
            return;
        }

        System.out.print("Seleccione el número del tipo que desea consultar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (opcion < 1 || opcion > tipos.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        String tipoSeleccionado = tipos.get(opcion - 1);

        // Mostrar registros filtrados por tipo
        String registrosSQL = "SELECT r.id, v.nombre AS variable_nombre, r.valor, r.observacion, r.fecha " +
                              "FROM registro r " +
                              "JOIN variable v ON r.variable_id = v.id " +
                              "WHERE v.tipo = ? " +
                              "ORDER BY r.fecha DESC";
        stmt = conn.prepareStatement(registrosSQL);
        stmt.setString(1, tipoSeleccionado);
        rs = stmt.executeQuery();

        boolean hayRegistros = false;
        System.out.println("\n--- Registros del tipo: " + tipoSeleccionado + " ---");
        while (rs.next()) {
            int id = rs.getInt("id");
            String variableNombre = rs.getString("variable_nombre");
            Double valorNumerico = rs.getObject("valor") != null ? rs.getDouble("valor") : null;
            String observacion = rs.getString("observacion");
            String fecha = rs.getString("fecha");

            System.out.println("ID: " + id);
            System.out.println("Variable: " + variableNombre);
            if (valorNumerico != null) System.out.println("Valor: " + valorNumerico);
            if (observacion != null && !observacion.isEmpty()) System.out.println("Observación: " + observacion);
            System.out.println("Fecha: " + fecha);
            System.out.println("---------------------------");

            hayRegistros = true;
        }

        if (!hayRegistros) {
            System.out.println("No hay registros para este tipo.");
        }

    } catch (SQLException e) {
        System.out.println("Error al filtrar registros: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}
    
    private static void filtrarPorUbicacionYFecha() {
    Scanner scanner = new Scanner(System.in);
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Conexion.getConexion();

        // Mostrar departamentos disponibles
        String departamentosSQL = "SELECT DISTINCT d.nombre " +
                                  "FROM departamento d " +
                                  "JOIN municipio m ON m.departamento_id = d.id " +
                                  "JOIN registro r ON r.municipio_id = m.id " +
                                  "ORDER BY d.nombre";
        stmt = conn.prepareStatement(departamentosSQL);
        rs = stmt.executeQuery();

        List<String> departamentos = new ArrayList<>();
        System.out.println("\nDepartamentos disponibles:");
        int i = 1;
        while (rs.next()) {
            String dep = rs.getString("nombre");
            departamentos.add(dep);
            System.out.println(i + ". " + dep);
            i++;
        }

        if (departamentos.isEmpty()) {
            System.out.println("No hay datos registrados aún.");
            return;
        }

        System.out.print("Seleccione el número del departamento: ");
        int opcionDep = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (opcionDep < 1 || opcionDep > departamentos.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        String departamentoSeleccionado = departamentos.get(opcionDep - 1);

        // Mostrar municipios del departamento seleccionado
        String municipiosSQL = "SELECT DISTINCT m.nombre " +
                                "FROM municipio m " +
                                "JOIN registro r ON r.municipio_id = m.id " +
                                "JOIN departamento d ON m.departamento_id = d.id " +
                                "WHERE d.nombre = ? " +
                                "ORDER BY m.nombre";
        stmt = conn.prepareStatement(municipiosSQL);
        stmt.setString(1, departamentoSeleccionado);
        rs = stmt.executeQuery();

        List<String> municipios = new ArrayList<>();
        System.out.println("\nMunicipios disponibles en " + departamentoSeleccionado + ":");
        i = 1;
        while (rs.next()) {
            String muni = rs.getString("nombre");
            municipios.add(muni);
            System.out.println(i + ". " + muni);
            i++;
        }

        if (municipios.isEmpty()) {
            System.out.println("No hay municipios registrados aún en este departamento.");
            return;
        }

        System.out.print("Seleccione el número del municipio: ");
        int opcionMuni = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (opcionMuni < 1 || opcionMuni > municipios.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        String municipioSeleccionado = municipios.get(opcionMuni - 1);

        // Preguntar si desea filtrar por fecha
        System.out.print("¿Desea filtrar por rango de fechas? (s/n): ");
        String respuesta = scanner.nextLine();

        String fechaInicio = null, fechaFin = null;
        boolean usarFecha = respuesta.equalsIgnoreCase("s");

        if (usarFecha) {
            System.out.print("Ingrese fecha de inicio (YYYY-MM-DD): ");
            fechaInicio = scanner.nextLine();

            System.out.print("Ingrese fecha de fin (YYYY-MM-DD): ");
            fechaFin = scanner.nextLine();
        }

        // Consulta final
        String sql = "SELECT r.id, v.nombre AS variable_nombre, r.valor, r.observacion, r.fecha " +
                     "FROM registro r " +
                     "JOIN variable v ON r.variable_id = v.id " +
                     "JOIN municipio m ON r.municipio_id = m.id " +
                     "JOIN departamento d ON m.departamento_id = d.id " +
                     "WHERE d.nombre = ? AND m.nombre = ?";

        if (usarFecha) {
            sql += " AND r.fecha BETWEEN ? AND ?";
        }

        sql += " ORDER BY r.fecha DESC";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, departamentoSeleccionado);
        stmt.setString(2, municipioSeleccionado);
        if (usarFecha) {
            stmt.setString(3, fechaInicio);
            stmt.setString(4, fechaFin);
        }

        rs = stmt.executeQuery();

        System.out.println("\n--- Registros encontrados ---");
        boolean hayRegistros = false;
        while (rs.next()) {
            int id = rs.getInt("id");
            String variable = rs.getString("variable_nombre");
            Double valor = rs.getObject("valor") != null ? rs.getDouble("valor") : null;
            String observacion = rs.getString("observacion");
            String fecha = rs.getString("fecha");

            System.out.println("ID: " + id);
            System.out.println("Variable: " + variable);
            if (valor != null) System.out.println("Valor: " + valor);
            if (observacion != null && !observacion.isEmpty()) System.out.println("Observación: " + observacion);
            System.out.println("Fecha: " + fecha);
            System.out.println("------------------------------");

            hayRegistros = true;
        }

        if (!hayRegistros) {
            System.out.println("No se encontraron registros con esos filtros.");
        }

    } catch (SQLException e) {
        System.out.println("Error al consultar registros: " + e.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}
    
}

