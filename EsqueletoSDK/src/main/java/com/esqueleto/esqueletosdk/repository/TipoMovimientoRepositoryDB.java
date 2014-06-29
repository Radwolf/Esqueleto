package com.esqueleto.esqueletosdk.repository;

import com.esqueleto.esqueletosdk.model.TipoMovimiento;

import java.util.List;

/**
 * Created by RUL on 28/06/2014.
 */
public interface TipoMovimientoRepositoryDB {
    int create(TipoMovimiento tipoMovimiento);

    int update(TipoMovimiento tipoMovimiento);

    int delete(TipoMovimiento tipoMovimiento);

    TipoMovimiento getTipoMovimiento(Integer id);

    List<TipoMovimiento> getTipoMovimientos();

    TipoMovimiento getTipoMovimientoByClave(String clave);
}
