package com.esqueleto.esqueletosdk.iteractor;

import android.database.Cursor;

import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface MovimientoInteractor {

    Movimiento addMovimiento(Resumen resumen, String tipoMovimiento, double importe,
                             Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto);
    Movimiento getMovimiento(Integer id);
    List<Movimiento> getMovimientosByAnyMes(String anyMes);
    List<Movimiento> getMovimientosByTipo(String tipoMovimiento);
    List<Movimiento> getMovimientosByCategoria(String categoria);
    List<Movimiento> getMovimientosByTipoInMes(String tipoMovimiento, String anyMes);
    List<Movimiento> getMovimientosByCategoriaInMes(String categoria, String anyMes);
    Cursor getCursorMovimientosByAnyMes(String anyMes);
    Movimiento updateMovimiento(Integer id, String tipoMovimiento, double importe,
                                Date fechaEstimada, Date fechaMovimiento, String categoria,
                                String concepto);
    Movimiento confirmMovimiento(Integer id, double importe, Date fechaMovimiento);
    void deleteMovimiento(Integer id);

}
