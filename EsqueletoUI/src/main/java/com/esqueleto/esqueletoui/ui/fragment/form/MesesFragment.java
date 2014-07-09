package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.GetResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorResumen;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.ui.fragment.list.ListaMovimientosFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Raúl on 29/06/2014.
 */
public class MesesFragment extends Fragment{

    public static final String TAG = "MesesFragment";
    private ActionBar actionBar;

    private Cuenta cuenta;
    GestorResumen gestorResumen;
    GetResumen getResumen;

    @InjectView(R.id.resumen_ahorro)
    TextView ahorro;
    @InjectView(R.id.resumen_ahorro_estimado)
    TextView ahorroEstimado;
    @InjectView(R.id.resumen_gasto)
    TextView gasto;
    @InjectView(R.id.resumen_gasto_estimado)
    TextView gastoEstimado;
    @InjectView(R.id.resumen_ingreso)
    TextView ingreso;
    @InjectView(R.id.resumen_ingreso_estimado)
    TextView ingresoEstimado;
    @InjectView(R.id.resumen_saldo)
    TextView saldo;
    @InjectView(R.id.resumen_saldo_estimado)
    TextView saldoEstimado;
    @InjectView(R.id.resumen_fecha_sincro)
    TextView fechaSincro;

    private FragmentIterationListener mCallback = null;

    public interface FragmentIterationListener{
        public void onFragmentIteration(Bundle parameters);
    }

    public static MesesFragment newInstance(Bundle arguments){
        MesesFragment f = new MesesFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public MesesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        actionBar = getActivity().getActionBar();
        Cuenta cuenta = getArguments().getParcelable("cuenta");
        CharSequence anyMes = getArguments().getCharSequence("anyMes");
        Toast.makeText(getActivity(), anyMes, Toast.LENGTH_LONG).show();
        gestorResumen = new GestorResumen(getActivity());
        getResumen = new GetResumen(gestorResumen, cuenta, anyMes.toString());
        Resumen resumen = getResumen.execute(getActivity());
        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);
        ButterKnife.inject(this, rootView);
        inicializarComponentesResumen(resumen);
//        fechaSincro.setText(String.valueOf(cuenta.getDateSinc()));
        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.resumen_mensual, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_add_movimiento:
//                Bundle arguments = new Bundle();
//                FormMovimientoFragment fragment = FormMovimientoFragment.newInstance(arguments);
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(android.R.id.content, fragment, FormMovimientoFragment
//                        .TAG);
//                ft.commit();
                // Crear un nuevo fragmento y transacción
                FormMovimientoFragment formFragmen = new FormMovimientoFragment();

                // Reemplazar lo que esté en el fragment_container view con este fragmento,
                // y añadir transacción al back stack
                transaction.replace(R.id.content_frame, formFragmen, FormMovimientoFragment.TAG);
                transaction.addToBackStack(ListaMovimientosFragment.TAG);

                //commit la trasacción
                transaction.commit();
                getActivity().setTitle("Añadir movimiento");
                return true;
            case R.id.action_get_movimientos:
                //                Bundle arguments = new Bundle();
//                ListaMovimientosFragment fragment = ListaMovimientosFragment.newInstance(arguments);
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(android.R.id.content, fragment, ListaMovimientosFragment.TAG);
//                ft.commit();
                // Crear un nuevo fragmento y transacción
                ListaMovimientosFragment listFragment = new ListaMovimientosFragment();

                // Reemplazar lo que esté en el fragment_container view con este fragmento,
                // y añadir transacción al back stack
                transaction.replace(R.id.content_frame, listFragment, ListaMovimientosFragment.TAG);
                transaction.addToBackStack(ResumenFragment.TAG);
                //TODO: Movimientos debería salir del drawer pero el drawer deberia ser personalizado por cuenta
                StringBuffer title = new StringBuffer(cuenta.getNombre());
                title.append(" (").append(actionBar.getSelectedTab().getText()).append(")");
                getActivity().setTitle(title.toString());
                //commit la trasacción
                transaction.commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void inicializarComponentesResumen(Resumen resumen) {
        //TODO: crear mascaras para el money
        if(resumen != null) {
            ahorro.setText(String.valueOf(resumen.getAhorro()));
            ahorroEstimado.setText(String.valueOf(resumen.getAhorroEstimado()));
            gasto.setText(String.valueOf(resumen.getGasto()));
            gastoEstimado.setText(String.valueOf(resumen.getGastoEstimado()));
            ingreso.setText(String.valueOf(resumen.getIngreso()));
            ingresoEstimado.setText(String.valueOf(resumen.getIngresoEstimado()));
            saldo.setText(String.valueOf(resumen.getSaldo()));
            saldoEstimado.setText(String.valueOf(resumen.getSaldoEstimado()));
        }else{
            //TODO: en principio no se deberá dar este caso ya que no deberá existir tab sin resumen creado
            Toast.makeText(getActivity(), "El resumen para este mes todavía no ha sido generado.", Toast.LENGTH_LONG).show();
        }

    }

}
