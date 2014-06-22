package com.esqueleto.esqueletosdk.repository;

import com.esqueleto.esqueletosdk.model.Movimiento;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface ResumenRepositoryDB {

    int create(Movimiento movimiento);

    int update(Movimiento movimiento);

    int delete(Movimiento movimiento);

    Movimiento getMovimiento(Integer id);

    List<Movimiento> getMovimientosByMesAny(String anyMes);

    List<Movimiento> getMovimientosByCategoria(String claveDiccionario);

    List<Movimiento> getMovimientosByTipo(String claveDiccionario);

}
