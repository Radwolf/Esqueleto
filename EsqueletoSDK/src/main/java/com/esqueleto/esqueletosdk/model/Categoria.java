package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "categoria")
public class Categoria implements Serializable {

    public static final String COLUMN_NAME_ID = "categoria_id";
    public static final String COLUMN_NAME_CLAVE= "clave";
    private static final String COLUMN_NAME_NOMBRE = "nombre";
    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_CLAVE)
    private String clave;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_NOMBRE)
    private String nombre;

    public Categoria() {
    }

    public Categoria(int _id, String clave, String nombre) {
        this._id = _id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public Categoria(String clave, String nombre) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (_id != categoria._id) return false;
        if (clave != null ? !clave.equals(categoria.clave) : categoria.clave != null) return false;
        if (nombre != null ? !nombre.equals(categoria.nombre) : categoria.nombre != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return clave != null ? clave.hashCode() : 0;
    }
}
