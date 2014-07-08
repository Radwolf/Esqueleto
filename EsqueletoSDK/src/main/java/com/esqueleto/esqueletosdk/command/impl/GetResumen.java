package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetResumen implements GetCommand<Resumen> {

    ResumenInteractor resumenInteractor;
    Cuenta cuenta;
    String anyMes;

    public GetResumen(ResumenInteractor resumenInteractor, Cuenta cuenta, String anyMes) {
        this.resumenInteractor = resumenInteractor;
        this.cuenta = cuenta;
        this.anyMes = anyMes;
    }

    @Override
    public Resumen execute(Context ctx) {
        return this.resumenInteractor.getResumen(ctx, this.cuenta, this.anyMes);
    }

}
