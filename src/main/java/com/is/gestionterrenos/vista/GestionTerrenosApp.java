package com.is.gestionterrenos.vista;
import javax.swing.*;

import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.controlador.ControladorParcelas;
import com.is.gestionterrenos.controlador.ControladorRecibos;
import com.is.gestionterrenos.controlador.ControladorTerrenos;
import com.is.gestionterrenos.dao.ArrendatarioDAO;
import com.is.gestionterrenos.dao.ConexionDB;
import com.is.gestionterrenos.dao.ReciboDAO;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.modelo.Parcela;
import com.is.gestionterrenos.modelo.Recibo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Clase principal que contiene el JFrame principal
public class GestionTerrenosApp {
    public static JTextField nombreArrendatarioField = new JTextField();
    public static JTextField ubicacionTerrenoField = new JTextField();
    public static JTextField fechaInicioField = new JTextField();
    public static JTextField duracionContratoField = new JTextField();
    public static JFrame ventanaRegistroAlquiler = new JFrame("Registrar Alquiler");
    public static JFrame ventanaInforme = new JFrame("Generar Informe");
    // Crear un JComboBox para que el usuario seleccione un arrendatario
    public static DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    public static JComboBox<String> comboBoxArrendatarios = new JComboBox<>(comboBoxModel);


    public static ImageIcon icono;
    public static boolean vistaArrenActiva = false;
    public static boolean vistaTerrenActiva = false;
    public static boolean vistaParcelaActiva = false;
    public static boolean vistaReciboActiva = false;
    private static String stringArrendatarioActual="";
    private static String stringParcelaActual="";
    private static String stringReciboActual="";
    private static Recibo reciboActual;
    // Lista que almacena los arrendatarios cargados
    private static ArrayList<Arrendatario> arrendatarios;

    // Lista temporal para comparación
    private static ArrayList<Arrendatario> arrendatariosAnteriores;
    

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

