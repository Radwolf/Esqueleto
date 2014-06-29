package com.esqueleto.esqueletosdk.repository;

import com.esqueleto.esqueletosdk.model.Categoria;

import java.util.List;

/**
 * Created by RUL on 28/06/2014.
 */
public interface CategoriaRepositoryDB {
    int create(Categoria categoria);

    int update(Categoria categoria);

    int delete(Categoria categoria);

    Categoria getCategoria(Integer id);

    List<Categoria> getCategorias();

    Categoria getCategoriaByClave(String clave);
}
