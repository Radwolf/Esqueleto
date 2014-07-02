package com.esqueleto.esqueletosdk.iteractor;

import android.content.Context;

import com.esqueleto.esqueletosdk.model.Usuario;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public interface UsuarioInteractor {

    Usuario addUsuario(Context ctx, String email);
    Usuario getUsuario(Context ctx, String email);

}
