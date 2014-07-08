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

    //TODO: Sacarlo a un fichero de Constants para compartir con la ui
    public static final String SEARCH_BY_ANYMES = "ANYMES";
    public static final String SEARCH_BY_TIPOMOVIMIENTO = "TIPOMOVIMIENTO";
    public static final String SEARCH_BY_CATEGORIA = "CATEGORIA";
    public static final String SEARCH_BY_TIPO_ANYMES = "TIPO_ANYMES";
    public static final String SEARCH_BY_CATEGORIA_ANYMES = "CATEGORIA_ANYMES";

    MovimientoInteractor movimientoInteractor;
    String typeSearch;
    String[] filtros;

    public GetMovimientos(MovimientoInteractor movimientoInteractor, String typeSearch, String[] filtros) {
        this.movimientoInteractor = movimientoInteractor;
        this.typeSearch = typeSearch;
        this.filtros = filtros;
    }

    @Override
    public List<Movimiento> execute(Context ctx) {
        List<Movimiento> movimientos = new ArrayList<Movimiento>();
        if(SEARCH_BY_ANYMES.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByAnyMes(filtros[0]);
        }else if (SEARCH_BY_CATEGORIA.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByCategoria(filtros[0]);
        }else if (SEARCH_BY_TIPOMOVIMIENTO.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByTipo(filtros[0]);
        }else if (SEARCH_BY_TIPO_ANYMES.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByTipoInMes(filtros[0], filtros[1]);
        }else if (SEARCH_BY_CATEGORIA_ANYMES.equals(typeSearch)){
            movimientos = this.movimientoInteractor.getMovimientosByCategoriaInMes(filtros[0], filtros[1]);
        }
        return movimientos;
    }

}