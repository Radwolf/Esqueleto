package com.esqueleto.esqueletosdk.repository;

import com.esqueleto.esqueletosdk.model.Usuario;

import java.util.List;

/**
 * Created by rgonzalez on 10/04/2014.
 */
public interface UsuarioRepositoryDB {
    int create(Usuario usuario);

    int update(Usuario usuario);

    int delete(Usuario usuario);

    List<Usuario> getAll();

    Usuario findById(Integer idUsuario);

    Usuario findByEmail(String email);
}
