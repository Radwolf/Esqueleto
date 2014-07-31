package com.esqueleto.esqueletosdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "presupuesto")
public class Presupuesto implements Parcelable{

    public static final String COLUMN_NAME_ID = "presupuesto_id";
    public static final String COLUMN_NAME_PRESUPUESTO = "presupuesto";
    public static final String COLUMN_NAME_PRESUPUESTOESTIMADO = "presupuesto_estimado";
    public static final String COLUMN_NAME_PRESUPUESTOINICIAL = "presupuesto_inicial";
    public static final String COLUMN_NAME_DATESYNC = "date_sync";
    public static final String COLUMN_NAME_CATEGORIA = "categoria_id";
    public static final String COLUMN_NAME_RESUMEN = "resumen_id";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_PRESUPUESTO)
    private double presupuesto;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_PRESUPUESTOESTIMADO)
    private double presupuestoEstimado;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_PRESUPUESTOINICIAL)
    private double presupuestoInicial;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_DATESYNC)
    private Date dateSync;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_CATEGORIA, foreignAutoRefresh=true, canBeNull=true, maxForeignAutoRefreshLevel=2)
    private Categoria categoria;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_RESUMEN, foreignAutoRefresh=true, canBeNull=true, maxForeignAutoRefreshLevel=2)
    private Resumen resumen;

    public static final Creator<Presupuesto> CREATOR =
        new Creator<Presupuesto>()
        {
            @Override
            public Presupuesto createFromParcel(Parcel parcel)
            {
                return new Presupuesto(parcel);
            }

            @Override
            public Presupuesto[] newArray(int size)
            {
                return new Presupuesto[size];
            }
        };

    public Presupuesto() {
    }

    public Presupuesto(Parcel parcel)
    {
        //seguir el mismo orden que el usado en el método writeToParcel
        this._id = parcel.readInt();
        this.presupuesto = parcel.readDouble();
        this.presupuestoEstimado = parcel.readDouble();
        this.presupuestoInicial = parcel.readDouble();
        this.fechaEstimada = (Date) parcel.readSerializable();
        this.fechaMovimiento = (Date) parcel.readSerializable();
        this.categoria = parcel.readParcelable(Categoria.class.getClassLoader());
        this.concepto = parcel.readString();
        this.resumen = parcel.readParcelable(Resumen.class.getClassLoader());
    }

    public Presupuesto(int _id, TipoMovimiento tipoMovimiento, double importe, double importeEstimado,
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
