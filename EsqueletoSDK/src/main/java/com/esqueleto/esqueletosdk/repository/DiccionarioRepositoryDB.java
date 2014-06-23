package com.esqueleto.esqueletosdk.repository;

import com.esqueleto.esqueletosdk.model.Diccionario;

/**
 * Created by rgonzalez on 23/06/2014.
 */
public interface DiccionarioRepositoryDB {

    int create(Diccionario diccionario);

    int update(Diccionario diccionario);

    int delete(Diccionario diccionario);

    Diccionario getDiccionario(Integer id);

    Diccionario getDiccionarioByClave(String clave);
}
