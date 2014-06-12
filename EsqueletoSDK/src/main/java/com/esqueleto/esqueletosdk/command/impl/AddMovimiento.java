package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;

import java.util.Date;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddMovimiento implements AddCommand {

    MovimientoInteractor movimientoInteractor;
    long _id;
    long resumenId;
    String tipoMovimiento;
    double importe;
    Date fechaEstimada;
    Date fechaMovimiento;
    String categoria;
    String concepto;

    public AddMovimiento(MovimientoInteractor movimientoInteractor, long resumenId, String tipoMovimiento,
                         double importe, Date fechaEstimada, Date fechaMovimiento, String categoria,
                         String concepto) {
        this.movimientoInteractor = movimientoInteractor;
        this.resumenId = resumenId;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.fechaEstimada = fechaEstimada;
        this.fechaMovimiento = fechaMovimiento;
        this.categoria = categoria;
        this.concepto = concepto;
    }

    @Override
    public void execute(Context ctx) {
        this._id = this.movimientoInteractor.addMovimiento(ctx, this.resumenId, this.tipoMovimiento, this.importe,
                this.fechaEstimada, this.fechaMovimiento, this.categoria, this.concepto);
    }

    @Override
    public void undo() {}

}
