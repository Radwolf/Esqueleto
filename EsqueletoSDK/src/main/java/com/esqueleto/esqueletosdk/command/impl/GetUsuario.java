package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.UsuarioInteractor;
import com.esqueleto.esqueletosdk.model.Usuario;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetUsuario implements GetCommand<Usuario> {

    UsuarioInteractor usuarioInteractor;
    String email;

    public GetUsuario(UsuarioInteractor usuarioInteractor, String email) {
        this.usuarioInteractor = usuarioInteractor;
        this.email = email;
    }

    @Override
    public Usuario execute(Context ctx) {
        return this.usuarioInteractor.getUsuario(ctx, email);
    }

}
