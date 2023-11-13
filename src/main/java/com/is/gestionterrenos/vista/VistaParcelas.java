package com.is.gestionterrenos.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.controlador.ControladorParcelas;
import com.is.gestionterrenos.controlador.ControladorTerrenos;
import com.is.gestionterrenos.dao.TerrenoDAO;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.modelo.Parcela;
import com.is.gestionterrenos.modelo.Terreno;

public class VistaParcelas {
    private JPanel panel;
    private DefaultListModel<String> listModel;
    private JList<String> jList;
    ImageIcon icono;

    public static String IdTerrenoasociadoActual;
    public static String ubicacionActual;
    public static String limiteBaseActual;
    public static String limiteAlturaActual;



    private static String ParcelaActual;
    


    public JPanel getPanel() {
        return panel;
    }
    public VistaParcelas(){
        panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panel.add(scrollPane, BorderLayout.CENTER);

        icono = new ImageIcon("src/main/resources/icono.png");
        

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
                    ParcelaActual=jList.getSelectedValue();
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
                    ParcelaActual=jList.getSelectedValue();
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
    public void abrirVentanaAñadir(){
        final JFrame ventanaAñadir = new JFrame("Añadir parcela");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("id del Terreno asociado:"));
        final JTextField terrenoidField = new JTextField();
        panelAñadir.add(terrenoidField);

        panelAñadir.add(new JLabel("ubicacion:"));
        final JTextField ubicacionField = new JTextField();
        panelAñadir.add(ubicacionField);

        panelAñadir.add(new JLabel("limiteBase:"));
        final JTextField limiteBaseField = new JTextField();
        panelAñadir.add(limiteBaseField);

        panelAñadir.add(new JLabel("limiteAltura:"));
        final JTextField limiteAlturaField = new JTextField();
        panelAñadir.add(limiteAlturaField);
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                IdTerrenoasociadoActual =  terrenoidField.getText(); 
                boolean IdterrenoValid = ControladorParcelas.ValidarIdTerreno(Integer.parseInt(IdTerrenoasociadoActual));
                if (IdterrenoValid){
                ubicacionActual = ubicacionField.getText();
                limiteBaseActual = limiteBaseField.getText();
                limiteAlturaActual = limiteAlturaField.getText();
                ControladorParcelas.insertar();
                }else{
                    JOptionPane.showMessageDialog(ventanaAñadir, "El id del Terreno asociado no existe en la base de datos", "ErrorIdTerreno", JOptionPane.ERROR_MESSAGE);
                    return; // Detener el proceso si el IDTerreno no es válido
                }              
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
    public void abrirVentanaActualizar(){

        final JFrame ventanaActualizar = new JFrame("Actualizar Parcela");
        ventanaActualizar.setSize(300, 200);
        ventanaActualizar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("ubicacion:"));
        final JTextField ubicacionField = new JTextField();
        panelAñadir.add(ubicacionField);

        panelAñadir.add(new JLabel("limiteBase:"));
        final JTextField limiteBaseField = new JTextField();
        panelAñadir.add(limiteBaseField);

        panelAñadir.add(new JLabel("limiteAltura:"));
        final JTextField limiteAlturaField = new JTextField();
        panelAñadir.add(limiteAlturaField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                
                
                ubicacionActual = ubicacionField.getText();
                limiteBaseActual = limiteBaseField.getText();
                limiteAlturaActual = limiteAlturaField.getText();
                ControladorParcelas.actualizar(ParcelaActual, ubicacionActual, limiteBaseActual, limiteAlturaActual);           
               // Cerrar la ventana después de guardar
                ventanaActualizar.dispose();
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
    public void abrirVentanaBuscar() {
        final JFrame ventanaBuscar = new JFrame("Buscar Parcela");
        ventanaBuscar.setSize(300, 200);
        ventanaBuscar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("id del Terreno asociado:"));
        final JTextField terrenoField = new JTextField();
        panelAñadir.add(terrenoField);

        panelAñadir.add(new JLabel("ubicacion:"));
        final JTextField ubicacionField = new JTextField();
        panelAñadir.add(ubicacionField);

        panelAñadir.add(new JLabel("limiteBase:"));
        final JTextField limiteBaseField = new JTextField();
        panelAñadir.add(limiteBaseField);

        panelAñadir.add(new JLabel("limiteAltura:"));
        final JTextField limiteAlturaField = new JTextField();
        panelAñadir.add(limiteAlturaField);

        JButton guardarButton = new JButton("Guardar");
         guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                IdTerrenoasociadoActual =  terrenoField.getText(); 
                boolean IdterrenoValid = ControladorParcelas.ValidarIdTerreno(Integer.parseInt(IdTerrenoasociadoActual));
                if (IdterrenoValid){
                ubicacionActual = ubicacionField.getText();
                limiteBaseActual = limiteBaseField.getText();
                limiteAlturaActual = limiteAlturaField.getText();
               //Falta: ArrayList<Parcela> parcelas=  ControladorParcelas.(ParcelaActual, IdTerrenoasociadoActual, ubicacionActual, limiteBaseActual, limiteAlturaActual);
                }else{
                    JOptionPane.showMessageDialog(ventanaBuscar, "El id del Terreno asociado no existe en la base de datos", "ErrorIdTerreno", JOptionPane.ERROR_MESSAGE);
                    return; // Detener el proceso si el IDTerreno no es válido
                }              
               // Cerrar la ventana después de guardar
                ventanaBuscar.dispose();
                actualizar(null);
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
    public void ejecutarBorrado(){
        ControladorParcelas.borrar(ParcelaActual);
        actualizar(null);
    }
    public void actualizar(ArrayList<Parcela> givenparcelas) {
        // Limpiar la lista actual
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENEr las PArcelas
        ArrayList<Parcela> parcelas;
        if(givenparcelas==null)
            parcelas=ControladorParcelas.listar();
        else
            parcelas=givenparcelas;

        //parcelas=...
        if (parcelas != null) {
            for (Parcela parcela : parcelas) {
                listModel.addElement(parcela.toString());
            }
        }
    }
}
