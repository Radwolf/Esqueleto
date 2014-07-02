package com.esqueleto.esqueletosdk.iteractor;

import android.content.Context;
import android.database.Cursor;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface MovimientoInteractor {

    Movimiento addMovimiento(Cuenta cuenta, String anyMes, String tipoMovimiento, double importe,
                       Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto);
    Movimiento getMovimiento(Integer id);
    List<Movimiento> getMovimientosByAnyMes(String anyMes);
    List<Movimiento> getMovimientosByTipo(String tipoMovimiento);
    List<Movimiento> getMovimientosByCategoria(String categoria);
    Cursor getCursorMovimientosByAnyMes(String anyMes);
    Movimiento updateMovimiento(Integer id, String tipoMovimiento, double importe,
                                Date fechaEstimada, Date fechaMovimiento, String categoria,
                                String concepto);
    Movimiento confirmMovimiento(Integer id, double importe, Date fechaMovimiento);
    void deleteMovimiento(Integer id);

}
