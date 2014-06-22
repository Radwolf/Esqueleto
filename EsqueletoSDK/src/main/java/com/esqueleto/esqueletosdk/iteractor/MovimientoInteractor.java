package com.esqueleto.esqueletosdk.iteractor;

import android.content.Context;

import com.esqueleto.esqueletosdk.model.Movimiento;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface MovimientoInteractor {

    long addMovimiento(Context ctx, long resumenId, String tipoMovimiento, double importe,
                       Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto);
    Movimiento getMovimiento(Context ctx, Integer id);
    List<Movimiento> getMovimientosByMesAny(Context ctx, String anyMes);
    List<Movimiento> getMovimientosByTipo(Context ctx, String tipoMovimiento);
    List<Movimiento> getMovimientosByCategoria(Context ctx, String categoria);
    Movimiento updateMovimiento(Context ctx, Integer id, String tipoMovimiento, double importe,
                                Date fechaEstimada, Date fechaMovimiento, String categoria,
                                String concepto);
    Movimiento confirmMovimiento(Context ctx, Integer id, double importe, Date fechaMovimiento);
    void deleteMovimiento(Context ctx, Integer id);

}