package com.is.gestionterrenos.vista;

import javax.swing.*;

import com.is.gestionterrenos.modelo.Arrendatario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class VistaArrendatarios {
    private JPanel panel;
    private DefaultListModel<String> listModel;
    private JList<String> jList;

    public VistaArrendatarios() {
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

        botonesPanel.add(addButton);
        botonesPanel.add(actButton);
        botonesPanel.add(buscarButton);
        botonesPanel.add(deleteButton);
        panel.add(botonesPanel, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void actualizar() {
        // Limpiar la lista actual
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        Arrendatario[] arrendatarios=null;
        //arrendatarios=...
        if (arrendatarios != null) {
            for (Arrendatario arrendatario : arrendatarios) {
                listModel.addElement(arrendatario.toString());
            }
        }
    }

 
}
