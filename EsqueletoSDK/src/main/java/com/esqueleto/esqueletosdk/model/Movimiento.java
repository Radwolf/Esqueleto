package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "movimiento")
public class Movimiento{

    public static final String COLUMN_NAME_ID = "movimiento_id";
    private static final String COLUMN_NAME_IMPORTE = "importe";
    private static final String COLUMN_NAME_FECHAESTIMADA = "fecha_estimada";
    private static final String COLUMN_NAME_FECHAMOVIMIENTO = "fecha_movimiento";
    private static final String COLUMN_NAME_CONCEPTO = "concepto";
    public static final String COLUMN_NAME_TIPOMOVIMIENTO = "tipo_movimiento_id";
    public static final String COLUMN_NAME_CATEGORIA = "categoria_id";
    public static final String COLUMN_NAME_RESUMEN = "resumen_id";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_TIPOMOVIMIENTO, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=2)
    private Diccionario tipoMovimiento;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_IMPORTE)
    private double importe;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_FECHAESTIMADA)
    private Date fechaEstimada;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_FECHAMOVIMIENTO)
    private Date fechaMovimiento;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_CATEGORIA, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=2)
    private Diccionario categoria;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_CONCEPTO)
    private String concepto;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_RESUMEN, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=2)
    private Resumen resumen;

    public Movimiento() {
    }

    public Movimiento(int _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate,
                      Resumen resumen, Diccionario tipoMovimiento, double importe, Date fechaEstimada,
                      Date fechaMovimiento, Diccionario categoria, String concepto) {
        this._id = _id;
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
