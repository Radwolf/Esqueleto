package com.esqueleto.esqueletosdk.iteractor;

import android.content.Context;

import com.esqueleto.esqueletosdk.model.Cuenta;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface CuentaInteractor {

    void addCuenta(Context ctx, String nombre, String email);
    Cuenta getCuenta(Context ctx, Integer id);
    List<Cuenta> getCuentas(Context ctx, String email);

}
