package com.mycompany.proyectopiscicultura;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.*;

public class MenuLogin {

    public static void mostrarMenuLoginGUI() {
        // Crear un panel con campos de texto
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField txtCorreo = new JTextField();
        JPasswordField txtContrasena = new JPasswordField();

        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);

        int opcion = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            "Inicio de Sesión", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());
            String rol = Autenticador.autenticar(correo, contrasena);

            if (rol != null) {
                JOptionPane.showMessageDialog(
                    null, 
                    "✅ Autenticación exitosa. Rol: " + rol, 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                if (rol.equalsIgnoreCase("administrador")) {
                    // Abrir ventana de administrador
                } else if (rol.equalsIgnoreCase("piscicultor")) {
                    // Abrir ventana de piscicultor
                }
            } else {
                JOptionPane.showMessageDialog(
                    null, 
                    "❌ Correo o contraseña incorrectos.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}