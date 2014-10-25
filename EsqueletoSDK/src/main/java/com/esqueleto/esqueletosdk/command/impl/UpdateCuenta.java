package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.UpdateCommand;
import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class UpdateCuenta implements UpdateCommand<Cuenta> {

    CuentaInteractor cuentaInteractor;
    Cuenta cuenta;

    public UpdateCuenta(CuentaInteractor cuentaInteractor, Cuenta cuenta) {
        this.cuentaInteractor = cuentaInteractor;
        this.cuenta = cuenta;
    }

    @Override
    public Cuenta execute(Context ctx) {
        return this.cuentaInteractor.updateCuenta(this.cuenta);
    }

    @Override
    public void undo() {}

}
