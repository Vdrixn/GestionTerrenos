package com.is.gestionterrenos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import com.is.gestionterrenos.controlador.ControladorTerrenos;
import com.is.gestionterrenos.dao.ConexionDB;
import com.is.gestionterrenos.modelo.Terreno;
import com.is.gestionterrenos.vista.VistaTerrenos;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
public class CTerrenoTest {

    @BeforeAll
    public static void setUp() {
        // Reseteamos la base de datos para que no haya problemas con test anteriores.
        ConexionDB.resetDatabase();
    }

    @Test
    @Order(1)
    @DisplayName("Test para insertar un terreno")
    public void testInsertar() {
        // Configurar los datos de prueba
        VistaTerrenos.nombreTerreno = "Terreno Prueba";
        VistaTerrenos.ubicacionTerreno = "Ubicación Prueba";
        VistaTerrenos.tamHectareasTerreno = "10";
        VistaTerrenos.tipoTerreno = "Tipo Prueba";
        VistaTerrenos.limiteBaseTerreno = "5";
        VistaTerrenos.limiteAlturaTerreno = "10";

        // Verificar si se ha insertado el terreno correctamente
        assertEquals(1, ControladorTerrenos.insertar());
    }

    @Test
    @Order(3)
    @DisplayName("Test para actualizar un terreno")
    public void testActualizar() {
        // Configurar los datos de prueba
        ArrayList<Terreno> terrenos = ControladorTerrenos.listar();
        String terrenoActual = terrenos.get(0).toString();
        String nombre = "Nombre Actualizado";
        String ubicacion = "Ubicación Actualizada";
        String tamHectareas = "15";
        String tipo = "Tipo Actualizado";
        String limiteBase = "6";
        String limiteAltura = "12";

        // Llamada al método a probar
        int resultado = ControladorTerrenos.actualizarTerreno(terrenoActual, nombre, ubicacion, tamHectareas, tipo,
                limiteBase, limiteAltura);

        // Verificar resultados
        assertEquals(1, resultado, "La actualización debería retornar 1");
    }

    @Test
    @Order(2)
    @DisplayName("Test para listar terrenos")
    public void testListar() {
        // Llamada al método a probar
        ArrayList<Terreno> terrenos = ControladorTerrenos.listar();

        // Verificar resultados
        assertNotNull(terrenos, "La lista de terrenos no debería ser nula");
        assertFalse(terrenos.isEmpty(), "La lista de terrenos no debería estar vacía");
    }

    @Test
    @Order(4)
    @DisplayName("Test para borrar un terreno")
    public void testBorrar() {
        // Configurar los datos de prueba
        ArrayList<Terreno> terrenos = ControladorTerrenos.listar();
        String terrenoActual = terrenos.get(0).toString();

        // Llamada al método a probar
        int resultado = ControladorTerrenos.borrar(terrenoActual);

        // Verificar resultados
        assertEquals(1, resultado, "El borrado debería retornar 1");
    }
}
