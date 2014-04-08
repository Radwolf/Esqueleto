package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "movimiento")
public class Movimiento extends Basic {

    @DatabaseField
    private long cuentaId;
    @DatabaseField
    private String tipoMovimiento;
    @DatabaseField
    private double importe;
    @DatabaseField
    private long momento;
    @DatabaseField
    private String categoria;
    @DatabaseField
    private String concepto;
    @DatabaseField
    private String email;

    public Movimiento() {
    }

    public Movimiento(long _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate, long cuentaId, String tipoMovimiento, double importe, long momento, String categoria, String concepto, String email) {
        super(_id, dateCreate, dateUpdate, userCreate, userUpdate);
        this.cuentaId = cuentaId;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.momento = momento;
        this.categoria = categoria;
        this.concepto = concepto;
        this.email = email;
    }

    public long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public long getMomento() {
        return momento;
    }

    public void setMomento(long momento) {
        this.momento = momento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
