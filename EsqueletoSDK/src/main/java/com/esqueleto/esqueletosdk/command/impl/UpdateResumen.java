package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.UpdateCommand;
import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;
import com.esqueleto.esqueletosdk.model.Resumen;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class UpdateResumen implements UpdateCommand<Resumen> {

    ResumenInteractor resumenInteractor;
    Resumen resumen;

    public UpdateResumen(ResumenInteractor resumenInteractor, Resumen resumen) {
        this.resumenInteractor = resumenInteractor;
        this.resumen = resumen;
    }

    @Override
    public Resumen execute(Context ctx) {
        return this.resumenInteractor.updateResumen(this.resumen);
    }

    @Override
    public void undo() {}

}
