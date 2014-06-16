package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "movimiento")
public class Movimiento extends Basic {

    private static final String COLUMN_NAME_IMPORTE = "IMPORTE";
    private static final String COLUMN_NAME_FECHAESTIMADA = "FECHA_ESTIMADA";
    private static final String COLUMN_NAME_FECHAMOVIMIENTO = "FECHA_MOVIMIENTO";
    private static final String COLUMN_NAME_CONCEPTO = "CONCEPTO";

    @DatabaseField(canBeNull = false, foreign = true)
    private Resumen resumen;
    @DatabaseField(canBeNull = false, foreign = true)
    private Diccionario tipoMovimiento;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_IMPORTE)
    private double importe;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_FECHAESTIMADA)
    private Date fechaEstimada;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_FECHAMOVIMIENTO)
    private Date fechaMovimiento;
    @DatabaseField(canBeNull = false, foreign = true)
    private Diccionario categoria;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_CONCEPTO)
    private String concepto;

    public Movimiento() {
    }

    public Movimiento(int _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate,
                      Resumen resumen, Diccionario tipoMovimiento, double importe, Date fechaEstimada,
                      Date fechaMovimiento, Diccionario categoria, String concepto) {
        super(_id, dateCreate, dateUpdate, userCreate, userUpdate);
        this.resumen = resumen;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.fechaEstimada = fechaEstimada;
        this.fechaMovimiento = fechaMovimiento;
        this.categoria = categoria;
        this.concepto = concepto;
    }

    public Resumen getResumen() {
        return resumen;
    }

    public void setResumen(Resumen resumen) {
        this.resumen = resumen;
    }

    public Diccionario getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Diccionario tipoMovimiento) {
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

    public Diccionario getCategoria() {
        return categoria;
    }

    public void setCategoria(Diccionario categoria) {
        this.categoria = categoria;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
