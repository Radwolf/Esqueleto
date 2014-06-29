package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable{

    public static final String COLUMN_NAME_ID = "usuario_id";
    public static final String COLUMN_NAME_EMAIL = "email";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_EMAIL)
    private String email;

    public Usuario() {
    }

    public Usuario(int _id, String email, String dateCreate, String dateUpdate, String userCreate, String userUpdate) {
        this._id = _id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
