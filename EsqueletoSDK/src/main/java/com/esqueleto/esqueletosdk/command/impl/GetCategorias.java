package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.TipoDatoInteractor;
import com.esqueleto.esqueletosdk.model.Categoria;

import java.util.List;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class GetCategorias implements GetCommand<List<Categoria>> {

    TipoDatoInteractor tipoDatoInteractor;

    public GetCategorias(TipoDatoInteractor tipoDatoInteractor) {
        this.tipoDatoInteractor = tipoDatoInteractor;
    }

    @Override
    public List<Categoria> execute(Context ctx) {
        return this.tipoDatoInteractor.getCategorias(ctx);
    }

}
