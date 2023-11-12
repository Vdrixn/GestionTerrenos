package com.is.gestionterrenos.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Date;

import com.is.gestionterrenos.controlador.ControladorRecibos;
import com.is.gestionterrenos.modelo.Recibo;

public class VistaRecibos {
    private JPanel panel;
    private DefaultListModel<String> listModel;
    private JList<String> jList;
    ImageIcon icono;

    // Variables para manejar los datos actuales
    public static int idArrendatarioActual;
    public static int idArrendatarioActual;
    public static double importeActual;
    public static double ivaActual;
    public static double irpfActual;

    private static String reciboActual;

    public VistaRecibos() {
        panel = new JPanel(new BorderLayout());
        icono = new ImageIcon("src/main/resources/icono.png");
        listModel = new DefaultListModel<>();
        jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel(new GridLayout(2, 2));
        JButton addButton = new JButton("Añadir");
        JButton actButton = new JButton("Actualizar");
        JButton buscarButton = new JButton("Buscar");
        JButton deleteButton = new JButton("Borrar");

        // Listeners para los botones
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAñadir();
            }
        });

        actButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jList.getSelectedValue() != null) {
                    reciboActual = jList.getSelectedValue();
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
                if (jList.getSelectedValue() != null) {
                    reciboActual = jList.getSelectedValue();
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

    public void actualizar(ArrayList<Recibo> nuevosRecibos) {
        // Limpiar la lista actual
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        ArrayList<Recibo> recibos;
        if(nuevosRecibos==null)
            recibos=ControladorRecibos.listar();
        else
            recibos=nuevosRecibos;
        
        //recibos=...
        if (recibos != null) {
            for (Recibo recibo : recibos) {
                listModel.addElement(recibo.toString());
            }
        }
    }

    private void abrirVentanaAñadir() {
        final JFrame ventanaAñadir = new JFrame("Añadir Recibo");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("ID Arrendatario:"));
        final JTextField idArrenField = new JTextField();
        panelAñadir.add(idArrenField);

        panelAñadir.add(new JLabel("ID Parcela:"));
        final JTextField idParcelaField = new JTextField();
        panelAñadir.add(idParcelaField);

        panelAñadir.add(new JLabel("Importe:"));
        final JTextField importeField = new JTextField();
        panelAñadir.add(importeField);

        panelAñadir.add(new JLabel("IVA:"));
        final JTextField ivaField = new JTextField();
        panelAñadir.add(ivaField);

        panelAñadir.add(new JLabel("IRPF:"));
        final JTextField irpfField = new JTextField();
        panelAñadir.add(irpfField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                idArrendatarioActual = Integer.parseInt(idArrenField.getText());
                idParcelaActual = Integer.parseInt(idParcelaField.getText());
                importeActual = importeField.getText();
                ivaActual = ivaField.getText();
                irpfActual = irpfField.getText();

                ControladorRecibos.insertar();
                // Cerrar la ventana después de guardar
                ventanaAñadir.dispose();
                actualizar(null);
            }
        }); 

        final JPanel panelBoton = new JPanel(new GridLayout(1,1));
        panelBoton.add(guardarButton);
        

        ventanaAñadir.getContentPane().add(panelAñadir);
        ventanaAñadir.getContentPane().add(panelBoton,BorderLayout.SOUTH);
        ventanaAñadir.setLocationRelativeTo(null); 
        ventanaAñadir.setIconImage(icono.getImage());
        ventanaAñadir.setVisible(true);
    }

    private void abrirVentanaActualizar() {
        final JFrame ventanaActualizar = new JFrame("Actualizar Recibo");
        ventanaActualizar.setSize(300, 200);
        ventanaActualizar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("ID Arrendatario:"));
        final JTextField idArrenField = new JTextField();
        panelAñadir.add(idArrenField);

        panelAñadir.add(new JLabel("ID Parcela:"));
        final JTextField idParcelaField = new JTextField();
        panelAñadir.add(idParcelaField);

        panelAñadir.add(new JLabel("Importe:"));
        final JTextField importeField = new JTextField();
        panelAñadir.add(importeField);

        panelAñadir.add(new JLabel("IVA:"));
        final JTextField ivaField = new JTextField();
        panelAñadir.add(ivaField);

        panelAñadir.add(new JLabel("IRPF:"));
        final JTextField irpfField = new JTextField();
        panelAñadir.add(irpfField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                idArrendatarioActual = Integer.parseInt(idArrenField.getText());
                idParcelaActual = Integer.parseInt(idParcelaField.getText());
                importeActual = importeField.getText();
                ivaActual = ivaField.getText();
                irpfActual = irpfField.getText();

                ControladorRecibos.actualizar(idArrendatarioActual,idParcelaActual,importeActual,ivaActual,irpfActual);
                // Cerrar la ventana después de guardar
                ventanaAñadir.dispose();
                actualizar(null);
            }
        });

        final JPanel panelBoton = new JPanel(new GridLayout(1,1));
        panelBoton.add(guardarButton);
        

        ventanaActualizar.getContentPane().add(panelAñadir);
        ventanaActualizar.getContentPane().add(panelBoton,BorderLayout.SOUTH);
        ventanaActualizar.setLocationRelativeTo(null); 
        ventanaActualizar.setIconImage(icono.getImage());
        ventanaActualizar.setVisible(true);
    }

    public void abrirVentanaBuscar(){
        final JFrame ventanaBuscar = new JFrame("Buscar Recibos");
        ventanaBuscar.setSize(300, 200);
        ventanaBuscar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("ID Arrendatario:"));
        final JTextField idArrenField = new JTextField();
        panelAñadir.add(idArrenField);

        panelAñadir.add(new JLabel("ID Parcela:"));
        final JTextField idParcelaField = new JTextField();
        panelAñadir.add(idParcelaField);

        panelAñadir.add(new JLabel("Importe:"));
        final JTextField importeField = new JTextField();
        panelAñadir.add(importeField);

        panelAñadir.add(new JLabel("IVA:"));
        final JTextField ivaField = new JTextField();
        panelAñadir.add(ivaField);

        panelAñadir.add(new JLabel("IRPF:"));
        final JTextField irpfField = new JTextField();
        panelAñadir.add(irpfField);

        JButton guardarButton = new JButton("Buscar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                idArrendatarioActual = Integer.parseInt(idArrenField.getText());
                idParcelaActual = Integer.parseInt(idParcelaField.getText());
                importeActual = importeField.getText();
                ivaActual = ivaField.getText();
                irpfActual = irpfField.getText();

                ArrayList<Arrendatario> arrens=ControladorRecibos.buscar(idArrendatarioActual,idParcelaActual,importeActual,ivaActual,irpfActual);
                // Cerrar la ventana después de guardar
                ventanaBuscar.dispose();
                actualizar(arrens);
            }
        });

        final JPanel panelBoton = new JPanel(new GridLayout(1,1));
        panelBoton.add(guardarButton);
        

        ventanaBuscar.getContentPane().add(panelAñadir);
        ventanaBuscar.getContentPane().add(panelBoton,BorderLayout.SOUTH);
        ventanaBuscar.setLocationRelativeTo(null); 
        ventanaBuscar.setIconImage(icono.getImage());
        ventanaBuscar.setVisible(true);
    }

    public  void ejecutarBorrado(){
        ControladorRecibos.borrar(arrendatarioActual);
        actualizar(null);
    }
}
