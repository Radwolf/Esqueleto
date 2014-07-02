package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.esqueleto.esqueletosdk.command.impl.AddMovimiento;
import com.esqueleto.esqueletosdk.command.impl.GetCategorias;
import com.esqueleto.esqueletosdk.command.impl.GetCuentas;
import com.esqueleto.esqueletosdk.command.impl.GetTipoMovimientos;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorTipoDato;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CategoriaSpinnerAdapter;
import com.esqueleto.esqueletoui.adapter.TipoMovimientoSpinnerAdapter;
import com.esqueleto.esqueletoui.receiver.MovimientoReceiver;

import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Raúl on 29/06/2014.
 */
public class FormMovimientoFragment extends Fragment {

    //TODO: Temporal hay que recuperar la cuenta de algo como una sesion
    GestorCuenta gestorCuenta;
    GetCuentas getCuentas;
    Cuenta cuenta;

    GestorTipoDato gestorTipoDato;
    GetCategorias getCategorias;
    GetTipoMovimientos getTipoMovimientos;

    GestorMovimiento gestorMovimiento;
    AddMovimiento addMovimiento;

    private int request_code = 1;

    @InjectView(R.id.eTConcepto)
    EditText concepto;
    @InjectView(R.id.sTipoMovimiento)
    Spinner tipoMovimientos;
    @InjectView(R.id.sCategoria)
    Spinner categorias;
    @InjectView(R.id.eTImporte)
    EditText importe;
    @InjectView(R.id.eTFechaMovimiento)
    EditText fechaMovimiento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gestorCuenta = new GestorCuenta(getActivity());
        getCuentas = new GetCuentas(gestorCuenta, "raul.gomo@gmail.com");
        List<Cuenta> cuentas = getCuentas.execute(getActivity());
        cuenta = (cuentas.size()>0)?cuentas.get(0):null;

        final View rootView = inflater.inflate(R.layout.fragment_form_movimiento, container, false);
        ButterKnife.inject(this, rootView);
        inicializarCommands(getActivity());
        //TODO: Crear los adapter para rellenar los dos spinners
        inicializarSpinners(getActivity());
        setHasOptionsMenu(true);
        return rootView;
    }

    private void insertarMovimiento(String claveCategoria, String claveTipoMovimiento, String concepto,
                 Cuenta cuenta, String anyMes, double importe, Date fechaEstimada, Date fechaMovimiento){
        addMovimiento = new AddMovimiento(gestorMovimiento, cuenta, anyMes, claveTipoMovimiento, importe,
                fechaEstimada, fechaMovimiento, claveCategoria, concepto);
        Movimiento movimiento = addMovimiento.execute(getActivity());

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == request_code){
            Log.i("FromMovimiento", "Todo OK");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.form_movimiento, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.guardar_movimiento:

                //TODO: Recuperar bien los spinners y castear correctamente el resto de datos
                insertarMovimiento(
                        "CATEGORIA_COMBUSTIBLE",
                        "TIPO_GASTO",
                        concepto.getText().toString(),
                        cuenta,
                        "2014/06",
                        Double.valueOf(importe.getText().toString()).doubleValue(),
                        new Date(fechaMovimiento.getText().toString()),
                        new Date(fechaMovimiento.getText().toString())
                );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inicializarCommands(Context ctx) {
        gestorMovimiento = new GestorMovimiento(ctx);
        gestorTipoDato = new GestorTipoDato(ctx);
        getCategorias = new GetCategorias(gestorTipoDato);
        getTipoMovimientos = new GetTipoMovimientos(gestorTipoDato);
    }

    private void inicializarSpinners(Context ctx) {
        categorias.setAdapter(new CategoriaSpinnerAdapter(ctx, getCategorias.execute(ctx)));
        tipoMovimientos.setAdapter(new TipoMovimientoSpinnerAdapter(ctx, getTipoMovimientos.execute(ctx)));
    }

}
