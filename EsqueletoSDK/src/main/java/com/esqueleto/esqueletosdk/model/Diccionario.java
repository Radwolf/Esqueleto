package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Raúl on 03/04/2014.
 */
@DatabaseTable(tableName = "diccionario")
public class Diccionario extends Basic {

    @DatabaseField
    private String clave;
    @DatabaseField
    private String valor;
    @DatabaseField
    private String tipo;

    public Diccionario() {
    }

    public Diccionario(long _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate, String clave, String valor, String tipo) {
        super(_id, dateCreate, dateUpdate, userCreate, userUpdate);
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
}
