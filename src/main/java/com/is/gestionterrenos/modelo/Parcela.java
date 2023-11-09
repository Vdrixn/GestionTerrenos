package com.is.gestionterrenos.modelo;

import java.sql.Date;

//TODO:Ciclo 1
public class Parcela {
    private int id;
    private int idTerreno;
    private int limiteBase,limiteAltura;
    private String ubicacion; 
    private Date fechaRegistro;

    public Parcela(int newId, int newIdTerreno, String newUbicacion, int limiteBase,int limiteAltura,Date fechaReg) {
        id = newId;
        idTerreno = newIdTerreno;
        limiteBase = limiteBase;
        limiteAltura = limiteAltura;
        ubicacion = newUbicacion;
        fechaRegistro = fechaReg;
    }

    // Getter para el campo 'id'
    public int getId() {
        return id;
    }

    // Setter para el campo 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para el campo 'idTerreno'
    public int getIdTerreno() {
        return idTerreno;
    }

    // Setter para el campo 'idTerreno'
    public void setIdTerreno(int idTerreno) {
        this.idTerreno = idTerreno;
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

    // Setter para el campo 'fechaRegistro'
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    // Getter para el campo 'fechaRegistro'
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    // Getter para el campo 'ubicacion'
    public String getUbicacion() {
        return ubicacion;
    }

    // Setter para el campo 'ubicacion'
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String toString() {
        return "Parcela [id=" + id + ", idTerreno=" + idTerreno + ", limiteBase=" + limiteBase + ", limiteAltura=" + limiteAltura + ", ubicacion=" + ubicacion + ", fechaRegistro=" + fechaRegistro + "]";
    }
}
