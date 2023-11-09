package com.is.gestionterrenos.modelo;

import java.sql.Date;

public class Recibo {
    private int id;
    private int idArren;
    private int idParcela;
    private Date fechaEmision;
    private double importe;
    private double iva;
    private double irpf;

    //Constructor que inicializa todos los campos con newX para cada parametro
    public Recibo(int newId, int newIdArren, int newIdParcela, Date newFechaEmision, double newImporte, double newIva, double newIrpf) {
        id = newId;
        idArren = newIdArren;
        idParcela = newIdParcela;
        fechaEmision = newFechaEmision;
        importe = newImporte;
        iva = newIva;
        irpf = newIrpf;    
    }


    // Getter para el campo 'id'
    public int getId() {
        return id;
    }

    // Setter para el campo 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para el campo 'idArren'
    public int getIdArren() {
        return idArren;
    }

    // Setter para el campo 'idArren'
    public void setIdArren(int idArren) {
        this.idArren = idArren;
    }

    // Getter para el campo 'idParcela'
    public int getIdParcela() {
        return idParcela;
    }

    // Setter para el campo 'idParcela'
    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    // Getter para el campo 'fechaEmision'
    public Date getFechaEmision() {
        return fechaEmision;
    }

    // Setter para el campo 'fechaEmision'
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    // Getter para el campo 'importe'
    public double getImporte() {
        return importe;
    }

    // Setter para el campo 'importe'
    public void setImporte(double importe) {
        this.importe = importe;
    }

    // Getter para el campo 'iva'
    public double getIva() {
        return iva;
    }

    // Setter para el campo 'iva'
    public void setIva(double iva) {
        this.iva = iva;
    }

    // Getter para el campo 'irpf'
    public double getIrpf() {
        return irpf;
    }

    // Setter para el campo 'irpf'
    public void setIrpf(double irpf) {
        this.irpf = irpf;
    }
}
