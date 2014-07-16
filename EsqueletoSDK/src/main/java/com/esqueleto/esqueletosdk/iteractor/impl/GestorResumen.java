package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.ResumenInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.ResumenRepositoryDBImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorResumen implements ResumenInteractor {

    public static ResumenRepositoryDB resumenRepositoryDB;
    private final double DOUBLE_VALUE_CERO = 0.00;

    public GestorResumen(Context ctx) {
        resumenRepositoryDB = new ResumenRepositoryDBImpl(ctx);
    }

    //Inicio Periodo y Fin periodo se calculará a partir de unas preferencias de la app

    @Override
    public Resumen addResumen(Context ctx, Cuenta cuenta, String anyMes) {
        Resumen resumen = new Resumen();

        resumen.setAhorro(DOUBLE_VALUE_CERO);
        resumen.setAhorroEstimado(DOUBLE_VALUE_CERO);
        resumen.setGasto(DOUBLE_VALUE_CERO);
        resumen.setGastoEstimado(DOUBLE_VALUE_CERO);
        resumen.setIngreso(DOUBLE_VALUE_CERO);
        resumen.setIngresoEstimado(DOUBLE_VALUE_CERO);
        resumen.setSaldo(DOUBLE_VALUE_CERO);
        resumen.setSaldoAnterior(DOUBLE_VALUE_CERO);
        resumen.setSaldoEstimado(DOUBLE_VALUE_CERO);

        resumen.setAnyMes(anyMes);
        resumen.setCuenta(cuenta);
        String[] splitAnyMes = anyMes.split("/");
        //TODO: Hay que afinar el calculo del periodo
        resumen.setInicioPeriodo(stringToDate("01/" + splitAnyMes[1] + "/" + splitAnyMes[0]));
        resumen.setFinPeriodo(stringToDate("28/" + splitAnyMes[1] + "/" + splitAnyMes[0]));
        resumenRepositoryDB.create(resumen);

        return resumen;
    }

    @Override
    public Resumen addResumenCompleto(Context ctx, Cuenta cuenta, String anyMes, Date inicioPeriodo,
                              Date finPeriodo, double ahorro, double ahorroEstimado, double gasto,
                              double gastoEstimado, double ingreso, double ingresoEstimado, double saldo,
                              double saldoAnterior, double saldoEstimado) {
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
    public Resumen updateResumen(Resumen resumen){
        resumenRepositoryDB.update(resumen);
        return resumen;
    }

    @Override
    public Resumen getResumen(Context ctx, Integer id) {
        return resumenRepositoryDB.getResumen(id);
    }

    @Override
    public Resumen getResumen(Context ctx, Cuenta cuenta, String anyMes){
        return resumenRepositoryDB.getResumen(cuenta, anyMes);
    }

    @Override
    public List<Resumen> getResumenes(Context ctx, Cuenta cuenta) {
        return resumenRepositoryDB.getResumenes(cuenta);
    }

    private Date stringToDate(String sDate){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(sDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
}
