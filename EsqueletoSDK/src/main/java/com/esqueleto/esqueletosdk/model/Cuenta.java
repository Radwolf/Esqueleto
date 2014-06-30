package com.esqueleto.esqueletosdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "cuenta")
public class Cuenta implements Parcelable{

    public static final String COLUMN_NAME_ID = "cuenta_id";
    public static final String COLUMN_NAME_USUARIO = "usuario_id";
    private static final String COLUMN_NAME_DATESINC = "date_sync";
    private static final String COLUMN_NAME_NOMBRE = "nombre";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(foreign = true, columnName = COLUMN_NAME_USUARIO, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=2)
    private Usuario usuario;
    @DatabaseField(columnName = COLUMN_NAME_DATESINC)
    private Date dateSinc;
    @DatabaseField(columnName = COLUMN_NAME_NOMBRE)
    private String nombre;

    public static final Parcelable.Creator<Cuenta> CREATOR =
        new Parcelable.Creator<Cuenta>()
        {
            @Override
            public Cuenta createFromParcel(Parcel parcel)
            {
                return new Cuenta(parcel);
            }

            @Override
            public Cuenta[] newArray(int size)
            {
                return new Cuenta[size];
            }
        };

    public Cuenta() {
    }

    public Cuenta(Parcel parcel)
    {
        //seguir el mismo orden que el usado en el método writeToParcel
        this._id = parcel.readInt();
        this.usuario = parcel.readParcelable(Usuario.class.getClassLoader());
        this.dateSinc = (Date) parcel.readSerializable();
        this.nombre = parcel.readString();
    }

    public Cuenta(int _id, Usuario usuario, Date dateSinc, String nombre) {
        this._id = _id;
        this.usuario = usuario;
        this.dateSinc = dateSinc;
        this.nombre = nombre;
    }

    //<editor-fold desc="GETTERS">
    public int get_id() {
        return _id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getDateSinc() {
        return dateSinc;
    }

    public String getNombre() {
        return nombre;
    }
    //</editor-fold>

    //<editor-fold desc="SETTERS">
    public void set_id(int _id) {
        this._id = _id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setDateSinc(Date dateSinc) {
        this.dateSinc = dateSinc;
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
        parcel.writeParcelable(this.usuario, flags);
        parcel.writeSerializable(this.dateSinc);
        parcel.writeString(this.nombre);
    }
}
