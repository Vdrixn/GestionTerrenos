package com.is.gestionterrenos.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.controlador.ControladorTerrenos;
import com.is.gestionterrenos.dao.TerrenoDAO;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.modelo.Terreno;

public class VistaTerrenos {

    private JPanel panel;
    private DefaultListModel<String> listModel;
    private JList<String> jList;

    public static String nombreTerreno;
    public static String ubicacionTerreno;
    public static String tamHectareasTerreno;
    public static String tipoTerreno;
    public static String limiteBaseTerreno;
    public static String limiteAlturaTerreno;
    public static String fechaTerreno;

    public static String terrenoActual;


    public VistaTerrenos() {
        panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panel.add(scrollPane, BorderLayout.CENTER);

        

        JPanel botonesPanel = new JPanel(new GridLayout(2, 1));
        JButton addButton = new JButton("Añadir");
        JButton actButton = new JButton("Actualizar");
        JButton buscarButton = new JButton("Buscar");
        JButton deleteButton = new JButton("Borrar");

         addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAñadir();
            }

            
         });

         actButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jList.getSelectedValue()!=null){
                    terrenoActual=jList.getSelectedValue();
                    abrirVentanaActualizar();
                }
            }
        });


        botonesPanel.add(addButton);
        botonesPanel.add(actButton);
        botonesPanel.add(buscarButton);
        botonesPanel.add(deleteButton);
        panel.add(botonesPanel, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }


    public void actualizar(ArrayList<Terreno> givenArrens) {
        // Limpiar la lista actual
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        ArrayList<Terreno> terrenos;
        if(givenArrens==null)
            terrenos=ControladorTerrenos.listar();
        else
            terrenos=givenArrens;

        //arrendatarios=...
        if (terrenos != null) {
            for (Terreno terreno : terrenos) {
                listModel.addElement(terreno.toString());
            }
        }
    }
        

    public void abrirVentanaAñadir(){

        final JFrame ventanaAñadir = new JFrame("Añadir Terreno");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(7, 2));

        panelAñadir.add(new JLabel("Nombre:"));
        final JTextField nombreField = new JTextField();
        panelAñadir.add(nombreField);

        panelAñadir.add(new JLabel("Ubicación:"));
        final JTextField ubicacionField = new JTextField();
        panelAñadir.add(ubicacionField);

        panelAñadir.add(new JLabel("Tam Hectáreas:"));
        final JTextField tamHectareasField = new JTextField();
        panelAñadir.add(tamHectareasField);

        panelAñadir.add(new JLabel("Tipo de Terreno:"));
        final JTextField tipoTerrenoField = new JTextField();
        panelAñadir.add(tipoTerrenoField);

        panelAñadir.add(new JLabel("Límite Base:"));
        final JTextField limiteBaseField = new JTextField();
        panelAñadir.add(limiteBaseField);

        panelAñadir.add(new JLabel("Límite Altura:"));
        final JTextField limiteAlturaField = new JTextField();
        panelAñadir.add(limiteAlturaField);

        panelAñadir.add(new JLabel("Fecha:"));
        final JTextField fechaField = new JTextField();
        panelAñadir.add(fechaField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreTerreno = nombreField.getText();
                ubicacionTerreno = ubicacionField.getText();
                tamHectareasTerreno = tamHectareasField.getText();
                tipoTerreno = tipoTerrenoField.getText();
                limiteBaseTerreno = limiteBaseField.getText();
                limiteAlturaTerreno = limiteAlturaField.getText();
                fechaTerreno = fechaField.getText();
                
                ControladorTerrenos.insertar();
                ventanaAñadir.dispose();
                actualizar(null);
            }
        });
        panelAñadir.add(guardarButton);

        ventanaAñadir.getContentPane().add(panelAñadir);
        ventanaAñadir.setVisible(true);
        
    }

    public void abrirVentanaActualizar() {
        final JFrame ventanaAñadir = new JFrame("Actualizar Terrenos");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(7, 2));

        panelAñadir.add(new JLabel("Nombre:"));
        final JTextField nombreField = new JTextField();
        panelAñadir.add(nombreField);

        panelAñadir.add(new JLabel("Ubicación:"));
        final JTextField ubicacionField = new JTextField();
        panelAñadir.add(ubicacionField);

        panelAñadir.add(new JLabel("Tam Hectáreas:"));
        final JTextField tamHectareasField = new JTextField();
        panelAñadir.add(tamHectareasField);

        panelAñadir.add(new JLabel("Tipo de Terreno:"));
        final JTextField tipoTerrenoField = new JTextField();
        panelAñadir.add(tipoTerrenoField);

        panelAñadir.add(new JLabel("Límite Base:"));
        final JTextField limiteBaseField = new JTextField();
        panelAñadir.add(limiteBaseField);

        panelAñadir.add(new JLabel("Límite Altura:"));
        final JTextField limiteAlturaField = new JTextField();
        panelAñadir.add(limiteAlturaField);

        panelAñadir.add(new JLabel("Fecha:"));
        final JTextField fechaField = new JTextField();
        panelAñadir.add(fechaField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                nombreTerreno = nombreField.getText();
                ubicacionTerreno = ubicacionField.getText();
                tamHectareasTerreno = tamHectareasField.getText();
                tipoTerreno = tipoTerrenoField.getText();
                limiteBaseTerreno = limiteBaseField.getText();
                limiteAlturaTerreno = limiteAlturaField.getText();
                fechaTerreno = fechaField.getText();

                ControladorTerrenos.actualizarTerreno(terrenoActual, nombreTerreno, ubicacionTerreno, tamHectareasTerreno, tipoTerreno, 
                                                limiteBaseTerreno, limiteAlturaTerreno, fechaTerreno);
                // Cerrar la ventana después de guardar
                ventanaAñadir.dispose();
                actualizar(null);
            }
        });

        panelAñadir.add(guardarButton);

        ventanaAñadir.getContentPane().add(panelAñadir);
        ventanaAñadir.setVisible(true);
    }

}

