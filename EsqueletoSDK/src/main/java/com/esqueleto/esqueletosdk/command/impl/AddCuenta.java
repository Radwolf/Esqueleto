package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddCuenta implements AddCommand<Cuenta> {

    CuentaInteractor cuentaInteractor;
    String nombre;
    String email;

    public AddCuenta(CuentaInteractor cuentaInteractor, String nombre, String email) {
        this.cuentaInteractor = cuentaInteractor;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public Cuenta execute(Context ctx) {
        return this.cuentaInteractor.addCuenta(ctx, this.nombre, this.email);
    }

    @Override
    public void undo() {}

}
