package com.is.gestionterrenos.controlador;
import com.is.gestionterrenos.dao.ArrendatarioDAO;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.vista.VistaArrendatarios;

import java.sql.Date;
import java.util.ArrayList;


public class ControladorArrendatarios {

    public static void insertar() {
        ArrendatarioDAO.insertar(VistaArrendatarios.dniActual,VistaArrendatarios.nombreActual,Integer.parseInt(VistaArrendatarios.edadActual),VistaArrendatarios.sexoActual, new Date(System.currentTimeMillis()));
    }
    
    public static ArrayList<Arrendatario> listar(){
        return ArrendatarioDAO.listarTodos();
    }
}
