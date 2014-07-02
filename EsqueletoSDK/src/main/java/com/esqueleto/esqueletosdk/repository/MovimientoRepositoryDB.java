package com.esqueleto.esqueletosdk.repository;

import android.database.Cursor;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Usuario;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface MovimientoRepositoryDB {

    int create(Movimiento movimiento);

    int update(Movimiento movimiento);

    int delete(Movimiento movimiento);

    Movimiento getMovimiento(Integer id);

    List<Movimiento> getMovimientosByAnyMes(String anyMes);

    List<Movimiento> getMovimientosByCategoria(String claveDiccionario);

    List<Movimiento> getMovimientosByTipo(String claveDiccionario);

    Cursor getCursorMovimientosByAnyMes(String anyMes);
}
