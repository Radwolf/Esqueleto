package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.UsuarioRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CuentaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.UsuarioRepositoryDBImpl;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorCuenta implements CuentaInteractor {

    public static CuentaRepositoryDB cuentaRepositoryDB;
    public static UsuarioRepositoryDB usuarioRepositoryDB;

    public GestorCuenta(Context ctx) {
        cuentaRepositoryDB = new CuentaRepositoryDBImpl(ctx);
        usuarioRepositoryDB = new UsuarioRepositoryDBImpl(ctx);
    }

    @Override
    public Cuenta addCuenta(Context ctx, String nombre, String email) {
        Usuario usuario = usuarioRepositoryDB.getUsuario(email);
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(usuario);
        cuenta.setNombre(nombre);
        cuentaRepositoryDB.create(cuenta);
        return cuenta;
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta){
        cuentaRepositoryDB.update(cuenta);
        return cuenta;
    }

    @Override
    public Cuenta getCuenta(Context ctx, Integer id) {
        return cuentaRepositoryDB.getCuenta(id);
    }

    @Override
    public List<Cuenta> getCuentas(Context ctx, String email) {
        return cuentaRepositoryDB.getCuentas(email);
    }
}
