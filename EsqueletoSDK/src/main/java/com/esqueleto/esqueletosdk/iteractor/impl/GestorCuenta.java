package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CuentaRepositoryDBImpl;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorCuenta implements CuentaInteractor {

    public static CuentaRepositoryDB cuentaRepositoryDB;

    @Override
    public void addCuenta(Context ctx, String nombre, String email) {
        cuentaRepositoryDB = new CuentaRepositoryDBImpl(ctx);
        Cuenta cuenta = new Cuenta();
        cuenta.setEmail(email);
        cuenta.setNombre(nombre);
        cuentaRepositoryDB.create(cuenta);
    }

    @Override
    public Cuenta getCuenta(Context ctx, Integer id) {
        return cuentaRepositoryDB.getCuenta(id);
    }

    @Override
    public List<Cuenta> getCuentas(Context ctx, String email) {
        cuentaRepositoryDB = new CuentaRepositoryDBImpl(ctx);
        return cuentaRepositoryDB.getCuentas(email);
    }
}
