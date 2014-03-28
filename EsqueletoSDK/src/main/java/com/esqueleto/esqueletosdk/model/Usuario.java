package com.esqueleto.esqueletosdk.model;

/**
 * Created by rgonzalez on 20/02/14.
 */
public class Usuario extends Basic {

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
