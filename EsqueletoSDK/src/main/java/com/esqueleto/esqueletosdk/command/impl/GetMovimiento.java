package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Movimiento;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GetMovimiento implements GetCommand<Movimiento> {


    MovimientoInteractor movimientoInteractor;
    Integer id;

    public GetMovimiento(MovimientoInteractor movimientoInteractor, Integer id) {
        this.movimientoInteractor = movimientoInteractor;
        this.id = id;
    }

    @Override
    public Movimiento execute(Context ctx) {
        return this.movimientoInteractor.getMovimiento(id);
    }

}