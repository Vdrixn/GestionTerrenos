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

    public static void actualizar(String oldArrendatario,String dni, String nombre, String edad, String sexo){
        //Cuando un campo no ha sido rellenado, será un string vacio ""
        
        //Primero vamos a sacar el id del arrendatario, como todos tienen el mismo formato, podemos sacar el id en la misma posición. EMPIEZA EN POS 17
        int idArrend;
        idArrend=Integer.parseInt(""+oldArrendatario.charAt(18));
        int i=19;
        while(oldArrendatario.charAt(i)>=0 && oldArrendatario.charAt(i)<=9){
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
        ArrendatarioDAO.actualizar(idArrend,dni,nombre,Integer.parseInt(edad),sexo,new Date(System.currentTimeMillis()));
    


        
    }
    
    public static ArrayList<Arrendatario> listar(){
        return ArrendatarioDAO.listarTodos();
    }
}
