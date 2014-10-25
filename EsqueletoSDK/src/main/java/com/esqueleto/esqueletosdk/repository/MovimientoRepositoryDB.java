package com.esqueleto.esqueletosdk.repository;

import android.database.Cursor;

import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface MovimientoRepositoryDB {

    int create(Movimiento movimiento);

    int update(Movimiento movimiento);

    int delete(Movimiento movimiento);

    void deleteAllByResumen(Resumen resumen);

    Movimiento getMovimiento(Integer id);

    List<Movimiento> getMovimientosByAnyMes(String anyMes);

    List<Movimiento> getMovimientosByCategoria(String claveDiccionario);

    List<Movimiento> getMovimientosByTipo(String claveDiccionario);

    List<Movimiento> getMovimientosByCategoriaInMes(String claveDiccionario, String anyMes);

    List<Movimiento> getMovimientosByTipoInMes(String claveDiccionario, String anyMes);

    Cursor getCursorMovimientosByAnyMes(String anyMes);
}
