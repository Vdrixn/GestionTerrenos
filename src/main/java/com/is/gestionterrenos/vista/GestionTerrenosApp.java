package com.is.gestionterrenos.vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

// Clase principal que contiene el JFrame principal
public class GestionTerrenosApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Gestión de Terrenos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        String[] opciones = {"Arrendatarios", "Terrenos", "Parcelas", "Recibos"};

        final JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox, BorderLayout.NORTH);

        // Crear instancias de las vistas específicas
        final VistaArrendatarios vistaArrendatarios = new VistaArrendatarios();

        // Agregar las vistas específicas al panel
        panel.add(vistaArrendatarios.getPanel(), BorderLayout.CENTER);

        

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBox.getSelectedItem();

                // Verificar la selección y actualizar la vista correspondiente
                if ("Arrendatarios".equals(seleccion)) {
                    vistaArrendatarios.actualizar(null);
                } else {
                    // Limpiar la vista si no es la opción de arrendatarios
                    // vistaArrendatarios.limpiar();
                }
            }
        });

        frame.setVisible(true);
    }
}

// Clase VistaArrendatarios que contiene la lógica y la interfaz específica para los arrendatarios
