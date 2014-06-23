package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;
import com.esqueleto.esqueletosdk.model.Resumen;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetResumen implements GetCommand<Resumen> {

    ResumenInteractor resumenInteractor;
    Integer cuentaId;
    String anyMes;

    public GetResumen(ResumenInteractor resumenInteractor, Integer cuentaId, String anyMes) {
        this.resumenInteractor = resumenInteractor;
        this.cuentaId = cuentaId;
        this.anyMes = anyMes;
    }

    @Override
    public Resumen execute(Context ctx) {
        return this.resumenInteractor.getResumen(ctx, this.cuentaId, this.anyMes);
    }

}
