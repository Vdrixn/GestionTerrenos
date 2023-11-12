package com.is.gestionterrenos.controlador;

import com.is.gestionterrenos.dao.ReciboDAO;
import com.is.gestionterrenos.modelo.Recibo;
import com.is.gestionterrenos.vista.VistaRecibos;

import java.sql.Date;
import java.util.ArrayList;

public class ControladorRecibos {

    public static void insertar() {
        // Asumiendo que VistaRecibos tiene campos estáticos o métodos para obtener los valores actuales.
        int idArren = VistaRecibos.getIdArrendatarioActual();
        int idParcela = VistaRecibos.getIdParcelaActual();
        double importe = VistaRecibos.getImporteActual();
        double iva = VistaRecibos.getIvaActual();
        double irpf = VistaRecibos.getIrpfActual();

        ReciboDAO.insertar(idArren, idParcela, new Date(System.currentTimeMillis()), importe, iva, irpf);
    }

    public static void actualizar(int id, int idArren, int idParcela, Date fechaEmision, double importe, double iva, double irpf){
        // Actualizar un recibo existente
        ReciboDAO.actualizar(id, idArren, idParcela, fechaEmision, importe, iva, irpf);
    }

    public static ArrayList<Recibo> buscar(int idArren, int idParcela, Date fechaEmision, double importe, double iva, double irpf){
        // Buscar recibos según los criterios proporcionados
        return ReciboDAO.buscarRecibos(idArren, idParcela, fechaEmision, importe, iva, irpf);
    }

    public static void borrar(int idRecibo){
        // Borrar un recibo específico
        ReciboDAO.eliminar(idRecibo);
    }
    
    public static ArrayList<Recibo> listar(){
        // Listar todos los recibos
        return ReciboDAO.listarTodos();
    }
}
