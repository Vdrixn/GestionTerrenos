package com.is.gestionterrenos.vista;
import javax.swing.*;

import com.is.gestionterrenos.dao.ArrendatarioDAO;
import com.is.gestionterrenos.dao.ConexionDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase principal que contiene el JFrame principal
public class GestionTerrenosApp {
    public static ImageIcon icono;
    public static boolean vistaArrenActiva = false;
    public static boolean vistaTerrenActiva = false;
    public static boolean vistaParcelaActiva = false;
    public static boolean vistaReciboActiva = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                icono = new ImageIcon("src/main/resources/icono.png");
                //Esta línea inicializa la conexión a la base de datos y evita un retardo que se producía
                //una vez iniciada la aplicación, al seleccionar una de las bases de datos.
                ArrendatarioDAO.conn=ConexionDB.getConn();
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
        
        JPanel northPannel=new JPanel(new BorderLayout());
        final JPanel homePannel=new JPanel(new BorderLayout());

        final JButton botonHome = new JButton("🏠");
        botonHome.setFont(new Font("Dialog", Font.BOLD, 20));
        botonHome.setForeground(Color.WHITE);
        botonHome.setBackground(Color.decode("#ff8c00"));
        botonHome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        northPannel.add(botonHome, BorderLayout.WEST);

        final JComboBox<String> comboBox = new JComboBox<>(opciones);
        northPannel.add(comboBox, BorderLayout.CENTER);

        final JButton botonRegistrarAlquiler = new JButton("Registrar Alquiler");
        botonRegistrarAlquiler.setPreferredSize(new Dimension(200, 80)); 
        botonRegistrarAlquiler.setFont(new Font("Arial", Font.BOLD, 16)); 
        botonRegistrarAlquiler.setForeground(Color.BLACK);
        botonRegistrarAlquiler.setBackground(Color.decode("#ff8c00"));
        botonRegistrarAlquiler.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // Crear un Box para centrar el botón en la parte inferior
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue()); // Espacio a la izquierda
        box.add(botonRegistrarAlquiler);
        box.add(Box.createHorizontalGlue()); // Espacio a la derecha

        homePannel.add(box,BorderLayout.CENTER);

        panel.add(northPannel,BorderLayout.NORTH);
        panel.add(homePannel,BorderLayout.CENTER);

        // Crear instancias de las vistas específicas
        final VistaArrendatarios vistaArrendatarios = new VistaArrendatarios();
        final VistaTerrenos vistaTerrenos = new VistaTerrenos();
        final VistaParcelas vistaParcelas = new VistaParcelas();
        final VistaRecibos vistaRecibos = new VistaRecibos();
        botonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaReciboActiva=false;
                        vistaArrenActiva=false;
                        vistaTerrenActiva = false;
                        vistaParcelaActiva = false;
                // Limpiar todos los paneles
                panel.remove(vistaArrendatarios.getPanel());
                panel.remove(vistaTerrenos.getPanel());
                panel.remove(vistaParcelas.getPanel());
                panel.remove(vistaRecibos.getPanel());

                // Establecer la selección del JComboBox en "Seleccione una opción"
                comboBox.setSelectedIndex(0);
                panel.add(homePannel,BorderLayout.CENTER);

                // Actualizar la interfaz gráfica
                panel.revalidate();
                panel.repaint();
            }
        });

        botonRegistrarAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: realizar el registro de alquiler de manera "mas sencilla e intuitiva" que tener que añadir un recibo nuevo
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBox.getSelectedItem();

                // Verificar la selección y actualizar la vista correspondiente
                if ("Arrendatarios".equals(seleccion)) {
                    // Mostrar la vista de arrendatarios
                    //Comprobamos que vista está activa para borrarla y actualizar la interfaz
                    if(vistaTerrenActiva){
                        panel.remove(vistaTerrenos.getPanel());
                        vistaTerrenActiva = false;
                    }
                    else if(vistaReciboActiva){
                        panel.remove(vistaRecibos.getPanel());
                        vistaReciboActiva = false;
                    }
                    else if(vistaParcelaActiva){
                        panel.remove(vistaParcelas.getPanel());
                        vistaParcelaActiva = false;
                    }else{
                        panel.remove(homePannel);
                    }

                    panel.add(vistaArrendatarios.getPanel(), BorderLayout.CENTER);
                    vistaArrendatarios.actualizar(null);
                    frame.revalidate();
                    vistaArrenActiva = true;
                } else if ("Terrenos".equals(seleccion)) {
                    // Mostrar la vista de terrenos

                    //Comprobamos que vista está activa para borrarla y actualizar la interfaz
                    if(vistaArrenActiva){
                        panel.remove(vistaArrendatarios.getPanel());
                        vistaArrenActiva = false;
                    }
                    else if(vistaReciboActiva){
                        panel.remove(vistaRecibos.getPanel());
                        vistaReciboActiva = false;
                    }
                    else if(vistaParcelaActiva){
                        panel.remove(vistaParcelas.getPanel());
                        vistaParcelaActiva = false;
                    }else{
                        
                        panel.remove(homePannel);
                    }
                    panel.add(vistaTerrenos.getPanel(), BorderLayout.CENTER);
                    vistaTerrenos.actualizar(null);
                    frame.revalidate();
                    vistaTerrenActiva = true;
                } else if("Parcelas".equals(seleccion)){
                    //mostrar vista parcelas 
                     if(vistaArrenActiva){
                        panel.remove(vistaArrendatarios.getPanel());
                        vistaArrenActiva = false;
                    }
                    else if(vistaTerrenActiva){
                        panel.remove(vistaTerrenos.getPanel());
                        vistaTerrenActiva = false;
                    }
                    else if(vistaReciboActiva){
                        panel.remove(vistaRecibos.getPanel());
                        vistaReciboActiva = false;
                    }else{
                        
                        panel.remove(homePannel);
                    }
                    panel.add(vistaParcelas.getPanel(), BorderLayout.CENTER);
                    vistaParcelas.actualizar(null);
                    frame.revalidate();
                    vistaParcelaActiva= true;

                }else if("Recibos".equals(seleccion)){
                    //mostrar vista parcelas 
                     if(vistaArrenActiva){
                        panel.remove(vistaArrendatarios.getPanel());
                        vistaArrenActiva = false;
                    }
                    else if(vistaTerrenActiva){
                        panel.remove(vistaTerrenos.getPanel());
                        vistaTerrenActiva = false;
                    }
                    else if(vistaParcelaActiva){
                        panel.remove(vistaParcelas.getPanel());
                        vistaParcelaActiva = false;
                    }else{
                        
                        panel.remove(homePannel);
                    }
                    panel.add(vistaRecibos.getPanel(), BorderLayout.CENTER);
                    vistaRecibos.actualizar(null);
                    frame.revalidate();
                    vistaReciboActiva= true;
                }
                else {
                    // Limpiar la vista si no es la opción de arrendatarios o terrenos
                    // vistaArrendatarios.limpiar();
                    panel.remove(vistaArrendatarios.getPanel());
                    panel.remove(vistaTerrenos.getPanel());
                    panel.remove(vistaParcelas.getPanel());
                    panel.remove(vistaRecibos.getPanel());
                    
                }

            }
        });

        frame.setLocationRelativeTo(null);
        frame.setIconImage(icono.getImage());
        frame.setVisible(true);
    }
}

// Clase VistaArrendatarios que contiene la lógica y la interfaz específica para los arrendatarios
