package com.esqueleto.esqueletoui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.esqueleto.esqueletosdk.command.impl.AddMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by RUL on 29/06/2014.
 */
public class MovimientoReceiver extends BroadcastReceiver {

    Context ctx;
    GestorMovimiento gestorMovimiento;
    AddMovimiento addMovimiento;

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
        int operacion = intent.getIntExtra("operacion", -1);
        switch (operacion){
            case MOVIMIENTO_INSERTADO: insertarMovimiento(intent); break;
            case MOVIMIENTO_ACTUALIZADO: actualizarMovimiento(intent); break;
            case MOVIMIENTO_ELIMINADO: eliminarMovimiento(intent); break;
        }
        adapter.notifyDataSetChanged();
    }

    private void insertarMovimiento(Intent intent) {
        //TODO: Llamar al command para añadir el movimiento
        double importe = intent.getDoubleExtra("importe", 0.0);
        Date fechaEstimada = (Date) intent.getSerializableExtra("fechaEstimada");
        Date fechaMovimiento = (Date) intent.getSerializableExtra("fechaMovimiento");
        String concepto = intent.getStringExtra("concepto");
        String claveCategoria = intent.getStringExtra("claveCategoria");
        String claveTipoMovimiento = intent.getStringExtra("claveTipoMovimiento");
        Cuenta cuenta = intent.getParcelableExtra("cuenta");
        String anyMes = intent.getStringExtra("anyMes");
        addMovimiento = new AddMovimiento(gestorMovimiento, cuenta, anyMes, claveTipoMovimiento, importe,
                fechaEstimada, fechaMovimiento, claveCategoria, concepto);
        Movimiento movimiento = addMovimiento.execute(ctx);
        adapter.setNotifyOnChange(true);
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
