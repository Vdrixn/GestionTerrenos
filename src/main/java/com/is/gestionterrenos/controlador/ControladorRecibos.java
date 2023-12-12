package com.is.gestionterrenos.controlador;

import com.is.gestionterrenos.dao.ReciboDAO;
import com.is.gestionterrenos.modelo.Recibo;
import com.is.gestionterrenos.vista.VistaRecibos;

import java.sql.Date;
import java.util.ArrayList;

public class ControladorRecibos {

    public static void insertar() { //TODO: gestionar mysql excepcion cuando se intenta añadir con un id no existente de arrendatario o de parcela
        ReciboDAO.insertar(VistaRecibos.idReciboActual,VistaRecibos.idParcelaActual,new Date(System.currentTimeMillis()),(double)VistaRecibos.importeActual,(double)VistaRecibos.importeActual*0.10,(double)VistaRecibos.importeActual*0.21);
    }

    public static void actualizar(String oldRecibo, int idArren, int idParcela, double importe, double iva, double irpf){
        //TODO: gestionar mysql excepcion cuando se intenta añadir con un id no existente de arrendatario o de parcela
        
        //Cuando un campo no ha sido rellenado, será un string vacio ""
        
        //Primero vamos a sacar el id del recibo, como todos tienen el mismo formato, podemos sacar el id en la misma posición. EMPIEZA EN POS 17
        int idRecibo = getIdRecibo(oldRecibo);
        
        //Ahora vamos a actualizar, pero si hay un dato vacío, vamos a asegurarnos de que no se copie, cogiendo el dato ya existente en la base de datos
        //Tambien nos aseguramos de que se haga como maximo una sóla llamada para sacar los datos ya existentes comprobando si recibo es null
        Recibo recibo=ReciboDAO.buscarPorId(idRecibo);
        if(idArren==0){
            idArren=recibo.getIdArren();
        }
        if(idParcela==0){
            idParcela=recibo.getIdParcela();
        }
        if(importe==0){
            importe=recibo.getImporte();
        }
        if(iva==0){
            iva=recibo.getIva();
        }
        if(irpf==0){
            irpf=recibo.getIrpf();
        }
        ReciboDAO.actualizar(idRecibo, idArren, idParcela, importe, iva, irpf);
    }

    public static ArrayList<Recibo> buscar(int idArren, int idParcela, double importe, double iva, double irpf){
        return ReciboDAO.buscarRecibos(idArren, idParcela, importe, iva, irpf);
    }

    public static void borrar(String strRecibo){
        int idRecibo = getIdRecibo(strRecibo);
        
        //Procedemos con el borrado
        ReciboDAO.eliminar(idRecibo);
    }
    
    public static ArrayList<Recibo> listar(){
        return ReciboDAO.listarTodos();
    }

    private static int getIdRecibo(String strRecibo){
        int idRecibo=Integer.parseInt(""+strRecibo.charAt(12));
        int i=13;
        while(Character.isDigit(strRecibo.charAt(i))){
            idRecibo=idRecibo*10+Integer.parseInt(""+strRecibo.charAt(i));
            i++;
        }
        return idRecibo;
    }
}
