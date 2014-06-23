package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CuentaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.ResumenRepositoryDBImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorResumen implements ResumenInteractor {

    public static CuentaRepositoryDB cuentaRepositoryDB;
    public static ResumenRepositoryDB resumenRepositoryDB;

    public static final double CERO_DOUBLE = (Double.valueOf("0,00")).doubleValue();

    public GestorResumen(Context ctx) {
        cuentaRepositoryDB = new CuentaRepositoryDBImpl(ctx);
        resumenRepositoryDB = new ResumenRepositoryDBImpl(ctx);
    }

    //Inicio Periodo y Fin periodo se calculará a partir de unas preferencias de la app

    @Override
    public void addResumen(Context ctx, Integer cuentaId, String anyMes, Date inicioPeriodo,
                           Date finPeriodo) {
        Cuenta cuenta = cuentaRepositoryDB.getCuenta(cuentaId);
        Resumen resumen = new Resumen();

        resumen.setAhorro(CERO_DOUBLE);
        resumen.setAhorroEstimado(CERO_DOUBLE);
        resumen.setGasto(CERO_DOUBLE);
        resumen.setGastoEstimado(CERO_DOUBLE);
        resumen.setIngreso(CERO_DOUBLE);
        resumen.setIngresoEstimado(CERO_DOUBLE);
        resumen.setSaldo(CERO_DOUBLE);
        resumen.setSaldoAnterior(CERO_DOUBLE);
        resumen.setSaldoEstimado(CERO_DOUBLE);

        resumen.setAnyMes(anyMes);
        resumen.setCuenta(cuenta);
        resumen.setInicioPeriodo(inicioPeriodo);
        resumen.setFinPeriodo(finPeriodo);

        resumenRepositoryDB.create(resumen);
    }

    @Override
    public Resumen getResumen(Context ctx, Integer id) {
        return resumenRepositoryDB.getResumen(id);
    }

    @Override
    public Resumen getResumen(Context ctx, Integer cuentaId, String anyMes){
        Cuenta cuenta = cuentaRepositoryDB.getCuenta(cuentaId);
        return resumenRepositoryDB.getResumen(cuenta, anyMes);
    }

    @Override
    public List<Resumen> getResumenes(Context ctx, Integer cuentaId) {
        Cuenta cuenta = cuentaRepositoryDB.getCuenta(cuentaId);
        return resumenRepositoryDB.getResumenes(cuenta);
    }
}
