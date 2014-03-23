package com.esqueleto.esqueletosdk.model;

/**
 * Created by rgonzalez on 20/02/14.
 */
public class Usuario {

    private long _id;
    private String email;
    private String dateCreate;
    private String dateUpdate;
    private String userCreate;
    private String userUpdate;

    public Usuario() {
    }

    public Usuario(long _id, String email, String dateCreate, String dateUpdate, String userCreate, String userUpdate) {
        this._id = _id;
        this.email = email;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }
}
