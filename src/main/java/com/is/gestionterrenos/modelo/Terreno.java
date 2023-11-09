package com.is.gestionterrenos.modelo;
//TODO:Ciclo 1
public class Terreno {
    private int id;
    private String nombre;
    private String ubicacion;
    private int tamHectareas;
    private String tipo;
    private int limiteBase;
    private int limiteAltura;

    //Constructor que inicializa todos los campos con newX para cada parametro
    public Terreno(int newId, String newNombre, String newUbicacion, int newTamHectareas, String newTipo, int newLimiteBase, int newLimiteAltura) {
        id = newId;
        nombre = newNombre;
        ubicacion = newUbicacion;
        tamHectareas = newTamHectareas;
        tipo = newTipo;
        limiteBase= newLimiteBase;
        limiteAltura = newLimiteAltura;
    }

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

    // Getter para el campo 'limiteBase'
    public int getLimiteBase() {
        return limiteBase;
    }
    // Setter para el campo 'limiteBase'
    public void setLimiteBase(int limiteBase) {
        this.limiteBase = limiteBase;
    }
    // Getter para el campo 'limiteAltura'
    public int getLimiteAltura() {
        return limiteAltura;
    }
    // Setter para el campo 'limiteAltura'
    public void setLimiteAltura(int limiteAltura) {
        this.limiteAltura = limiteAltura;
    }
    
}
