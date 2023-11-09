package com.is.gestionterrenos.modelo;
import java.sql.Date;
//TODO:Ciclo 1
public class Arrendatario {
    private int id;
    private String dni;
    private String nombre;
    private int edad;
    private String sexo;
    private Date fechaRegistro;

    //Constructor que inicializa todos los campos con newX para cada parametro
    public Arrendatario(int newId, String newDni, String newNombre, int newEdad, String newSexo, Date newFechaRegistro) {
        this.id = newId;
        this.dni = newDni;
        this.nombre = newNombre;
        this.edad = newEdad;
        this.sexo = newSexo;
        this.fechaRegistro = newFechaRegistro;        
    }

    // Getter para el campo 'id'
    public int getId() {
        return id;
    }

    // Setter para el campo 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para el campo 'dni'
    public String getDni() {
        return dni;
    }

    // Setter para el campo 'dni'
    public void setDni(String dni) {
        this.dni = dni;
    }

    // Getter para el campo 'nombre'
    public String getNombre() {
        return nombre;
    }

    // Setter para el campo 'nombre'
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el campo 'edad'
    public int getEdad() {
        return edad;
    }

    // Setter para el campo 'edad'
    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Getter para el campo 'sexo'
    public String getSexo() {
        return sexo;
    }

    // Setter para el campo 'sexo'
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    //Imprime los datos del arrendatario
    public String toString(){
        return "ID: " + id + " DNI: " + dni + " Nombre: " + nombre + " Edad: " + edad + " Sexo: " + sexo + " Fecha Registro: " + fechaRegistro;        
    }
}
