package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.TipoDatoInteractor;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetTipoMovimiento implements GetCommand<TipoMovimiento> {

    TipoDatoInteractor tipoDatoInteractor;
    String clave;

    public GetTipoMovimiento(TipoDatoInteractor tipoDatoInteractor, String clave) {
        this.tipoDatoInteractor = tipoDatoInteractor;
        this.clave = clave;
    }

    @Override
    public TipoMovimiento execute(Context ctx) {
        return this.tipoDatoInteractor.getTipoMovimiento(ctx, this.clave);
    }

}
