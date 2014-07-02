package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;
import android.database.Cursor;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Movimiento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GetCursorMovimientos implements GetCommand<Cursor> {

    //TODO: Sacarlo a un fichero de Constants para compartir con la ui
    private final String SEARCH_BY_ANYMES = "ANYMES";
    private final String SEARCH_BY_TIPOMOVIMIENTO = "TIPOMOVIMIENTO";
    private final String SEARCH_BY_CATEGORIA = "CATEGORIA";

    MovimientoInteractor movimientoInteractor;
    String typeSearch;
    String filtro;

    public GetCursorMovimientos(MovimientoInteractor movimientoInteractor, String typeSearch, String filtro) {
        this.movimientoInteractor = movimientoInteractor;
        this.typeSearch = typeSearch;
        this.filtro = filtro;
    }

    @Override
    public Cursor execute(Context ctx) {
        Cursor cursor = null;
        if(SEARCH_BY_ANYMES.equals(typeSearch)){
            cursor = this.movimientoInteractor.getCursorMovimientosByAnyMes(filtro);
        }
//        else if (SEARCH_BY_CATEGORIA.equals(typeSearch)){
//            cursor = this.movimientoInteractor.getMovimientosByCategoria(filtro);
//        }else if (SEARCH_BY_TIPOMOVIMIENTO.equals(typeSearch)){
//            cursor = this.movimientoInteractor.getMovimientosByTipo(filtro);
//        }
        return cursor;
    }

}