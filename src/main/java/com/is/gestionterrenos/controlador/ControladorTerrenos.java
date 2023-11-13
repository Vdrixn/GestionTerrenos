package com.is.gestionterrenos.controlador;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.is.gestionterrenos.dao.ArrendatarioDAO;
import com.is.gestionterrenos.dao.TerrenoDAO;
import com.is.gestionterrenos.modelo.Terreno;
import com.is.gestionterrenos.vista.VistaTerrenos;

public class ControladorTerrenos {


    public ControladorTerrenos() {
    }

    public static void insertar(){
        
        TerrenoDAO.insertar(VistaTerrenos.nombreTerreno, VistaTerrenos.ubicacionTerreno, Integer.parseInt(VistaTerrenos.tamHectareasTerreno), 
                            VistaTerrenos.tipoTerreno, Integer.parseInt(VistaTerrenos.limiteBaseTerreno), 
                            Integer.parseInt(VistaTerrenos.limiteAlturaTerreno), new Date(System.currentTimeMillis()));
    }
    
    public static void actualizarTerreno(String oldNombreTerreno, String nombre, String ubicacion, String tamHectareas,
                                        String tipoTerreno, String limiteBase, String limiteAltura) {
        // Extraer el ID del terreno de la cadena (suponiendo un formato específico)
        int idTerreno;
        int idIndex = oldNombreTerreno.indexOf("ID:") + 3; // Posición inicial del ID
        idTerreno = Integer.parseInt(oldNombreTerreno.substring(idIndex, oldNombreTerreno.indexOf(" ", idIndex)));

        // Variables para almacenar los datos existentes del terreno
        Terreno terreno = null;

        // Si algún campo está vacío, obtener los datos existentes del terreno
        if (nombre.equals("")) {
            terreno = TerrenoDAO.buscarPorId(idTerreno);
            nombre = terreno.getNombre();
        }

        if (ubicacion.equals("")) {
            if (terreno == null)
                terreno = TerrenoDAO.buscarPorId(idTerreno);

            ubicacion = terreno.getUbicacion();
        }

        if (tamHectareas.equals("")) {
            if (terreno == null)
                terreno = TerrenoDAO.buscarPorId(idTerreno);

            tamHectareas = String.valueOf(terreno.getTamHectareas());
        }

        if (tipoTerreno.equals("")) {
            if (terreno == null)
                terreno = TerrenoDAO.buscarPorId(idTerreno);

            tipoTerreno = terreno.getTipo();
        }

        if (limiteBase.equals("")) {
            if (terreno == null)
                terreno = TerrenoDAO.buscarPorId(idTerreno);

            limiteBase = String.valueOf(terreno.getLimiteBase());
        }

        if (limiteAltura.equals("")) {
            if (terreno == null)
                terreno = TerrenoDAO.buscarPorId(idTerreno);

            limiteAltura = String.valueOf(terreno.getLimiteAltura());
        }


        // Convertir valores numéricos y actualizar el terreno
        TerrenoDAO.actualizar(idTerreno, nombre, ubicacion, Integer.parseInt(tamHectareas), tipoTerreno,
                Integer.parseInt(limiteBase), Integer.parseInt(limiteAltura), new Date(System.currentTimeMillis()));
    }

    public static void borrar(String idTerreno){
        //Sacamos el id del terreno, como todos tienen el mismo formato, podemos sacar el id en la misma posición. EMPIEZA EN POS 17
        int idTerren;
        idTerren=Integer.parseInt(""+idTerreno.charAt(14));
        int i=15;
        while(Character.isDigit(idTerreno.charAt(i))){
            idTerren=idTerren*10+Integer.parseInt(""+idTerreno.charAt(i));
            i++;
        }

        //Procedemos con el borrado
        TerrenoDAO.eliminar(idTerren);
    }



    public static ArrayList<Terreno> listar(){
        return TerrenoDAO.listarTodos();
    }












    // Método para parsear la fecha (manejo de ParseException)
    private static Date parsearFecha(String fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(dateFormat.parse(fecha).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}

