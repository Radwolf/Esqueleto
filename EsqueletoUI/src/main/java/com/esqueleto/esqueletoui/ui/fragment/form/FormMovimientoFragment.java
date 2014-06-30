package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.esqueleto.esqueletoui.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Raúl on 29/06/2014.
 */
public class FormMovimientoFragment extends Fragment {

    private int request_code = 1;

    @InjectView(R.id.eTConcepto)
    EditText concetpo;
    @InjectView(R.id.sTipoMovimiento)
    Spinner tipoMovimiento;
    @InjectView(R.id.sCategoria)
    Spinner categoria;
    @InjectView(R.id.eTImporte)
    EditText importe;
    @InjectView(R.id.eTFechaMovimiento)
    EditText fechaMovimiento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_form_movimiento, container, false);
        ButterKnife.inject(this, rootView);
        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == request_code){
            Log.i("FromMovimiento", "Todo OK");
        }
    }
}
