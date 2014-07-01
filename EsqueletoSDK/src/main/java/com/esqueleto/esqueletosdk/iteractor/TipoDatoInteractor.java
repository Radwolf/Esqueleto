package com.esqueleto.esqueletosdk.iteractor;

import android.content.Context;

import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;

import java.util.List;

/**
 * Created by rgonzalez on 01/07/2014.
 */
public interface TipoDatoInteractor {
    Categoria getCategoria(Context ctx, String clave);

    TipoMovimiento getTipoMovimiento(Context ctx, String clave);

    List<Categoria> getCategorias(Context ctx);

    List<TipoMovimiento> getTipoMovimientos(Context ctx);
}
