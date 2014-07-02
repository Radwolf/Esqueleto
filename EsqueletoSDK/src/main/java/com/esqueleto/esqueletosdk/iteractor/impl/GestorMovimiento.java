package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;
import android.database.Cursor;

import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletosdk.repository.CategoriaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.TipoMovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CategoriaRepositoryDBImpl;
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
    public Movimiento addMovimiento(Cuenta cuenta, String anyMes, String tipoMovimiento, double importe,
                              Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        Movimiento movimiento = new Movimiento();
        Resumen resumen = resumenRepositoryDB.getResumen(cuenta, anyMes);
        Categoria dCategoria = categoriaRepositoryDB.getCategoriaByClave(categoria);
        TipoMovimiento dTipoMovimiento = tipoMovimientoRepositoryDB.getTipoMovimientoByClave(tipoMovimiento);
        movimiento.setCategoria(dCategoria);
        movimiento.setTipoMovimiento(dTipoMovimiento);
        movimiento.setConcepto(concepto);
        movimiento.setResumen(resumen);
        movimiento.setFechaEstimada(fechaEstimada);
        movimiento.setFechaMovimiento(fechaMovimiento);
        movimiento.setImporte(importe);
        //TODO: Faltará la lógica para actualizar el resumen.
        movimientoRepositoryDB.create(movimiento);
        return movimiento;
    }

    @Override
    public Movimiento getMovimiento(Integer id) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByAnyMes(String anyMes) {
        return movimientoRepositoryDB.getMovimientosByAnyMes(anyMes);
    }

    @Override
    public List<Movimiento> getMovimientosByTipo(String tipoMovimiento) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByCategoria(String categoria) {
        return movimientoRepositoryDB.getMovimientosByCategoria(categoria);
    }

    @Override
    public Cursor getCursorMovimientosByAnyMes(String anyMes) {
        return movimientoRepositoryDB.getCursorMovimientosByAnyMes(anyMes);
    }

    @Override
    public Movimiento updateMovimiento(Integer id, String tipoMovimiento, double importe, Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        return null;
    }

    @Override
    public Movimiento confirmMovimiento(Integer id, double importe, Date fechaMovimiento) {
        return null;
    }

    @Override
    public void deleteMovimiento(Integer id) {

    }
}
