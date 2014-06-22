package com.esqueleto.esqueletosdk.repository;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public interface ResumenRepositoryDB {

    int create(Resumen resumen);

    int update(Resumen movimiento);

    int delete(Resumen movimiento);

    Resumen getResumen(Integer id);

    List<Resumen> getResumenes(Cuenta cuenta);

}
