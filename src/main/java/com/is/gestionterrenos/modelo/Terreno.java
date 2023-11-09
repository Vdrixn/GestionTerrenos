package com.is.gestionterrenos.modelo;
//TODO:Ciclo 1
public class Terreno {
    private int id;
    private String nombre;
    private String ubicacion;
    private int tamHectareas;
    private String tipo;
    private double[] limites;

    // Getter para el campo 'id'
    public int getId() {
        return id;
    }

    // Setter para el campo 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para el campo 'nombre'
    public String getNombre() {
        return nombre;
    }

    // Setter para el campo 'nombre'
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el campo 'ubicacion'
    public String getUbicacion() {
        return ubicacion;
    }

    // Setter para el campo 'ubicacion'
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Getter para el campo 'tamHectareas'
    public int getTamHectareas() {
        return tamHectareas;
    }

    // Setter para el campo 'tamHectareas'
    public void setTamHectareas(int tamHectareas) {
        this.tamHectareas = tamHectareas;
    }

    // Getter para el campo 'tipo'
    public String getTipo() {
        return tipo;
    }

    // Setter para el campo 'tipo'
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter para el campo 'limites'
    public double[] getLimites() {
        return limites;
    }

    // Setter para el campo 'limites'
    public void setLimites(double[] limites) {
        this.limites = limites;
    }
}
