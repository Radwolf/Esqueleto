package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.CuentaInteractor;
import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.UsuarioRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CuentaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.MovimientoRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.ResumenRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.UsuarioRepositoryDBImpl;
import com.j256.ormlite.stmt.query.In;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorMovimiento implements MovimientoInteractor {

    public static MovimientoRepositoryDB movimientoRepositoryDB;
    public static ResumenRepositoryDB resumenRepositoryDB;

    public GestorMovimiento(Context ctx) {
        movimientoRepositoryDB = new MovimientoRepositoryDBImpl(ctx);
        resumenRepositoryDB = new ResumenRepositoryDBImpl(ctx);
    }

    @Override
    public long addMovimiento(Context ctx, long resumenId, String tipoMovimiento, double importe, Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        return 0;
    }

    @Override
    public Movimiento getMovimiento(Context ctx, Integer id) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByMesAny(Context ctx, String anyMes) {
        return null;
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