    private static int mostrarListadoArrendatarios(){
        final JFrame listaArrend=new JFrame();
        listaArrend.setTitle("Seleccionar Arrendatario");
        listaArrend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listaArrend.setLayout(new FlowLayout());
        
        final JPanel panelA = new JPanel(new BorderLayout());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        ArrayList<Arrendatario> arrendatarios=ControladorArrendatarios.listar();
        
        //arrendatarios=...
        if (arrendatarios != null) {
            for (Arrendatario arrendatario : arrendatarios) {
                listModel.addElement(arrendatario.toString());
            }
        }
        final JList<String> jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panelA.add(scrollPane);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stringArrendatarioActual = jList.getSelectedValue();
                listaArrend.dispose();
            }
        });
        panelA.add(seleccionarButton);
        listaArrend.add(scrollPane);
        listaArrend.add(seleccionarButton);

        listaArrend.pack();
        listaArrend.setLocationRelativeTo(null);
        listaArrend.revalidate();
        listaArrend.setIconImage(icono.getImage());
        listaArrend.setVisible(true);
        return 0;
    }

    private static int mostrarListadoParcelas(){
        final JFrame listaParc=new JFrame();
        listaParc.setTitle("Seleccionar Parcela");
        listaParc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listaParc.setLayout(new FlowLayout());
        
        final JPanel panelA = new JPanel(new BorderLayout());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        

        // SE LLAMA AL CONTROLADOR PARA OBTENEr las PArcelas
        ArrayList<Parcela> parcelas=ControladorParcelas.listar();
        
        //parcelas=...
        if (parcelas != null) {
            for (Parcela parcela : parcelas) {
                listModel.addElement(parcela.toString());
            }
        }
        final JList<String> jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panelA.add(scrollPane);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stringParcelaActual = jList.getSelectedValue();
                listaParc.dispose();
            }
        });
        panelA.add(seleccionarButton);
        listaParc.add(scrollPane);
        listaParc.add(seleccionarButton);

        listaParc.pack();
        listaParc.setLocationRelativeTo(null);
        listaParc.revalidate();
        listaParc.setIconImage(icono.getImage());
        listaParc.setVisible(true);
        return 0;
    }

    private static int mostrarListadoRecibos(){
        final JFrame listaRecibo=new JFrame();
        listaRecibo.setTitle("Seleccionar Arrendatario");
        listaRecibo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listaRecibo.setLayout(new FlowLayout());
        
        final JPanel panelA = new JPanel(new BorderLayout());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        ArrayList<Recibo> recibos=ControladorRecibos.buscar(0, 0, 0, 0, 0, 1);
        
        //arrendatarios=...
        if (recibos != null) {
            for (Recibo recibo : recibos) {
                listModel.addElement(recibo.toString());
            }
        }
        final JList<String> jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panelA.add(scrollPane);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stringReciboActual = jList.getSelectedValue();
                listaRecibo.dispose();
            }
        });
        panelA.add(seleccionarButton);
        listaRecibo.add(scrollPane);
        listaRecibo.add(seleccionarButton);
        listaRecibo.pack();
        listaRecibo.setLocationRelativeTo(null);
        listaRecibo.revalidate();
        listaRecibo.setIconImage(icono.getImage());
        listaRecibo.setVisible(true);
        return 0;
    }

    private static void mostrarVentanaGenerarInforme() {
        // Almacena temporalmente la lista actual de arrendatarios antes de mostrar la ventana
        // Almacena temporalmente la lista actual de arrendatarios antes de mostrar la ventana
        // Obtener la lista actualizada de arrendatarios
        ArrayList<Arrendatario> arrendatarios = (ArrayList<Arrendatario>) ControladorArrendatarios.listar();

        ventanaInforme.setSize(400, 200);
        ventanaInforme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un panel para la interfaz de usuario
        JPanel panelInforme = new JPanel(new BorderLayout());

        // Obtener la lista actualizada de arrendatarios
        arrendatarios = ControladorArrendatarios.listar();

        // Verificar si arrendatarios es null y, si es así, inicializarlo con una lista vacía
        if (arrendatarios == null) {
            arrendatarios = new ArrayList<>();
        }

        // Limpiar el modelo antes de llenarlo nuevamente
        comboBoxModel.removeAllElements();

        // Llenar el modelo con los nombres de los arrendatarios
        for (Arrendatario arrendatario : arrendatarios) {
            comboBoxModel.addElement(arrendatario.toString());
        }

        panelInforme.add(comboBoxArrendatarios, BorderLayout.CENTER);

        JButton botonGenerar = new JButton("Generar Informe");
        botonGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el arrendatario seleccionado
                String arrendatarioSeleccionado = (String) comboBoxArrendatarios.getSelectedItem();

                if (arrendatarioSeleccionado != null) {
                    // Aquí puedes agregar la lógica para generar el informe con el arrendatario seleccionado el ID del arrendatario seleccionado
                int idArrendatario = obtenerIdArrendatario(arrendatarioSeleccionado);

                // Obtener los recibos del arrendatario seleccionado
                ArrayList<Recibo> recibos = ReciboDAO.buscarPorIdDeArrendatario(idArrendatario);

                // Generar el informe en un archivo de texto
                generarInforme(arrendatarioSeleccionado, recibos);

                    // Recuerda cerrar la ventana después de completar la operación
                    ventanaInforme.dispose();
                } else {
                    JOptionPane.showMessageDialog(ventanaInforme, "Seleccione un arrendatario antes de generar el informe.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        panelInforme.add(botonGenerar, BorderLayout.SOUTH);

        ventanaInforme.getContentPane().add(panelInforme);
        ventanaInforme.setLocationRelativeTo(null);
        ventanaInforme.setVisible(true);
    }

    private static int obtenerIdArrendatario(String nombreArrendatario) {
        int idArrendatario = -1;  // Valor por defecto si no se encuentra
    
        // Obtener la lista actualizada de arrendatarios
        ArrayList<Arrendatario> arrendatarios = ControladorArrendatarios.listar();
    
        // Buscar el arrendatario por nombre y obtener su ID
        for (Arrendatario arrendatario : arrendatarios) {
            if (arrendatario.toString().equals(nombreArrendatario)) {
                idArrendatario = arrendatario.getId();
                break;
            }
        }
    
        return idArrendatario;
    }
private static void generarInforme(String nombreArrendatario, ArrayList<Recibo> recibos) {
    try {
        // Obtén el DNI del arrendatario desde la cadena generada
        String dniArrendatario = extraerDNI(nombreArrendatario);

        // Verifica si el DNI es válido
        if (dniArrendatario != null) {
            // Usa el DNI del arrendatario como nombre de archivo
            String nombreArchivo = "Informe_" + dniArrendatario + ".txt";
        // Crear un FileWriter
        FileWriter writer = new FileWriter(nombreArchivo);

        // Escribir información en el archivo
        writer.write("Información del arrendatario: " + nombreArrendatario + "\n\n");

        for (Recibo recibo : recibos) {
            writer.write("ID Recibo: " + recibo.getId() + "\n");
            writer.write("Fecha Emisión: " + recibo.getFechaEmision() + "\n");
            writer.write("Importe: " + recibo.getImporte() + "\n\n");
        }

        // Cerrar el FileWriter
        writer.close();
        JOptionPane.showMessageDialog(null, "Informe creado con éxito.\nNombre del archivo: " + nombreArchivo, "Informe Creado", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo extraer el DNI del arrendatario.", "Error", JOptionPane.ERROR_MESSAGE);
    }
} catch (IOException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error al generar el informe.", "Error", JOptionPane.ERROR_MESSAGE);
}
}

private static String extraerDNI(String arrendatarioInfo) {
    // Encuentra la posición de "dni=" en la cadena
    int inicioDNI = arrendatarioInfo.indexOf("dni=");

    // Verifica si se encontró "dni=" en la cadena
    if (inicioDNI != -1) {
        // Encuentra la posición de "," después de "dni="
        int finDNI = arrendatarioInfo.indexOf(",", inicioDNI);

        // Verifica si se encontró "," después de "dni="
        if (finDNI != -1) {
            // Extrae la subcadena que representa el DNI
            return arrendatarioInfo.substring(inicioDNI + 4, finDNI).trim();
        }
    }

    // Devuelve null si no se pudo extraer el DNI
    return null;
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
        botonHome.setBackground(Color.decode("#FAAE17"));
        botonHome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        northPannel.add(botonHome, BorderLayout.WEST);

        final JComboBox<String> comboBox = new JComboBox<>(opciones);
        northPannel.add(comboBox, BorderLayout.CENTER);

        final JButton botonRegistrarAlquiler = new JButton("Registrar Alquiler");
        botonRegistrarAlquiler.setPreferredSize(new Dimension(200, 80));
        botonRegistrarAlquiler.setFont(new Font("Arial", Font.BOLD, 16));
        botonRegistrarAlquiler.setForeground(Color.BLACK);
        botonRegistrarAlquiler.setBackground(Color.decode("#FAAE17"));
        botonRegistrarAlquiler.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        final JButton botonDarDeBajaAlquiler = new JButton("Dar de baja Alquiler");
        botonDarDeBajaAlquiler.setPreferredSize(new Dimension(200, 80)); 
        botonDarDeBajaAlquiler.setFont(new Font("Arial", Font.BOLD, 16)); 
        botonDarDeBajaAlquiler.setForeground(Color.BLACK);
        botonDarDeBajaAlquiler.setBackground(Color.decode("#FAAE17"));
        botonDarDeBajaAlquiler.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(botonRegistrarAlquiler);
        box.add(Box.createHorizontalGlue());
        box.add(Box.createHorizontalStrut(10)); // Espacio vertical entre botones
        box.add(botonDarDeBajaAlquiler);
        box.add(Box.createHorizontalGlue());

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
                // Crear un nuevo JFrame para la ventana de registro de alquiler
                ventanaRegistroAlquiler.setSize(400, 300);
                ventanaRegistroAlquiler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
                // Crear un JPanel con un GridLayout
                JPanel panelRegistroAlquiler = new JPanel(new GridLayout(5, 2,10,10));

                final JTextField importeField;
                final JCheckBox pagadoCheckbox;
                final JCheckBox activoCheckbox;
        
                // Elementos del panel
                JLabel arrendatarioLabel = new JLabel("Arrendatario");
                JButton seleccionarArrendatarioButton = new JButton("Seleccionar");
                
                seleccionarArrendatarioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener y mostrar el listado de registros de arrendatarios
                        mostrarListadoArrendatarios();
                    }

                });

                JLabel parcelaLabel = new JLabel("Parcela");
                JButton seleccionarParcelaButton = new JButton("Seleccionar");
                final int idParcela=-1;
                seleccionarParcelaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener y mostrar el listado de registros de parcelas
                        mostrarListadoParcelas();
                    }
                });

                JLabel importeLabel = new JLabel("Importe");
                importeField = new JTextField();

                JLabel pagadoLabel = new JLabel("Pagado");
                pagadoCheckbox = new JCheckBox();
                

                JLabel activoLabel = new JLabel("Activo");
                activoCheckbox = new JCheckBox();
                
                // Agregar elementos al panel
                panelRegistroAlquiler.add(arrendatarioLabel);
                panelRegistroAlquiler.add(seleccionarArrendatarioButton);
                panelRegistroAlquiler.add(parcelaLabel);
                panelRegistroAlquiler.add(seleccionarParcelaButton);
                panelRegistroAlquiler.add(importeLabel);
                panelRegistroAlquiler.add(importeField);
               panelRegistroAlquiler. add(pagadoLabel);
                panelRegistroAlquiler.add(pagadoCheckbox);
               panelRegistroAlquiler.add(activoLabel);
               panelRegistroAlquiler. add(activoCheckbox);
        
                // Crear un botón de guardar
                JButton guardarButton = new JButton("Registrar");
                guardarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener los valores ingresados por el usuario
                        if(stringArrendatarioActual.equals("") || stringParcelaActual.equals("") || importeField.getText().equals("")){
                            JOptionPane.showMessageDialog(frame, "Seleccione arrendatario, parcela e importe.", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            boolean estaPagado = pagadoCheckbox.isSelected();
                            boolean estaActivo = activoCheckbox.isSelected();
            
                        
                            int idRecibo=ControladorRecibos.registrar(stringArrendatarioActual, stringParcelaActual, Integer.parseInt(importeField.getText()), estaPagado, estaActivo);
                            if(idRecibo!=-1){
                                JOptionPane.showMessageDialog(frame, "¡Registro realizado! ID de recibo: "+idRecibo, "Registro", JOptionPane.INFORMATION_MESSAGE);
                            }
                            stringArrendatarioActual="";
                            stringParcelaActual="";                       
                            // Cerrar la ventana de registro de alquiler
                            ventanaRegistroAlquiler.dispose();
                        }
                        
                    }
                });
        
                // Añadir componentes al panel de botones
                JPanel panelBoton = new JPanel(new GridLayout(1, 1));
                panelBoton.add(guardarButton);
        
                // Añadir paneles al JFrame
                ventanaRegistroAlquiler.getContentPane().add(panelRegistroAlquiler, BorderLayout.CENTER);
                ventanaRegistroAlquiler.getContentPane().add(panelBoton, BorderLayout.SOUTH);
        
                // Configurar la ubicación y hacer visible la ventana
                ventanaRegistroAlquiler.setLocationRelativeTo(null);
                ventanaRegistroAlquiler.setVisible(true);
            }
        });
        

        botonDarDeBajaAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanaBajaAlquiler = new JFrame("Dar de Baja Alquiler");
                ventanaBajaAlquiler.setSize(500, 100);
                ventanaRegistroAlquiler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Crear un JPanel con un GridLayout
                JPanel panelDarBajaAlquiler = new JPanel(new GridLayout(1, 2,10,10));

                final JTextField importeField;
                final JCheckBox pagadoCheckbox;
                final JCheckBox activoCheckbox;
        
                // Elementos del panel
                JLabel reciboLabel = new JLabel("Recibo");
                JButton seleccionarReciboButton = new JButton("Seleccionar");
                
                seleccionarReciboButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener y mostrar el listado de registros de arrendatarios
                        mostrarListadoRecibos();
                    }

                });
        
                // Aquí implementas la lógica para listar los recibos en un JTable o similar
        
                JButton botonBaja = new JButton("Dar de Baja");
                botonBaja.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(stringReciboActual.equals("")){
                            JOptionPane.showMessageDialog(frame, "Seleccione un recibo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{

                        }
                        stringReciboActual="";
                    }
                });

                // Agregar elementos al panel
                panelDarBajaAlquiler.add(reciboLabel);
                panelDarBajaAlquiler.add(seleccionarReciboButton);
        
                // Añade el botón y otros componentes a la ventana
                // Añadir paneles al JFrame
                ventanaBajaAlquiler.getContentPane().add(panelDarBajaAlquiler, BorderLayout.CENTER);
                ventanaBajaAlquiler.add(botonBaja, BorderLayout.SOUTH);

                ventanaBajaAlquiler.setLocationRelativeTo(null);
                ventanaBajaAlquiler.setVisible(true);
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


        final JButton botonGenerarInforme = new JButton("Generar Informe");
        botonGenerarInforme.setPreferredSize(new Dimension(200, 80));
        botonGenerarInforme.setFont(new Font("Arial", Font.BOLD, 16));
        botonGenerarInforme.setForeground(Color.BLACK);
        botonGenerarInforme.setBackground(Color.decode("#FAAE17"));
        botonGenerarInforme.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        botonGenerarInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para abrir una ventana emergente y generar el informe
                mostrarVentanaGenerarInforme();
            }
        });

        box.add(Box.createHorizontalStrut(10));
        box.add(botonGenerarInforme);
        box.add(Box.createHorizontalGlue());

    }
}

// Clase VistaArrendatarios que contiene la lógica y la interfaz específica para los arrendatarios
