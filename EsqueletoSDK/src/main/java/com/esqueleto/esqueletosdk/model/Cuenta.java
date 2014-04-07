package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "cuenta")
public class Cuenta extends Basic {

    @DatabaseField
    private String email;
    @DatabaseField
    private String dateSinc;
    @DatabaseField
    private String nombre;

    public Cuenta() {
    }

    public Cuenta(long _id, String email, String dateSinc, String nombre, String dateCreate, String dateUpdate, String userCreate, String userUpdate) {
        super(_id, dateCreate, dateUpdate, userCreate, userUpdate);
        this.email = email;
        this.dateSinc = dateSinc;
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateSinc() {
        return dateSinc;
    }

    public void setDateSinc(String dateSinc) {
        this.dateSinc = dateSinc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
