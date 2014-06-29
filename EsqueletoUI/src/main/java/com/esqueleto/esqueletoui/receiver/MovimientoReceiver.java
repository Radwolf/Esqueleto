package com.esqueleto.esqueletoui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.esqueleto.esqueletosdk.model.Movimiento;

import java.util.ArrayList;

/**
 * Created by RUL on 29/06/2014.
 */
public class MovimientoReceiver extends BroadcastReceiver {

    public static final int MOVIMIENTO_INSERTADO = 1;
    public static final int MOVIMIENTO_ACTUALIZADO = 2;
    public static final int MOVIMIENTO_ELIMINADO = 3;

    private final ArrayAdapter<Movimiento> adapter;

    public MovimientoReceiver(ArrayAdapter<Movimiento> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion", -1);
        switch (operacion){
            case MOVIMIENTO_INSERTADO: insertarMovimiento(intent); break;
            case MOVIMIENTO_ACTUALIZADO: actualizarMovimiento(intent); break;
            case MOVIMIENTO_ELIMINADO: eliminarMovimiento(intent); break;
        }
    }

    private void insertarMovimiento(Intent intent) {
        Movimiento movimiento = (Movimiento)intent.getSerializableExtra("datos");
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

}
