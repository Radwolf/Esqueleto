package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Diccionario;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletosdk.repository.CategoriaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.DiccionarioRepositoryDB;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.TipoMovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CategoriaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.DiccionarioRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.MovimientoRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.ResumenRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.TipoMovimientoRepositoryDBImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorMovimiento implements MovimientoInteractor {

    public static MovimientoRepositoryDB movimientoRepositoryDB;
    public static ResumenRepositoryDB resumenRepositoryDB;
    public static CategoriaRepositoryDB categoriaRepositoryDB;
    public static TipoMovimientoRepositoryDB tipoMovimientoRepositoryDB;

    public GestorMovimiento(Context ctx) {
        movimientoRepositoryDB = new MovimientoRepositoryDBImpl(ctx);
        resumenRepositoryDB = new ResumenRepositoryDBImpl(ctx);
        categoriaRepositoryDB = new CategoriaRepositoryDBImpl(ctx);
        tipoMovimientoRepositoryDB = new TipoMovimientoRepositoryDBImpl(ctx);
    }

    @Override
    public int addMovimiento(Context ctx, Integer resumenId, String tipoMovimiento, double importe,
                              Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        Movimiento movimiento = new Movimiento();
        Resumen resumen = resumenRepositoryDB.getResumen(resumenId);
        Categoria dCategoria = categoriaRepositoryDB.getCategoriaByClave(categoria);
        TipoMovimiento dTipoMovimiento = tipoMovimientoRepositoryDB.getTipoMovimientoByClave(tipoMovimiento);
        movimiento.setCategoria(dCategoria);
        movimiento.setTipoMovimiento(dTipoMovimiento);
        movimiento.setConcepto(concepto);
        movimiento.setResumen(resumen);
        movimiento.setFechaEstimada(fechaEstimada);
        movimiento.setFechaMovimiento(fechaMovimiento);
        movimiento.setImporte(importe);
        //Faltará la lógica para actualizar el resumen.
        return movimientoRepositoryDB.create(movimiento);
    }

    @Override
    public Movimiento getMovimiento(Context ctx, Integer id) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByAnyMes(Context ctx, String anyMes) {
        return movimientoRepositoryDB.getMovimientosByAnyMes(anyMes);
    }

    @Override
    public List<Movimiento> getMovimientosByTipo(Context ctx, String tipoMovimiento) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByCategoria(Context ctx, String categoria) {
        return movimientoRepositoryDB.getMovimientosByCategoria(categoria);
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
