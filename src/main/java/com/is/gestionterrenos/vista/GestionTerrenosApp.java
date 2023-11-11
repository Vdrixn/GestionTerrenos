package com.is.gestionterrenos.vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

// Clase principal que contiene el JFrame principal
public class GestionTerrenosApp {
    public static ImageIcon icono;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                icono = new ImageIcon("src/main/resources/icono.png");
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Gestión de Terrenos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);

        final JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        String[] opciones = {"Seleccione una opción","Arrendatarios", "Terrenos", "Parcelas", "Recibos"};

        final JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox, BorderLayout.NORTH);

        // Crear instancias de las vistas específicas
        final VistaArrendatarios vistaArrendatarios = new VistaArrendatarios();
        final VistaTerrenos vistaTerrenos = new VistaTerrenos();

        

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBox.getSelectedItem();

                // Verificar la selección y actualizar la vista correspondiente
                if ("Arrendatarios".equals(seleccion)) {
                    // Mostrar la vista de arrendatarios
                    panel.remove(vistaTerrenos.getPanel());
                    panel.add(vistaArrendatarios.getPanel(), BorderLayout.CENTER);
                    vistaArrendatarios.actualizar();
                    frame.revalidate();
                } else if ("Terrenos".equals(seleccion)) {
                    // Mostrar la vista de terrenos
                    panel.remove(vistaArrendatarios.getPanel());
                    panel.add(vistaTerrenos.getPanel(), BorderLayout.CENTER);
                    vistaTerrenos.actualizar();
                    frame.revalidate();
                } else {
                    // Limpiar la vista si no es la opción de arrendatarios o terrenos
                    // vistaArrendatarios.limpiar();
                    panel.remove(vistaArrendatarios.getPanel());
                    panel.remove(vistaTerrenos.getPanel());
                }

            }
        });

        frame.setLocationRelativeTo(null);
        frame.setIconImage(icono.getImage());
        frame.setVisible(true);
    }
}

// Clase VistaArrendatarios que contiene la lógica y la interfaz específica para los arrendatarios
