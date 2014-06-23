package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Diccionario;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.repository.DiccionarioRepositoryDB;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.DiccionarioRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.MovimientoRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.ResumenRepositoryDBImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorMovimiento implements MovimientoInteractor {

    public static MovimientoRepositoryDB movimientoRepositoryDB;
    public static ResumenRepositoryDB resumenRepositoryDB;
    public static DiccionarioRepositoryDB diccionarioRepositoryDB;

    public GestorMovimiento(Context ctx) {
        movimientoRepositoryDB = new MovimientoRepositoryDBImpl(ctx);
        resumenRepositoryDB = new ResumenRepositoryDBImpl(ctx);
        diccionarioRepositoryDB = new DiccionarioRepositoryDBImpl(ctx);
    }

    @Override
    public int addMovimiento(Context ctx, Integer resumenId, String tipoMovimiento, double importe,
                              Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        Movimiento movimiento = new Movimiento();
        Resumen resumen = resumenRepositoryDB.getResumen(resumenId);
        Diccionario dCategoria = diccionarioRepositoryDB.getDiccionarioByClave(categoria);
        Diccionario dTipoMovimiento = diccionarioRepositoryDB.getDiccionarioByClave(tipoMovimiento);
        movimiento.setCategoria(dCategoria);
        movimiento.setTipoMovimiento(dTipoMovimiento);
        movimiento.setConcepto(concepto);
        movimiento.setResumen(resumen);
        movimiento.setFechaEstimada(fechaEstimada);
        movimiento.setFechaMovimiento(fechaMovimiento);
        return movimientoRepositoryDB.create(movimiento);
    }

    @Override
    public Movimiento getMovimiento(Context ctx, Integer id) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByMesAny(Context ctx, String anyMes) {
        return movimientoRepositoryDB.getMovimientosByMesAny(anyMes);
    }

    @Override
    public List<Movimiento> getMovimientosByTipo(Context ctx, String tipoMovimiento) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByCategoria(Context ctx, String categoria) {
        return null;
    }

    @Override
    public Movimiento updateMovimiento(Context ctx, Integer id, String tipoMovimiento, double importe, Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        return null;
    }

    @Override
    public Movimiento confirmMovimiento(Context ctx, Integer id, double importe, Date fechaMovimiento) {
        return null;
    }

    @Override
    public void deleteMovimiento(Context ctx, Integer id) {

    }
}
