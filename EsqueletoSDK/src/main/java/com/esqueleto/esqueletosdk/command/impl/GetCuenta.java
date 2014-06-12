package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetCuenta implements GetCommand<Cuenta> {

    CuentaInteractor cuentaInteractor;
    Integer _id;

    public GetCuenta(CuentaInteractor cuentaInteractor, Integer _id) {
        this.cuentaInteractor = cuentaInteractor;
        this._id = _id;
    }

    @Override
    public Cuenta execute(Context ctx) {
        return this.cuentaInteractor.getCuenta(ctx, this._id);
    }

}
