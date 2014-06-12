package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.GetCommand;
import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Movimiento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GetMovimientos implements GetCommand<List<Movimiento>> {

    private final String SEARCH_BY_ANYMES = "ANYMES";
    private final String SEARCH_BY_TIPOMOVIMIENTO = "TIPOMOVIMIENTO";
    private final String SEARCH_BY_CATEGORIA = "CATEGORIA";

    MovimientoInteractor movimientoInteractor;
    String typeSearch;
    String filtro;

    public GetMovimientos(MovimientoInteractor movimientoInteractor, String typeSearch, String filtro) {
        this.movimientoInteractor = movimientoInteractor;
        this.typeSearch = typeSearch;
        this.filtro = filtro;
    }

    @Override
    public List<Movimiento> execute(Context ctx) {
        List<Movimiento> movimientos = new ArrayList<Movimiento>();
        if(SEARCH_BY_ANYMES.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByMesAny(ctx, filtro);
        }else if (SEARCH_BY_CATEGORIA.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByCategoria(ctx, filtro);
        }else if (SEARCH_BY_TIPOMOVIMIENTO.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByTipo(ctx, filtro);
        }
        return movimientos;
    }

}