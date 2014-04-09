package com.esqueleto.esqueletosdk.storage.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.model.Usuario;

import java.sql.SQLException;

/**
 * Created by rgonzalez on 09/04/2014.
 */
public class UsuarioStorageImpl extends StorageAbstractImpl<Usuario, Integer>{

    public UsuarioStorageImpl(Context ctx) {
        super(ctx);
        try {
            this.dao = getDb().getUsuarioDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
