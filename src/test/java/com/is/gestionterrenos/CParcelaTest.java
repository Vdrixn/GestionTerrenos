package com.is.gestionterrenos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import com.is.gestionterrenos.controlador.ControladorParcelas;
import com.is.gestionterrenos.controlador.ControladorTerrenos;
import com.is.gestionterrenos.dao.ConexionDB;
import com.is.gestionterrenos.modelo.Parcela;
import com.is.gestionterrenos.modelo.Terreno;
import com.is.gestionterrenos.vista.VistaParcelas;
import com.is.gestionterrenos.vista.VistaTerrenos;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.DisplayName;

// La anotación TestMethodOrder permite definir el orden en que se ejecutan los tests.
@TestMethodOrder(OrderAnnotation.class)
public class CParcelaTest {

    @BeforeAll
    public static void setUp() throws Exception {
        // Reseteamos la base de datos para que no haya problemas con test anteriores.
        ConexionDB.resetDatabase();

        // Insertamos un terreno de prueba.
        VistaTerrenos.nombreTerreno = "Terreno Prueba";
        VistaTerrenos.ubicacionTerreno = "Ubicación Prueba";
        VistaTerrenos.tamHectareasTerreno = "10";
        VistaTerrenos.tipoTerreno = "Tipo Prueba";
        VistaTerrenos.limiteBaseTerreno = "5";
        VistaTerrenos.limiteAlturaTerreno = "10";
        ControladorTerrenos.insertar();
    }

    @Test
    @Order(1)
    @DisplayName("Test para el método insertar")
    public void testInsertar() {
        // Configurar los datos de prueba
        ArrayList<Terreno> terrenos = ControladorTerrenos.listar();
        VistaParcelas.IdTerrenoasociadoActual = terrenos.get(0).getId() + "";
        VistaParcelas.ubicacionActual = "Ubicación Test";
        VistaParcelas.limiteBaseActual = "10";
        VistaParcelas.limiteAlturaActual = "20";
        
        // Comprobamos que se ha insertado correctamente.
        assertEquals(1, ControladorParcelas.insertar(), "La inserción de la parcela debería ser exitosa");
    }

    @Test
    @Order(3)
    @DisplayName("Test para el método actualizar")
    public void testActualizar() {
        // Configurar los datos de prueba
        ArrayList<Parcela> parcelas = ControladorParcelas.listar();
        String strParcela = parcelas.get(0).toString();
        String Ubi = "Nueva Ubicación";
        String Limitebase = "15";
        String limiteAltura = "25";

        // Comprobamos que se ha actualizado correctamente.
        assertEquals(1, ControladorParcelas.actualizar(strParcela, Ubi, Limitebase, limiteAltura), "La actualización de la parcela debería ser exitosa");
    }

    @Test
    @Order(2)
    @DisplayName("Test para el método listar")
    public void testListar() {
        // Obtenemos la lista de parcelas.
        ArrayList<Parcela> parcelas = ControladorParcelas.listar();

        // Comprobamos que la lista no esté vacía.
        assertTrue(parcelas.size() > 0, "La lista de parcelas no debería estar vacía");
    }

    @Test
    @Order(4)
    @DisplayName("Test para el método borrar")
    public void testBorrar() {
        // Obtenemos la lista de parcelas para sacar una parcela.
        ArrayList<Parcela> parcelas = ControladorParcelas.listar();
        String strParcela = parcelas.get(0).toString();
        
        // Comprobamos que la parcela se borra correctamente.
        assertEquals(1, ControladorParcelas.borrar(strParcela), "El borrado de la parcela debería ser exitoso");
    }
}