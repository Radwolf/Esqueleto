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

    public GestorResumen(Context ctx) {
        cuentaRepositoryDB = new CuentaRepositoryDBImpl(ctx);
        resumenRepositoryDB = new ResumenRepositoryDBImpl(ctx);
    }

    //Inicio Periodo y Fin periodo se calculará a partir de unas preferencias de la app

    @Override
    public Resumen addResumen(Context ctx, Integer cuentaId, String anyMes, Date inicioPeriodo,
                           Date finPeriodo, double ahorro, double ahorroEstimado, double gasto,
                           double gastoEstimado, double ingreso, double ingresoEstimado, double saldo,
                           double saldoAnterior, double saldoEstimado) {
        Cuenta cuenta = cuentaRepositoryDB.getCuenta(cuentaId);
        Resumen resumen = new Resumen();

        resumen.setAhorro(ahorro);
        resumen.setAhorroEstimado(ahorroEstimado);
        resumen.setGasto(gasto);
        resumen.setGastoEstimado(gastoEstimado);
        resumen.setIngreso(ingreso);
        resumen.setIngresoEstimado(ingresoEstimado);
        resumen.setSaldo(saldo);
        resumen.setSaldoAnterior(saldoAnterior);
        resumen.setSaldoEstimado(saldoEstimado);

        resumen.setAnyMes(anyMes);
        resumen.setCuenta(cuenta);
        resumen.setInicioPeriodo(inicioPeriodo);
        resumen.setFinPeriodo(finPeriodo);

        resumenRepositoryDB.create(resumen);

        return resumen;
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
