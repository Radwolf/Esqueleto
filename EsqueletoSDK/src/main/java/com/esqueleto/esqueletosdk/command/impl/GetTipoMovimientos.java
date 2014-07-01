package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.TipoDatoInteractor;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;

import java.util.List;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetTipoMovimientos implements GetCommand<List<TipoMovimiento>> {

    TipoDatoInteractor tipoDatoInteractor;

    public GetTipoMovimientos(TipoDatoInteractor tipoDatoInteractor) {
        this.tipoDatoInteractor = tipoDatoInteractor;
    }

    @Override
    public List<TipoMovimiento> execute(Context ctx) {
        return this.tipoDatoInteractor.getTipoMovimientos(ctx);
    }

}
