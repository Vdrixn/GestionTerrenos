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

    // Getter para el campo 'fechaRegistro'
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    // Setter para el campo 'fechaRegistro'
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    //toString para imprimir los valores de los campos
    @Override
    public String toString() {
        return "Arrendatario [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + ", sexo=" + sexo + ", fechaRegistro=" + fechaRegistro + "]";
    }
    
}
