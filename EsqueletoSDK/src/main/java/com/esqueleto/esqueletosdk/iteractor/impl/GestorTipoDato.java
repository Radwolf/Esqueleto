package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.iteractor.TipoDatoInteractor;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletosdk.repository.CategoriaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.TipoMovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CategoriaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.TipoMovimientoRepositoryDBImpl;

import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorTipoDato implements TipoDatoInteractor {

    public static CategoriaRepositoryDB categoriaRepositoryDB;
    public static TipoMovimientoRepositoryDB tipoMovimientoRepositoryDB;

    public GestorTipoDato(Context ctx) {
        categoriaRepositoryDB = new CategoriaRepositoryDBImpl(ctx);
        tipoMovimientoRepositoryDB = new TipoMovimientoRepositoryDBImpl(ctx);
    }

    @Override
    public Categoria getCategoria(Context ctx, String clave) {
        return categoriaRepositoryDB.getCategoriaByClave(clave);
    }

    @Override
    public TipoMovimiento getTipoMovimiento(Context ctx, String clave) {
        return tipoMovimientoRepositoryDB.getTipoMovimientoByClave(clave);
    }

    @Override
    public List<Categoria> getCategorias(Context ctx) {
        return categoriaRepositoryDB.getCategorias();
    }

    @Override
    public List<TipoMovimiento> getTipoMovimientos(Context ctx) {
        return tipoMovimientoRepositoryDB.getTipoMovimientos();
    }
}
