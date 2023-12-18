package com.is.gestionterrenos;

import org.junit.jupiter.api.Test;
import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.controlador.ControladorParcelas;
import com.is.gestionterrenos.controlador.ControladorRecibos;
import com.is.gestionterrenos.controlador.ControladorTerrenos;
import com.is.gestionterrenos.dao.ConexionDB;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.modelo.Parcela;
import com.is.gestionterrenos.modelo.Recibo;
import com.is.gestionterrenos.vista.VistaArrendatarios;
import com.is.gestionterrenos.vista.VistaTerrenos;
import com.is.gestionterrenos.vista.VistaParcelas;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CReciboTest {

    @BeforeAll
    public static void crearArrendatarioYParcela() {
        // Configurar entorno de prueba
        ConexionDB.resetDatabase();

        // Creación de entidades necesarias
        VistaArrendatarios.dniActual = "12345678Z";
        VistaArrendatarios.nombreActual = "Juan Pérez";
        VistaArrendatarios.edadActual = "30";
        VistaArrendatarios.sexoActual = "M";
        ControladorArrendatarios.insertar();

        VistaTerrenos.nombreTerreno = "Terreno 1";
        VistaTerrenos.ubicacionTerreno = "Ubicacion";
        VistaTerrenos.tamHectareasTerreno = "100";
        VistaTerrenos.tipoTerreno = "Finca";
        VistaTerrenos.limiteBaseTerreno = "10";
        VistaTerrenos.limiteAlturaTerreno = "10";
        ControladorTerrenos.insertar();

        VistaParcelas.IdTerrenoasociadoActual = "1";
        VistaParcelas.ubicacionActual = "Ubicacion";
        VistaParcelas.limiteBaseActual = "20";
        VistaParcelas.limiteAlturaActual = "5";
        ControladorParcelas.insertar();
    }

    @Test
    @Order(1)
    public void testRegistrarRecibo() {
        // Configurar entorno de prueba
        ArrayList<Arrendatario> arrens = ControladorArrendatarios.listar();
        String stringArrend = arrens.get(0).toString();
        ArrayList<Parcela> parcelas = ControladorParcelas.listar();
        String stringParcela = parcelas.get(0).toString();
        int importe = 100;
        boolean pagado = true;
        boolean activo = true;

        // Verificar que el recibo se ha registrado correctamente.
        assertEquals(1, ControladorRecibos.registrar(stringArrend, stringParcela, importe, pagado, activo), "El registro del recibo debería ser exitoso.");
    }

    @Test
    @Order(2)
    public void testBuscarRecibos() {
        // Búsqueda de recibos. 
        int idArren = 1;
        int idParcela = 1;
        ArrayList<Recibo> recibos = ControladorRecibos.buscar(idArren, idParcela, 0, 0, 0.0, 0);

        // Verificar que la busqueda se ha realizado correctamente.
        assertTrue(recibos.size() == 1, "La búsqueda debería retornar un recibo");
    }

    @Test
    @Order(3)
    public void testActualizarRecibo() {
        // Configurar entorno de prueba
        ArrayList<Recibo> recibos = ControladorRecibos.listar();
        String strRecibo = recibos.get(0).toString();

        // Actualizamos la información del recibo.
        ControladorRecibos.actualizar(strRecibo, 1, 1, 200.0, 21.0, 10.0);
        Recibo reciboActualizado = ControladorRecibos.buscar(1, 1, 200.0, 21.0, 10.0, 0).get(0);

        // Verificamos que la información se ha actualizado correctamente.
        assertEquals(200.0, reciboActualizado.getImporte(), "El importe del recibo debería haberse actualizado a 200.0");
    }

    @Test
    @Order(4)
    public void testListarRecibos() {
        // Configurar entorno de prueba
        ArrayList<Recibo> recibos = ControladorRecibos.listar();

        // Verificamos que la lista de recibos no esté vacía.
        assertFalse(recibos.isEmpty(), "La lista de recibos no debería estar vacía");
    }

    @Test
    @Order(5)
    public void testEliminarRecibo() {
        // Configurar entorno de prueba
        ArrayList<Recibo> recibos = ControladorRecibos.listar();
        String strRecibo = recibos.get(0).toString();
        
        // Verificamos que el recibo se ha eliminado correctamente.
        assertEquals(1, ControladorRecibos.borrar(strRecibo), "El recibo debería haber sido eliminado correctamente");
    }
}