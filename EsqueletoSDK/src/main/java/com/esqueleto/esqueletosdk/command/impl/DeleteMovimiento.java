package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.DeleteCommand;
import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class DeleteMovimiento implements DeleteCommand {

    MovimientoInteractor movimientoInteractor;
    Integer _id;

    public DeleteMovimiento(MovimientoInteractor movimientoInteractor, Integer _id) {
        this.movimientoInteractor = movimientoInteractor;
        this._id = _id;
    }

    @Override
    public void execute(Context ctx) {
        this.movimientoInteractor.deleteMovimiento(ctx, this._id);
    }

    @Override
    public void undo() {}

}
