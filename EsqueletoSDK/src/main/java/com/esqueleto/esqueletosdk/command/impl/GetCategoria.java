package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.TipoDatoInteractor;
import com.esqueleto.esqueletosdk.model.Categoria;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetCategoria implements GetCommand<Categoria> {

    TipoDatoInteractor tipoDatoInteractor;
    String clave;

    public GetCategoria(TipoDatoInteractor tipoDatoInteractor, String clave) {
        this.tipoDatoInteractor = tipoDatoInteractor;
        this.clave = clave;
    }

    @Override
    public Categoria execute(Context ctx) {
        return this.tipoDatoInteractor.getCategoria(ctx, this.clave);
    }

}
