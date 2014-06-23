package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.AddCommand;
import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;

import java.util.Date;

/**
 * Created by rgonzalez on 24/04/2014.
 */
public class AddResumen implements AddCommand {

    ResumenInteractor resumenInteractor;
    Integer cuentaId;
    String anyMes;
    Date inicioPeriodo;
    Date finPeriodo;

    public AddResumen(ResumenInteractor resumenInteractor, Integer cuentaId, String anyMes, Date inicioPeriodo,
                      Date finPeriodo) {
        this.resumenInteractor = resumenInteractor;
        this.cuentaId = cuentaId;
        this.anyMes = anyMes;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
    }

    @Override
    public void execute(Context ctx) {
        this.resumenInteractor.addResumen(ctx, cuentaId, this.anyMes, this.inicioPeriodo, this.finPeriodo);
    }

    @Override
    public void undo() {}

}
