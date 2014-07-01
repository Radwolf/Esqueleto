package com.esqueleto.esqueletoui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.esqueleto.esqueletosdk.command.impl.AddMovimiento;
import com.esqueleto.esqueletosdk.command.impl.GetCategoria;
import com.esqueleto.esqueletosdk.command.impl.GetResumen;
import com.esqueleto.esqueletosdk.command.impl.GetTipoMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorTipoDato;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;

import java.util.ArrayList;

/**
 * Created by RUL on 29/06/2014.
 */
public class MovimientoReceiver extends BroadcastReceiver {

    Context ctx;
    GestorMovimiento gestorMovimiento;
    GestorResumen gestorResumen;
    GestorTipoDato gestorTipoDato;
    AddMovimiento addMovimiento;
    GetResumen getResumen;
    GetCategoria getCategoria;
    GetTipoMovimiento getTipoMovimiento;

    public static final int MOVIMIENTO_INSERTADO = 1;
    public static final int MOVIMIENTO_ACTUALIZADO = 2;
    public static final int MOVIMIENTO_ELIMINADO = 3;

    private final ArrayAdapter<Movimiento> adapter;

    public MovimientoReceiver(ArrayAdapter<Movimiento> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.ctx = context;
        gestorMovimiento = new GestorMovimiento(context);
        gestorResumen = new GestorResumen(context);
        gestorTipoDato = new GestorTipoDato(context);
        int operacion = intent.getIntExtra("operacion", -1);
        switch (operacion){
            case MOVIMIENTO_INSERTADO: insertarMovimiento(intent); break;
            case MOVIMIENTO_ACTUALIZADO: actualizarMovimiento(intent); break;
            case MOVIMIENTO_ELIMINADO: eliminarMovimiento(intent); break;
        }
    }

    private void insertarMovimiento(Intent intent) {
        Movimiento movimiento = popularMovimiento(intent);
        //TODO: Llamar al command para añadir el movimiento
        adapter.add(movimiento);
    }

    private void actualizarMovimiento(Intent intent) {
        Movimiento movimiento = (Movimiento) intent.getSerializableExtra("datos");
        int posicion = adapter.getPosition(movimiento);
        adapter.insert(movimiento, posicion);
    }

    private void eliminarMovimiento(Intent intent) {
        ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) intent.getSerializableExtra("datos");
        for (Movimiento movimiento: movimientos){
            adapter.remove(movimiento);
        }
    }

    private Movimiento popularMovimiento(Intent intent) {
        Movimiento movimiento = (Movimiento)intent.getParcelableExtra("movimiento");
        String claveCategoria = intent.getStringExtra("claveCategoria");
        String claveTipoMovimiento = intent.getStringExtra("claveTipoMovimiento");
        Cuenta cuenta = intent.getParcelableExtra("cuenta");
        String anyMes = intent.getStringExtra("anyMes");
        getResumen = new GetResumen(gestorResumen, cuenta.get_id(), anyMes);
        Resumen resumen = getResumen.execute(ctx);
        getCategoria = new GetCategoria(gestorTipoDato, claveCategoria);
        Categoria categoria = getCategoria.execute(ctx);
        getTipoMovimiento = new GetTipoMovimiento(gestorTipoDato, claveTipoMovimiento);
        TipoMovimiento tipoMovimiento = getTipoMovimiento.execute(ctx);
        movimiento.setResumen(resumen);
        movimiento.setCategoria(categoria);
        movimiento.setTipoMovimiento(tipoMovimiento);
        return movimiento;
    }

}
