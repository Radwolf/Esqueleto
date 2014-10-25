package com.esqueleto.esqueletosdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "tipo_movimiento")
public class TipoMovimiento implements Parcelable{

    public static final String COLUMN_NAME_ID = "tipo_movimiento_id";
    public static final String COLUMN_NAME_CLAVE= "clave";
    private static final String COLUMN_NAME_NOMBRE = "nombre";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_CLAVE)
    private String clave;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_NOMBRE)
    private String nombre;

    public static final Creator<TipoMovimiento> CREATOR =
        new Creator<TipoMovimiento>()
        {
            @Override
            public TipoMovimiento createFromParcel(Parcel parcel)
            {
                return new TipoMovimiento(parcel);
            }

            @Override
            public TipoMovimiento[] newArray(int size)
            {
                return new TipoMovimiento[size];
            }
        };

    public TipoMovimiento() {
    }

    public TipoMovimiento(Parcel parcel)
    {
        //seguir el mismo orden que el usado en el método writeToParcel
        this._id = parcel.readInt();
        this.clave = parcel.readString();
        this.nombre = parcel.readString();
    }

    public TipoMovimiento(int _id, String clave, String nombre) {
        this._id = _id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public TipoMovimiento(String clave, String nombre) {
        this.clave = clave;
        this.nombre = nombre;
    }

    //<editor-fold desc="GETTERS">
    public int get_id() {
        return _id;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }
    //</editor-fold>

    //<editor-fold desc="SETTERS">
    public void set_id(int _id) {
        this._id = _id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //</editor-fold>


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this._id);
        parcel.writeString(this.clave);
        parcel.writeString(this.nombre);
    }
}
