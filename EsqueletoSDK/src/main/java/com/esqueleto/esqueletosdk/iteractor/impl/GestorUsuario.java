package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.UsuarioInteractor;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.UsuarioRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.UsuarioRepositoryDBImpl;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GestorUsuario implements UsuarioInteractor {

    public static UsuarioRepositoryDB usuarioRepositoryDB;

    public GestorUsuario(Context ctx) {
        usuarioRepositoryDB = new UsuarioRepositoryDBImpl(ctx);
    }

    @Override
    public Usuario addUsuario(Context ctx, String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuarioRepositoryDB.create(usuario);
        return usuario;
    }

    @Override
    public Usuario getUsuario(Context ctx, String email) {
        return usuarioRepositoryDB.getUsuario(email);
    }
}
