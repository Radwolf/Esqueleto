package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Raúl on 27/03/2014.
 */
public class Basic {

    public static final String COLUMN_NAME_ID = "_id";
    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField
    private String dateCreate;
    @DatabaseField
    private String dateUpdate;
    @DatabaseField
    private String userCreate;
    @DatabaseField
    private String userUpdate;

    public Basic() {
    }

    public Basic(int _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate) {
        this._id = _id;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
