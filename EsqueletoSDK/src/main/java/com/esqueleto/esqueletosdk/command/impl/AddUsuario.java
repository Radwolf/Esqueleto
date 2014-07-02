package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.UsuarioInteractor;
import com.esqueleto.esqueletosdk.model.Usuario;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddUsuario implements AddCommand<Usuario> {

    UsuarioInteractor usuarioInteractor;
    String email;

    public AddUsuario(UsuarioInteractor usuarioInteractor, String email) {
        this.usuarioInteractor = usuarioInteractor;
        this.email = email;
    }

    @Override
    public Usuario execute(Context ctx) {
        return this.usuarioInteractor.addUsuario(ctx, email);
    }

    @Override
    public void undo() {}

}
