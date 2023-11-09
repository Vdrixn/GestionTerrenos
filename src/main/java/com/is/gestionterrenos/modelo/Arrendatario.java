package com.is.gestionterrenos.modelo;
//TODO:Ciclo 1
public class Arrendatario {
    private int id;
    private String dni;
    private String nombre;
    private int edad;
    private int sexo;

    public Arrendatario() {

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
    public int getSexo() {
        return sexo;
    }

    // Setter para el campo 'sexo'
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    
}
