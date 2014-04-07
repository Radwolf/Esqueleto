package com.esqueleto.esqueletosdk.model;

/**
 * Created by Raúl on 03/04/2014.
 */
public class Diccionario extends Basic {

    private String clave;
    private String valor;
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
