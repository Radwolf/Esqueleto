package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.UpdateCommand;
import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class UpdateCuentaSeleccionada implements UpdateCommand<Cuenta> {

    CuentaInteractor cuentaInteractor;
    Cuenta cuenta;
    String email;

    public UpdateCuentaSeleccionada(CuentaInteractor cuentaInteractor, Cuenta cuenta, String email) {
        this.cuentaInteractor = cuentaInteractor;
        this.cuenta = cuenta;
        this.email = email;
    }

    @Override
    public Cuenta execute(Context ctx) {
        return this.cuentaInteractor.updateCuentaSeleccionada(this.cuenta, this.email);
    }

    @Override
    public void undo() {}

}
