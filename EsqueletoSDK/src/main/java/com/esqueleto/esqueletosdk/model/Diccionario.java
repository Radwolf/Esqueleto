package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "diccionario")
public class Diccionario implements Serializable{

    public static final String COLUMN_NAME_ID = "diccionario_id";
    public static final String COLUMN_NAME_CLAVE = "clave";
    private static final String COLUMN_NAME_VALOR = "valor";
    private static final String COLUMN_NAME_TIPO = "tipo";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_CLAVE)
    private String clave;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_VALOR)
    private String valor;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_TIPO)
    private String tipo;

    public Diccionario() {
    }

    public Diccionario(int _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate, String clave, String valor, String tipo) {
        this._id = _id;
        this.clave = clave;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Diccionario that = (Diccionario) o;

        if (clave != null ? !clave.equals(that.clave) : that.clave != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return clave != null ? clave.hashCode() : 0;
    }
}
