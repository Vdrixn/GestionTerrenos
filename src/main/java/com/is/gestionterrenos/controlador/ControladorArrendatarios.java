package com.is.gestionterrenos.controlador;
import com.is.gestionterrenos.dao.ArrendatarioDAO;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.vista.VistaArrendatarios;

import java.sql.Date;
import java.util.ArrayList;


public class ControladorArrendatarios {
    
    public static int insertar() {
        return ArrendatarioDAO.insertar(VistaArrendatarios.dniActual,VistaArrendatarios.nombreActual,Integer.parseInt(VistaArrendatarios.edadActual),VistaArrendatarios.sexoActual, new Date(System.currentTimeMillis()));
    }

    public static int actualizar(String oldArrendatario,String dni, String nombre, String edad, String sexo){
        //Cuando un campo no ha sido rellenado, será un string vacio ""
        
        //Primero vamos a sacar el id del arrendatario, como todos tienen el mismo formato, podemos sacar el id en la misma posición. EMPIEZA EN POS 17
        int idArrend;
        idArrend=Integer.parseInt(""+oldArrendatario.charAt(18));
        int i=19;
        while(Character.isDigit(oldArrendatario.charAt(i))){
            idArrend=idArrend*10+Integer.parseInt(""+oldArrendatario.charAt(i));
            i++;
        }
        //Ahora vamos a actualizar, pero si hay un dato vacío, vamos a asegurarnos de que no se copie, cogiendo el dato ya existente en la base de datos
        //Tambien nos aseguramos de que se haga como maximo una sóla llamada para sacar los datos ya existentes comprobando si arrendatario es null
        Arrendatario arrendatario=null;
        if(dni.equals("")){
            arrendatario=ArrendatarioDAO.buscarPorId(idArrend);
            dni=arrendatario.getDni();
        }

        if(nombre.equals("")){
            if(arrendatario==null)
                arrendatario=ArrendatarioDAO.buscarPorId(idArrend);

            nombre=arrendatario.getNombre();
        }
        if(edad.equals("")){
            if(arrendatario==null)
                arrendatario=ArrendatarioDAO.buscarPorId(idArrend);

            edad=arrendatario.getEdad()+"";
        }
        if(sexo.equals("")){
            if(arrendatario==null)
                arrendatario=ArrendatarioDAO.buscarPorId(idArrend);

            sexo=arrendatario.getSexo();
        }
        return ArrendatarioDAO.actualizar(idArrend,dni,nombre,Integer.parseInt(edad),sexo,new Date(System.currentTimeMillis()));
    }

    public static ArrayList<Arrendatario> buscar(String dni, String nombre, String edad, String sexo){
        if(edad.equals("") || edad==null)
            edad="0";
        return ArrendatarioDAO.buscarArrendatarios(dni,nombre,Integer.parseInt(edad),sexo);
    }

    public static int borrar(String strArrendatario){
        //Sacamos el id del arrendatario, como todos tienen el mismo formato, podemos sacar el id en la misma posición. EMPIEZA EN POS 17
        int idArrend;
        idArrend=Integer.parseInt(""+strArrendatario.charAt(18));
        int i=19;
        while(Character.isDigit(strArrendatario.charAt(i))){
            idArrend=idArrend*10+Integer.parseInt(""+strArrendatario.charAt(i));
            i++;
        }

        //Procedemos con el borrado
        return ArrendatarioDAO.eliminar(idArrend);
    }
    
    public static ArrayList<Arrendatario> listar(){
        return ArrendatarioDAO.listarTodos();
    }
}
