package com.is.gestionterrenos.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.is.gestionterrenos.controlador.ControladorRecibos;
import com.is.gestionterrenos.modelo.Recibo;

public class VistaRecibos {
    private JPanel panel;
    private DefaultListModel<String> listModel;
    private JList<String> jList;
    ImageIcon icono;

    // Variables para manejar los datos actuales
    public static int idReciboActual;
    public static int idParcelaActual;
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
        JButton imprButton = new JButton("Imprimir recibo");
        JButton actButton = new JButton("Actualizar");
        JButton buscarButton = new JButton("Buscar");
        JButton deleteButton = new JButton("Borrar");

        imprButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jList.getSelectedValue() != null) {
                    reciboActual = jList.getSelectedValue();
                    imprimir();
                }
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

        botonesPanel.add(imprButton);
        botonesPanel.add(actButton);
        botonesPanel.add(buscarButton);
        botonesPanel.add(deleteButton);
        panel.add(botonesPanel, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void actualizar(ArrayList<Recibo> nuevosRecibos) {
        listModel.clear();
        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        ArrayList<Recibo> recibos;
        if (nuevosRecibos == null)
            recibos = ControladorRecibos.listar();
        else
            recibos = nuevosRecibos;

        // recibos=...
        if (recibos != null) {
            for (Recibo recibo : recibos) {
                listModel.addElement(recibo.toString());
            }
        }
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
                idReciboActual = 0;
                idParcelaActual = 0;
                importeActual = 0;
                ivaActual = 0;
                irpfActual = 0;

                if (!idArrenField.getText().equals(""))
                    idReciboActual = Integer.parseInt(idArrenField.getText());

                if (!idParcelaField.getText().equals(""))
                    idParcelaActual = Integer.parseInt(idParcelaField.getText());
                if (!importeField.getText().equals(""))
                    importeActual = Double.parseDouble(importeField.getText());

                if (!ivaField.getText().equals(""))
                    ivaActual = Double.parseDouble(ivaField.getText());

                if (!irpfField.getText().equals(""))
                    irpfActual = Double.parseDouble(irpfField.getText());

                ControladorRecibos.actualizar(reciboActual, idReciboActual, idParcelaActual, importeActual, ivaActual,
                        irpfActual);
                ventanaActualizar.dispose();
                actualizar(null);
            }
        });

        final JPanel panelBoton = new JPanel(new GridLayout(1, 1));
        panelBoton.add(guardarButton);

        ventanaActualizar.getContentPane().add(panelAñadir);
        ventanaActualizar.getContentPane().add(panelBoton, BorderLayout.SOUTH);
        ventanaActualizar.setLocationRelativeTo(null);
        ventanaActualizar.setIconImage(icono.getImage());
        ventanaActualizar.setVisible(true);
    }

    public void abrirVentanaBuscar() {
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
                idReciboActual = 0;
                idParcelaActual = 0;
                importeActual = 0;
                ivaActual = 0;
                irpfActual = 0;

                if (!idArrenField.getText().equals(""))
                    idReciboActual = Integer.parseInt(idArrenField.getText());

                if (!idParcelaField.getText().equals(""))
                    idParcelaActual = Integer.parseInt(idParcelaField.getText());
                if (!importeField.getText().equals(""))
                    importeActual = Double.parseDouble(importeField.getText());

                if (!ivaField.getText().equals(""))
                    ivaActual = Double.parseDouble(ivaField.getText());

                if (!irpfField.getText().equals(""))
                    irpfActual = Double.parseDouble(irpfField.getText());

                ArrayList<Recibo> recibos = ControladorRecibos.buscar(idReciboActual, idParcelaActual, importeActual,
                        ivaActual, irpfActual, 0);
                ventanaBuscar.dispose();
                actualizar(recibos);
            }
        });

        final JPanel panelBoton = new JPanel(new GridLayout(1, 1));
        panelBoton.add(guardarButton);

        ventanaBuscar.getContentPane().add(panelAñadir);
        ventanaBuscar.getContentPane().add(panelBoton, BorderLayout.SOUTH);
        ventanaBuscar.setLocationRelativeTo(null);
        ventanaBuscar.setIconImage(icono.getImage());
        ventanaBuscar.setVisible(true);
    }

    public void imprimir() {
        ControladorRecibos.imprimir(reciboActual);
    }

    public void ejecutarBorrado() {
        ControladorRecibos.borrar(reciboActual);
        actualizar(null);
    }
}
