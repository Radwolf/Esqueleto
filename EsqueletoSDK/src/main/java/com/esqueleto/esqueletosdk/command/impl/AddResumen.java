package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.Date;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddResumen implements AddCommand<Resumen> {

    ResumenInteractor resumenInteractor;
    Cuenta cuenta;
    String anyMes;
    Date inicioPeriodo;
    Date finPeriodo;
    double ahorro;
    double ahorroEstimado;
    double gasto;
    double gastoEstimado;
    double ingreso;
    double ingresoEstimado;
    double saldo;
    double saldoAnterior;
    double saldoEstimado;

    public AddResumen(ResumenInteractor resumenInteractor, Cuenta cuenta, String anyMes, Date inicioPeriodo,
                      Date finPeriodo, double ahorro, double ahorroEstimado, double gasto,
                      double gastoEstimado, double ingreso, double ingresoEstimado, double saldo,
                      double saldoAnterior, double saldoEstimado) {
        this.resumenInteractor = resumenInteractor;
        this.cuenta = cuenta;
        this.anyMes = anyMes;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.ahorro = ahorro;
        this.ahorroEstimado = ahorroEstimado;
        this.gasto = gasto;
        this.gastoEstimado = gastoEstimado;
        this.ingreso = ingreso;
        this.ingresoEstimado = ingresoEstimado;
        this.saldo = saldo;
        this.saldoAnterior = saldoAnterior;
        this.saldoEstimado = saldoEstimado;
    }

    @Override
    public Resumen execute(Context ctx) {
        return this.resumenInteractor.addResumen(ctx, this.cuenta, this.anyMes, this.inicioPeriodo, this.finPeriodo,
                this.ahorro, this.ahorroEstimado, this.gasto, this.gastoEstimado, this.ingreso,
                this.ingresoEstimado, this.saldo, this.saldoAnterior, this.saldoEstimado);
    }

    @Override
    public void undo() {}

}
