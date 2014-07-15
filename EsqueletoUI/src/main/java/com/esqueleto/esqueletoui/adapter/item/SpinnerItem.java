package com.esqueleto.esqueletoui.adapter.item;

import com.esqueleto.esqueletosdk.model.Cuenta;

/**
 * Created by rgonzalez on 14/07/2014.
 */
public class SpinnerItem {

    int drawableResID;
    String name;
    String email;
    Cuenta cuenta;

    public SpinnerItem(int drawableResID, String name, String email, Cuenta cuenta) {
        super();
        this.drawableResID = drawableResID;
        this.name = name;
        this.email = email;
        this.cuenta = cuenta;
    }

    public int getDrawableResID() {
        return drawableResID;
    }
    public void setDrawableResID(int drawableResID) {
        this.drawableResID = drawableResID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Cuenta getCuenta() {
        return cuenta;
    }
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
