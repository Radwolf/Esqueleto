package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.UsuarioInteractor;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddUsuario implements AddCommand {

    UsuarioInteractor usuarioInteractor;
    String email;

    public AddUsuario(UsuarioInteractor usuarioInteractor, String email) {
        this.usuarioInteractor = usuarioInteractor;
        this.email = email;
    }

    @Override
    public void execute(Context ctx) {
        this.usuarioInteractor.addUsuario(ctx, email);
    }

    @Override
    public void undo() {}

}
