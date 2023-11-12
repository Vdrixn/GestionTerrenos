package com.is.gestionterrenos.controlador;

import com.is.gestionterrenos.dao.ReciboDAO;
import com.is.gestionterrenos.modelo.Recibo;
import com.is.gestionterrenos.vista.VistaRecibos;

import java.sql.Date;
import java.util.ArrayList;

public class ControladorRecibos {

    public static void insertar() {
        ReciboDAO.insertar(VistaRecibos.idReciboatarioActual,VistaRecibos.idParcelaActual,new Date(System.currentTimeMillis()),VistaRecibos.importeActual,VistaRecibos.ivaActual,VistaRecibos.irpfActual);
    }

    public static void actualizar(String oldRecibo, int idArren, int idParcela, double importe, double iva, double irpf){
        //Cuando un campo no ha sido rellenado, será un string vacio ""
        
        //Primero vamos a sacar el id del recibo, como todos tienen el mismo formato, podemos sacar el id en la misma posición. EMPIEZA EN POS 17
        int idRecibo = getIdRecibo(oldRecibo);
        
        //Ahora vamos a actualizar, pero si hay un dato vacío, vamos a asegurarnos de que no se copie, cogiendo el dato ya existente en la base de datos
        //Tambien nos aseguramos de que se haga como maximo una sóla llamada para sacar los datos ya existentes comprobando si recibo es null
        Recibo recibo=ReciboDAO.buscarPorId(idRecibo);
        if(idArren){
            idArren=recibo.getIdArren();
        }
        if(idParcela){
            idParcela=recibo.getIdParcela();
        }
        if(importe){
            importe=recibo.getImporte();
        }
        if(iva){
            iva=recibo.getIva();
        }
        if(irpf){
            irpf=recibo.getIrpf();
        }
        ReciboDAO.actualizar(idRecibo, idArren, idParcela, importe, iva, irpf);
    }

    public static ArrayList<recibo> buscar(int idArren, int idParcela, Date fechaEmision, double importe, double iva, double irpf){
        return ReciboDAO.buscarrecibos(idArren, idParcela, fechaEmision, importe, iva, irpf);
    }

    public static void borrar(String strRecibo){
        int idRecibo = getIdRecibo(strRecibo);
        
        //Procedemos con el borrado
        ReciboDAO.eliminar(idRecibo);
    }
    
    public static ArrayList<recibo> listar(){
        return ReciboDAO.listarTodos();
    }

    private static int getIdRecibo(String strRecibo){
        //TODO: Cambiar index para sacar id
        int idRecibo=Integer.parseInt(""+strRecibo.charAt(18));
        int i=19;
        while(Character.isDigit(strRecibo.charAt(i))){
            idRecibo=idRecibo*10+Integer.parseInt(""+strRecibo.charAt(i));
            i++;
        }
        return idRecibo;
    }
}
