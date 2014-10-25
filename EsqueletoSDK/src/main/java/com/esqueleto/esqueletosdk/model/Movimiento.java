package com.esqueleto.esqueletosdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "movimiento")
public class Movimiento implements Parcelable{

    public static final String COLUMN_NAME_ID = "movimiento_id";
    public static final String COLUMN_NAME_IMPORTE = "importe";
    public static final String COLUMN_NAME_IMPORTEESTIMADO = "importe_estimado";
    public static final String COLUMN_NAME_FECHAESTIMADA = "fecha_estimada";
    public static final String COLUMN_NAME_FECHAMOVIMIENTO = "fecha_movimiento";
    public static final String COLUMN_NAME_CONCEPTO = "concepto";
    public static final String COLUMN_NAME_TIPOMOVIMIENTO = "tipo_movimiento_id";
    public static final String COLUMN_NAME_CATEGORIA = "categoria_id";
    public static final String COLUMN_NAME_RESUMEN = "resumen_id";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_TIPOMOVIMIENTO, foreignAutoRefresh=true, canBeNull=true, maxForeignAutoRefreshLevel=2)
    private TipoMovimiento tipoMovimiento;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_IMPORTE)
    private double importe;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_IMPORTEESTIMADO)
    private double importeEstimado;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_FECHAESTIMADA)
    private Date fechaEstimada;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_FECHAMOVIMIENTO)
    private Date fechaMovimiento;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_CATEGORIA, foreignAutoRefresh=true, canBeNull=true, maxForeignAutoRefreshLevel=2)
    private Categoria categoria;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_CONCEPTO)
    private String concepto;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_RESUMEN, foreignAutoRefresh=true, canBeNull=true, maxForeignAutoRefreshLevel=2)
    private Resumen resumen;

    public static final Creator<Movimiento> CREATOR =
        new Creator<Movimiento>()
        {
            @Override
            public Movimiento createFromParcel(Parcel parcel)
            {
                return new Movimiento(parcel);
            }

            @Override
            public Movimiento[] newArray(int size)
            {
                return new Movimiento[size];
            }
        };

    public Movimiento() {
    }

    public Movimiento(Parcel parcel)
    {
        //seguir el mismo orden que el usado en el método writeToParcel
        this._id = parcel.readInt();
        this.tipoMovimiento = parcel.readParcelable(TipoMovimiento.class.getClassLoader());
        this.importe = parcel.readDouble();
        this.importeEstimado = parcel.readDouble();
        this.fechaEstimada = (Date) parcel.readSerializable();
        this.fechaMovimiento = (Date) parcel.readSerializable();
        this.categoria = parcel.readParcelable(Categoria.class.getClassLoader());
        this.concepto = parcel.readString();
        this.resumen = parcel.readParcelable(Resumen.class.getClassLoader());
    }

    public Movimiento(int _id, TipoMovimiento tipoMovimiento, double importe, double importeEstimado,
                      Date fechaEstimada, Date fechaMovimiento, Categoria categoria, String concepto,
                      Resumen resumen) {
        this._id = _id;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.importeEstimado = importeEstimado;
        this.fechaEstimada = fechaEstimada;
        this.fechaMovimiento = fechaMovimiento;
        this.categoria = categoria;
        this.concepto = concepto;
        this.resumen = resumen;
    }

    //<editor-fold desc="GETTERS">
    public int get_id() {
        return _id;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public double getImporte() {
        return importe;
    }

    public double getImporteEstimado() {
        return importeEstimado;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getConcepto() {
        return concepto;
    }

    public Resumen getResumen() {
        return resumen;
    }
    //</editor-fold>

    //<editor-fold desc="SETTERS">
    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public void setImporteEstimado(double importeEstimado) {
        this.importeEstimado = importeEstimado;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setResumen(Resumen resumen) {
        this.resumen = resumen;
    }
    //</editor-fold>

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this._id);
        parcel.writeParcelable(this.tipoMovimiento, flags);
        parcel.writeDouble(this.importe);
        parcel.writeDouble(this.importeEstimado);
        parcel.writeSerializable(this.fechaEstimada);
        parcel.writeSerializable(this.fechaMovimiento);
        parcel.writeParcelable(this.categoria, flags);
        parcel.writeString(this.concepto);
        parcel.writeParcelable(this.resumen, flags);
    }
}
