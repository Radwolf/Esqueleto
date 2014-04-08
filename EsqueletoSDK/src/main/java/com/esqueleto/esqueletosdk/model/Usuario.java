package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "usuario")
public class Usuario extends Basic {

    @DatabaseField
    private String email;

    public Usuario() {
    }

    public Usuario(long _id, String email, String dateCreate, String dateUpdate, String userCreate, String userUpdate) {
        super(_id, dateCreate, dateUpdate, userCreate, userUpdate);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
