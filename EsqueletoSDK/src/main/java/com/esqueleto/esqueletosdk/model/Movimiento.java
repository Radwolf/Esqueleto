package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "movimiento")
public class Movimiento extends Basic {

    @DatabaseField
    private long resumenId;
    @DatabaseField
    private String tipoMovimiento;
    @DatabaseField
    private double importe;
    @DatabaseField
    private Date fechaEstimada;
    @DatabaseField
    private Date fechaMovimiento;
    @DatabaseField
    private String categoria;
    @DatabaseField
    private String concepto;

    public Movimiento() {
    }

    public Movimiento(long resumenId, String tipoMovimiento, double importe, Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        this.resumenId = resumenId;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.fechaEstimada = fechaEstimada;
        this.fechaMovimiento = fechaMovimiento;
        this.categoria = categoria;
        this.concepto = concepto;
    }

    public long getResumenId() {
        return resumenId;
    }

    public void setResumenId(long resumenId) {
        this.resumenId = resumenId;
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

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
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
}
