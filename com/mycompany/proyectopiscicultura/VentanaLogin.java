package com.mycompany.proyectopiscicultura;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaLogin extends JFrame {

    public VentanaLogin() {
        System.out.println(System.getProperty("user.dir"));

        setTitle("PSICONSULTA");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        // Panel principal con GridLayout para dividir en 2 partes
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));

        // Panel izquierdo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(173, 216, 230)); // Azul claro
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

        // Título
        JLabel lblTitulo = new JLabel("PSICONSULTA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botones
        JButton btnIniciarSesion = new JButton("INICIAR SESION");
        btnIniciarSesion.addActionListener(e -> {
            MenuLogin.mostrarMenuLoginGUI(); // Llama a la versión GUI
        });
        JButton btnRegistrarse = new JButton("REGISTRARSE");
        btnRegistrarse.addActionListener(e -> {
            MenuRegistro.mostrarMenuRegistroGUI(); // Usará tu RegistradorUsuario
        });

        // Estilo botones
        Color rojo = new Color(240, 80, 80);
        btnIniciarSesion.setBackground(rojo);
        btnRegistrarse.setBackground(rojo);
        btnIniciarSesion.setForeground(Color.WHITE);
        btnRegistrarse.setForeground(Color.WHITE);
        btnIniciarSesion.setFocusPainted(false);
        btnRegistrarse.setFocusPainted(false);

        // Tamaño y alineación botones
        btnIniciarSesion.setMaximumSize(new Dimension(200, 50));
        btnRegistrarse.setMaximumSize(new Dimension(200, 50));
        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espaciado
        panelIzquierdo.add(Box.createVerticalGlue());
        panelIzquierdo.add(lblTitulo);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 30)));
        panelIzquierdo.add(btnIniciarSesion);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));
        panelIzquierdo.add(btnRegistrarse);
        panelIzquierdo.add(Box.createVerticalGlue());

        
        // Panel derecho con imagen (usando getResource)
        JLabel lblImagen = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/assets/loginpsicultura.jpg"));
            lblImagen.setIcon(icon);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            lblImagen.setText("Imagen no encontrada");
        }
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);




        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);

        panelPrincipal.add(panelIzquierdo);
        panelPrincipal.add(lblImagen);

        // Agregar al frame
        add(panelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}
