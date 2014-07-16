package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.Date;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddResumen implements AddCommand<Resumen> {

    ResumenInteractor resumenInteractor;
    Cuenta cuenta;
    String anyMes;

    public AddResumen(ResumenInteractor resumenInteractor, Cuenta cuenta, String anyMes) {
        this.resumenInteractor = resumenInteractor;
        this.cuenta = cuenta;
        this.anyMes = anyMes;
    }

    @Override
    public Resumen execute(Context ctx) {
        return this.resumenInteractor.addResumen(ctx, this.cuenta, this.anyMes);
    }

    @Override
    public void undo() {}

}
