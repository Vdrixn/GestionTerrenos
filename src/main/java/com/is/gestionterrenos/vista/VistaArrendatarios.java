package com.is.gestionterrenos.vista;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.modelo.Arrendatario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

public class VistaArrendatarios {
    private JPanel panel;
    private DefaultListModel<String> listModel;
    private JList<String> jList;

    public static String dniActual;
    public static String nombreActual;
    public static String edadActual;
    public static String sexoActual;

    private static String arrendatarioActual;


    public  VistaArrendatarios() {
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
                // TODO Auto-generated method stub
                abrirVentanaAñadir();
            }

            
         });

         actButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jList.getSelectedValue()!=null){
                    arrendatarioActual=jList.getSelectedValue();
                    abrirVentanaActualizar();
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaBuscar();
                
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jList.getSelectedValue()!=null){
                    arrendatarioActual=jList.getSelectedValue();
                    ejecutarBorrado();
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

    public void actualizar(ArrayList<Arrendatario> givenArrens) {
        // Limpiar la lista actual
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        ArrayList<Arrendatario> arrendatarios;
        if(givenArrens==null)
            arrendatarios=ControladorArrendatarios.listar();
        else
            arrendatarios=givenArrens;
        
        //arrendatarios=...
        if (arrendatarios != null) {
            for (Arrendatario arrendatario : arrendatarios) {
                listModel.addElement(arrendatario.toString());
            }
        }
    }

    private void abrirVentanaAñadir() {
        final JFrame ventanaAñadir = new JFrame("Añadir Arrendatario");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("DNI:"));
        final JTextField dniField = new JTextField();
        panelAñadir.add(dniField);

        panelAñadir.add(new JLabel("Nombre:"));
        final JTextField nombreField = new JTextField();
        panelAñadir.add(nombreField);

        panelAñadir.add(new JLabel("Edad:"));
        final JTextField edadField = new JTextField();
        panelAñadir.add(edadField);

        panelAñadir.add(new JLabel("Sexo:"));
        final JTextField sexoField = new JTextField();
        panelAñadir.add(sexoField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                 dniActual = dniField.getText();
                nombreActual = nombreField.getText();
                edadActual = edadField.getText();
                sexoActual = sexoField.getText();

                ControladorArrendatarios.insertar();
                // Cerrar la ventana después de guardar
                ventanaAñadir.dispose();
                actualizar(null);
            }
        }); 

        panelAñadir.add(guardarButton);

        ventanaAñadir.getContentPane().add(panelAñadir);
        ventanaAñadir.setVisible(true);
    }

    private void abrirVentanaActualizar() {
        final JFrame ventanaAñadir = new JFrame("Actualizar Arrendatario");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("DNI:"));
        final JTextField dniField = new JTextField();
        panelAñadir.add(dniField);

        panelAñadir.add(new JLabel("Nombre:"));
        final JTextField nombreField = new JTextField();
        panelAñadir.add(nombreField);

        panelAñadir.add(new JLabel("Edad:"));
        final JTextField edadField = new JTextField();
        panelAñadir.add(edadField);

        panelAñadir.add(new JLabel("Sexo:"));
        final JTextField sexoField = new JTextField();
        panelAñadir.add(sexoField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                 dniActual = dniField.getText();
                 if (!dniActual.matches("\\d{8}[a-zA-Z]") && !dniActual.equals("")) { //O el campo está vacío, o esta en formato correcto

                    JOptionPane.showMessageDialog(ventanaAñadir, "DNI inválido. Debe ser un string de 8 letras.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener el proceso si el DNI no es válido
                }
                nombreActual = nombreField.getText();
                edadActual = edadField.getText();
                sexoActual = sexoField.getText();

                ControladorArrendatarios.actualizar(arrendatarioActual,dniActual,nombreActual,edadActual,sexoActual);
                // Cerrar la ventana después de guardar
                ventanaAñadir.dispose();
                actualizar(null);
            }
        });

        panelAñadir.add(guardarButton);

        ventanaAñadir.getContentPane().add(panelAñadir);
        ventanaAñadir.setVisible(true);
    }

    public void abrirVentanaBuscar(){
        final JFrame ventanaAñadir = new JFrame("Buscar Arrendatarios");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("DNI:"));
        final JTextField dniField = new JTextField();
        panelAñadir.add(dniField);

        panelAñadir.add(new JLabel("Nombre:"));
        final JTextField nombreField = new JTextField();
        panelAñadir.add(nombreField);

        panelAñadir.add(new JLabel("Edad:"));
        final JTextField edadField = new JTextField();
        panelAñadir.add(edadField);

        panelAñadir.add(new JLabel("Sexo:"));
        final JTextField sexoField = new JTextField();
        panelAñadir.add(sexoField);

        JButton guardarButton = new JButton("Buscar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                 dniActual = dniField.getText();
                 if (!dniActual.matches("\\d{8}[a-zA-Z]") && !dniActual.equals("")) { //O el campo está vacío, o esta en formato correcto

                    JOptionPane.showMessageDialog(ventanaAñadir, "DNI inválido. Debe ser un string de 8 letras.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener el proceso si el DNI no es válido
                }
                nombreActual = nombreField.getText();
                edadActual = edadField.getText();
                sexoActual = sexoField.getText();

                ArrayList<Arrendatario> arrens=ControladorArrendatarios.buscar(dniActual,nombreActual,edadActual,sexoActual);
                // Cerrar la ventana después de guardar
                ventanaAñadir.dispose();
                actualizar(arrens);
            }
        });

        panelAñadir.add(guardarButton);

        ventanaAñadir.getContentPane().add(panelAñadir);
        ventanaAñadir.setVisible(true);
    }

    public  void ejecutarBorrado(){
        ControladorArrendatarios.borrar(arrendatarioActual);
        actualizar(null);
    }
 
}
