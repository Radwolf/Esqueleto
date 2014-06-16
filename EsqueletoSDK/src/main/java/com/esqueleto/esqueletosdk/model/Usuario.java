package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "usuario")
public class Usuario extends Basic {

    public static final String COLUMN_NAME_EMAIL = "EMAIL";

    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_EMAIL)
    private String email;

    public Usuario() {
    }

    public Usuario(int _id, String email, String dateCreate, String dateUpdate, String userCreate, String userUpdate) {
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
