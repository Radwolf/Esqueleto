package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.Date;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddMovimiento implements AddCommand<Movimiento> {

    MovimientoInteractor movimientoInteractor;
    Integer _id;
    Resumen resumen;
    String tipoMovimiento;
    double importe;
    Date fechaEstimada;
    Date fechaMovimiento;
    String categoria;
    String concepto;

    public AddMovimiento(MovimientoInteractor movimientoInteractor, Resumen resumen,
                         String tipoMovimiento, double importe, Date fechaEstimada, Date fechaMovimiento,
                         String categoria, String concepto) {
        this.movimientoInteractor = movimientoInteractor;
        this.resumen = resumen;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.fechaEstimada = fechaEstimada;
        this.fechaMovimiento = fechaMovimiento;
        this.categoria = categoria;
        this.concepto = concepto;
    }

    @Override
    public Movimiento execute(Context ctx) {
        return this.movimientoInteractor.addMovimiento(this.resumen, this.tipoMovimiento, this.importe,
                this.fechaEstimada, this.fechaMovimiento, this.categoria, this.concepto);
    }

    @Override
    public void undo() {}

}
