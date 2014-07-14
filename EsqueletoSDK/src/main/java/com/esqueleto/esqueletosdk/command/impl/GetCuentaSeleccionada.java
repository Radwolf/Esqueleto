package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetCuentaSeleccionada implements GetCommand<Cuenta> {

    CuentaInteractor cuentaInteractor;
    String email;

    public GetCuentaSeleccionada(CuentaInteractor cuentaInteractor, String email) {
        this.cuentaInteractor = cuentaInteractor;
        this.email = email;
    }

    @Override
    public Cuenta execute(Context ctx) {
        return this.cuentaInteractor.getCuentaSeleccionada(email);
    }

}
