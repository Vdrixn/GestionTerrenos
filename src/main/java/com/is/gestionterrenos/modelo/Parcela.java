package com.is.gestionterrenos.modelo;
//TODO:Ciclo 1
public class Parcela {
    private int id;
    private int idTerreno;
    private double[] limites;
    private String ubicacion; 

    public Parcela(int newId, int newIdTerreno, double[] newLimites, String newUbicacion) {
        id = newId;
        idTerreno = newIdTerreno;
        limites = newLimites;
        ubicacion = newUbicacion;
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

    // Getter para el campo 'limites'
    public double[] getLimites() {
        return limites;
    }

    // Setter para el campo 'limites'
    public void setLimites(double[] limites) {
        this.limites = limites;
    }

    // Getter para el campo 'ubicacion'
    public String getUbicacion() {
        return ubicacion;
    }

    // Setter para el campo 'ubicacion'
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
