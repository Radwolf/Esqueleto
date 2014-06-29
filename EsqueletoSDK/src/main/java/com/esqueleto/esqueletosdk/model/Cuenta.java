package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "cuenta")
public class Cuenta implements Serializable{

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

    public Cuenta() {
    }

    public Cuenta(int _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate, Usuario usuario, Date dateSinc, String nombre) {
        this._id = _id;
        this.usuario = usuario;
        this.dateSinc = dateSinc;
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDateSinc() {
        return dateSinc;
    }

    public void setDateSinc(Date dateSinc) {
        this.dateSinc = dateSinc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Cuenta cuenta = (Cuenta) o;

        if (_id != cuenta._id) return false;
        if (dateSinc != null ? !dateSinc.equals(cuenta.dateSinc) : cuenta.dateSinc != null)
            return false;
        if (nombre != null ? !nombre.equals(cuenta.nombre) : cuenta.nombre != null) return false;
        if (usuario != null ? !usuario.equals(cuenta.usuario) : cuenta.usuario != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = usuario != null ? usuario.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
