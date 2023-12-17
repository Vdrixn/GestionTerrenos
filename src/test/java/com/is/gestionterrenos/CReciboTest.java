package com.is.gestionterrenos;

import org.junit.jupiter.api.Test;

import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.controlador.ControladorParcelas;
import com.is.gestionterrenos.controlador.ControladorRecibos;
import com.is.gestionterrenos.controlador.ControladorTerrenos;
import com.is.gestionterrenos.dao.ArrendatarioDAO;
import com.is.gestionterrenos.dao.ConexionDB;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.modelo.Recibo;
import com.is.gestionterrenos.vista.VistaArrendatarios;
import com.is.gestionterrenos.vista.VistaTerrenos;
import com.is.gestionterrenos.vista.VistaParcelas;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

public class CReciboTest {
    String strRecibo;
    

    @BeforeAll
    public static void crearArrendatarioYParcela(){
        ConexionDB.resetDatabase();
        VistaArrendatarios.dniActual = "12345678Z";
        VistaArrendatarios.nombreActual = "Juan Pérez";
        VistaArrendatarios.edadActual= "30";
        VistaArrendatarios.sexoActual= "M";

        ControladorArrendatarios.insertar();

        VistaTerrenos.nombreTerreno="Terreno 1";
        VistaTerrenos.ubicacionTerreno="Ubicacion";
        VistaTerrenos.tamHectareasTerreno="100";
        VistaTerrenos.tipoTerreno="Finca";
        VistaTerrenos.limiteBaseTerreno="10";
        VistaTerrenos.limiteAlturaTerreno="10";
       ControladorTerrenos.insertar();

        VistaParcelas.IdTerrenoasociadoActual="1";
        VistaParcelas.ubicacionActual ="Ubicacion";
        VistaParcelas.limiteBaseActual="20";
         VistaParcelas.limiteAlturaActual="5";

       ControladorParcelas.insertar();
    }


    @Test
    @Order(1)
    public void testRegistrarRecibo() {
        // Simular datos de entrada
        ArrayList <Arrendatario> arrens=ArrendatarioDAO.buscarArrendatarios(VistaArrendatarios.dniActual,VistaArrendatarios.nombreActual,Integer.parseInt(VistaArrendatarios.edadActual),VistaArrendatarios.sexoActual);
        Arrendatario a=arrens.get(0);
        
        String stringArrend = a.toString();

        String stringParcela = "1";
        int importe = 100;
        boolean pagado = true;
        boolean activo = true;

        
        int idRecibo = ControladorRecibos.registrar(stringArrend, stringParcela, importe, pagado, activo);

        
        assertTrue(idRecibo > 0);
    }

    @Test
    @Order(2)
    public void testBuscarRecibos() {
        int idArren = 1;
        int idParcela = 1;

        
        ArrayList<Recibo> recibos= ControladorRecibos.buscar(idArren, idParcela, 0,0, 0.0, 0).size();

        strRecibo=recibos.get(0).toString();
        assertTrue(recibos.size() >= 0);
    }

    @Test
    @Order(3)
    public void eliminarRecibo(){
        ControladorRecibos.borrar(strRecibo);
        assertTrue(ControladorRecibos.listar()==null || ControladorRecibos.listar().size()==0);
    }
}
