package com.is.gestionterrenos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.dao.ConexionDB;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.vista.VistaArrendatarios;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CArrenTest {

    @BeforeAll
    public static void setUp() {
        // Restablecer la base de datos antes de cada prueba
        ConexionDB.resetDatabase();
    }

    @Test
    @Order(1)
    @DisplayName("Test para insertar Arrendatario")
    public void testInsertar() {
        // Configuración de los datos del arrendatario para la prueba de inserción
        VistaArrendatarios.dniActual = "12345678X";
        VistaArrendatarios.nombreActual = "Test Name";
        VistaArrendatarios.edadActual = "30";
        VistaArrendatarios.sexoActual = "M";

        // Verificar si el arrendatario se ha insertado correctamente
        assertEquals(1, ControladorArrendatarios.insertar(), "La inserción del arrendatario debería ser exitosa");
    }

    @Test
    @Order(2)
    @DisplayName("Test para buscar Arrendatario")
    public void testBuscar() {
        // Configurar criterios de búsqueda para la prueba
        String dni = "12345678X";
        String nombre = "";
        String edad = "";
        String sexo = "";

        // Realizar la búsqueda y verificar los resultados
        ArrayList<Arrendatario> resultado = ControladorArrendatarios.buscar(dni, nombre, edad, sexo);
        assertEquals(1, resultado.size(), "Debería encontrar al menos un arrendatario con los criterios dados");
    }

    @Test
    @Order(3)
    @DisplayName("Test para actualizar Arrendatario")
    public void testActualizar() {
        // Configuración de datos para la prueba de actualización
        String dni = "12345678X";
        String nombre = "Updated Name";
        String edad = "35";
        String sexo = "F";

        // Obtener el arrendatario a actualizar
        ArrayList<Arrendatario> resultado = ControladorArrendatarios.buscar(dni, "", "", "");
        Arrendatario arren = resultado.get(0);
        String oldArrendatario = arren.toString();

        // Realizar la actualización y verificar
        assertEquals(1, ControladorArrendatarios.actualizar(oldArrendatario, dni, nombre, edad, sexo), "La actualización del arrendatario debería ser exitosa");
    }

    @Test
    @Order(4)
    @DisplayName("Test para borrar Arrendatario")
    public void testBorrar() {
        // Configuración de datos para la prueba de borrado
        String dni = "12345678X";

        // Obtener el arrendatario a borrar
        ArrayList<Arrendatario> resultado = ControladorArrendatarios.buscar(dni, "", "", "");
        Arrendatario arren = resultado.get(0);
        String strArrendatario = arren.toString();

        // Realizar el borrado y verificar
        assertEquals(1, ControladorArrendatarios.borrar(strArrendatario), "El borrado del arrendatario debería ser exitoso");
    }

    @Test
    @Order(5)
    @DisplayName("Test para listar Arrendatarios")
    public void testListar() {
        // Listar los arrendatarios y verificar si se obtiene una lista no nula
        ArrayList<Arrendatario> arrendatarios = ControladorArrendatarios.listar();
        assertNotNull(arrendatarios, "La lista de arrendatarios no debería ser nula");
    }
}