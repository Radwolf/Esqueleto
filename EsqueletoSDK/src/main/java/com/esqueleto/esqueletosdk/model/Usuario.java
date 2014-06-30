package com.esqueleto.esqueletosdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "usuario")
public class Usuario implements Parcelable{

    public static final String COLUMN_NAME_ID = "usuario_id";
    public static final String COLUMN_NAME_EMAIL = "email";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_EMAIL)
    private String email;

    public static final Parcelable.Creator<Usuario> CREATOR =
        new Parcelable.Creator<Usuario>()
        {
            @Override
            public Usuario createFromParcel(Parcel parcel)
            {
                return new Usuario(parcel);
            }

            @Override
            public Usuario[] newArray(int size)
            {
                return new Usuario[size];
            }
        };

    public Usuario() {
    }

    public Usuario(Parcel parcel)
    {
        //seguir el mismo orden que el usado en el método writeToParcel
        this._id = parcel.readInt();
        this.email = parcel.readString();
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this._id);
        parcel.writeString(this.email);
    }
}
