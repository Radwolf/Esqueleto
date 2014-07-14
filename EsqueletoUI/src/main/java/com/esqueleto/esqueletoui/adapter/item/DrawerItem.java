package com.esqueleto.esqueletoui.adapter.item;

/**
 * Created by rgonzalez on 14/07/2014.
 */
public class DrawerItem {
    String ItemName;
    int imgResID;
    String title;
    boolean isSpinner;

    public DrawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }

    public DrawerItem(boolean isSpinner) {
        this(null, 0);
        this.isSpinner = isSpinner;
    }

    public DrawerItem(String title) {
        this(null, 0);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSpinner() {
        return isSpinner;
    }

    public void setSpinner(boolean isSpinner) {
        this.isSpinner = isSpinner;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

}
