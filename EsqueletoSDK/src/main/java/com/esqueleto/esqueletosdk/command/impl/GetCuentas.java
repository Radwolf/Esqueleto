package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GetCuentas implements GetCommand<List<Cuenta>> {

    CuentaInteractor cuentaInteractor;
    String email;

    public GetCuentas(CuentaInteractor cuentaInteractor, String email) {
        this.cuentaInteractor = cuentaInteractor;
        this.email = email;
    }

    @Override
    public List<Cuenta> execute(Context ctx) {
        return this.cuentaInteractor.getCuentas(ctx, email);
    }

}