package com.esqueleto.esqueletoui.adapter.item;

/**
 * Created by rgonzalez on 14/07/2014.
 */
public class SpinnerItem {

    int drawableResID;
    String name;
    String email;
    int cuentaId;

    public SpinnerItem(int drawableResID, String name, String email, int cuentaId) {
        super();
        this.drawableResID = drawableResID;
        this.name = name;
        this.email = email;
        this.cuentaId = cuentaId;
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
    public int getCuentaId() {
        return cuentaId;
    }
    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }
}
