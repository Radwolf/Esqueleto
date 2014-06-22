package com.esqueleto.esqueletosdk.repository;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Usuario;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface MovimientoRepositoryDB {

    int create(Cuenta cuenta);

    int update(Cuenta cuenta);

    int delete(Cuenta cuenta);

    Cuenta getCuenta(Integer id);

    List<Cuenta> getCuentas(Usuario usuario);
}
