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
        ConexionDB.resetDatabase();
    }

    @Test
    @Order(1)
    @DisplayName("Insertar Arrendatario")
    public void testInsertar() {
        // Assume VistaArrendatarios has static fields/methods for input
        // Set dummy data for insertion
        VistaArrendatarios.dniActual = "12345678X";
        VistaArrendatarios.nombreActual = "Test Name";
        VistaArrendatarios.edadActual = "30";
        VistaArrendatarios.sexoActual = "M";

        // Assert that the insertion was successful
        assertEquals(1, ControladorArrendatarios.insertar());
    }

    @Test
    @Order(2)
    @DisplayName("Buscar Arrendatario")
    public void testBuscar() {
        // Set search criteria
        String dni = "12345678X";
        String nombre = "";
        String edad = "";
        String sexo = "";

        // Perform the search
        ArrayList<Arrendatario> result = ControladorArrendatarios.buscar(dni, nombre, edad, sexo);

        assertEquals(1,result.size());
        // assertTrue(result.contains(expectedTenant));
    }

    @Test
    @Order(3)
    @DisplayName("Actualizar Arrendatario")
    public void testActualizar() {
        // Insert a dummy tenant for deletion
        // Assume the tenant's ID is known or retrieved
        String dni = "12345678X";
        String nombre = "";
        String edad = "";
        String sexo = "";
        ArrayList<Arrendatario> result = ControladorArrendatarios.buscar(dni, nombre, edad, sexo);

        Arrendatario arren = result.get(0);
        String oldArrendatario = arren.toString();
        
        // Create a dummy tenant for testing the update
        dni = "12345678X";
        nombre = "Updated Name";
        edad = "35";
        sexo = "F";

        // Perform the update
        // Assume the update was successful
        assertEquals(1, ControladorArrendatarios.actualizar(oldArrendatario, dni, nombre, edad, sexo));
    }

    @Test
    @Order(4)
    public void testBorrar() {
        // Insert a dummy tenant for deletion
        // Assume the tenant's ID is known or retrieved
        String dni = "12345678X";
        String nombre = "";
        String edad = "";
        String sexo = "";
        ArrayList<Arrendatario> result = ControladorArrendatarios.buscar(dni, nombre, edad, sexo);

        Arrendatario arren = result.get(0);
        String strArrendatario = arren.toString();
    
        // Perform the deletion

        // Assert that the deletion was successful
        assertEquals(1, ControladorArrendatarios.borrar(strArrendatario));
    }

    @Test
    @Order(3)
    public void testListar() {
        
        ArrayList<Arrendatario>  arrendatarios = ControladorArrendatarios.listar();

        assertNotNull(arrendatarios);
    }
}
