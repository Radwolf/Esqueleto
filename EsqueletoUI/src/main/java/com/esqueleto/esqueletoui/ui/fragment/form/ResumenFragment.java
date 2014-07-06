package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
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
import android.widget.TextView;

import com.esqueleto.esqueletosdk.command.impl.AddMovimiento;
import com.esqueleto.esqueletosdk.command.impl.GetCategorias;
import com.esqueleto.esqueletosdk.command.impl.GetCuentas;
import com.esqueleto.esqueletosdk.command.impl.GetTipoMovimientos;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorTipoDato;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CategoriaSpinnerAdapter;
import com.esqueleto.esqueletoui.adapter.TipoMovimientoSpinnerAdapter;
import com.esqueleto.esqueletoui.ui.fragment.list.ListaMovimientosFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Raúl on 29/06/2014.
 */
public class ResumenFragment extends Fragment {

    public static final String TAG = "ResumenFragment";

    //TODO: Temporal hay que recuperar la cuenta de algo como una sesion


    private FragmentIterationListener mCallback = null;
    public interface FragmentIterationListener{
        public void onFragmentIteration(Bundle parameters);
    }

    public static ResumenFragment newInstance(Bundle arguments){
        ResumenFragment f = new ResumenFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public ResumenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);

        return rootView;
    }

}