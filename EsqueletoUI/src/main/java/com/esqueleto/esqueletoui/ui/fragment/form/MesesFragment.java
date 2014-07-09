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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.command.impl.GetResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorResumen;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.ui.activity.MainActivity;
import com.esqueleto.esqueletoui.ui.fragment.list.ListaMovimientosFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Raúl on 29/06/2014.
 */
public class MesesFragment extends Fragment{

    public static final String TAG = "MesesFragment";
    private ActionBar actionBar;

    private Cuenta cuenta;
    private String anyMes;
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

    @OnClick(R.id.totales_ahorro)
    void onClickAhorro(LinearLayout total){
        Bundle listArguments = new Bundle();
        listArguments.putString("tipoSearch", GetMovimientos.SEARCH_BY_TIPO_ANYMES);
        //TODO: Sacarlo a fichero de constantes
        String[] filtros = {"TIPO_AHORRO", anyMes};
        listArguments.putStringArray("filtros", filtros);
        ListaMovimientosFragment listFragment = ListaMovimientosFragment.newInstance(listArguments);
        StringBuffer title = new StringBuffer(cuenta.getNombre());
        title.append(" (").append(actionBar.getSelectedTab().getText()).append(")");

        loadFragment(listFragment, ListaMovimientosFragment.TAG, title.toString());
    }

    @OnClick(R.id.totales_gasto)
    void onClickGasto(LinearLayout total){
        Bundle listArguments = new Bundle();
        listArguments.putString("tipoSearch", GetMovimientos.SEARCH_BY_TIPO_ANYMES);
        String[] filtros = {"TIPO_GASTO", anyMes};
        listArguments.putStringArray("filtros", filtros);
        ListaMovimientosFragment listFragment = ListaMovimientosFragment.newInstance(listArguments);
        StringBuffer title = new StringBuffer(cuenta.getNombre());
        title.append(" (").append(actionBar.getSelectedTab().getText()).append(")");

        loadFragment(listFragment, ListaMovimientosFragment.TAG, title.toString());
    }

    @OnClick(R.id.totales_ingreso)
    void onClickIngreso(LinearLayout total){
        Bundle listArguments = new Bundle();
        listArguments.putString("tipoSearch", GetMovimientos.SEARCH_BY_TIPO_ANYMES);
        String[] filtros = {"TIPO_INGRESO", anyMes};
        listArguments.putStringArray("filtros", filtros);
        ListaMovimientosFragment listFragment = ListaMovimientosFragment.newInstance(listArguments);
        StringBuffer title = new StringBuffer(cuenta.getNombre());
        title.append(" (").append(actionBar.getSelectedTab().getText()).append(")");

        loadFragment(listFragment, ListaMovimientosFragment.TAG, title.toString());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        actionBar = getActivity().getActionBar();
        cuenta = getArguments().getParcelable("cuenta");
        anyMes = String.valueOf(getArguments().getCharSequence("anyMes"));
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
        ocultarMenuItems(menu, !MainActivity.shouldGoInvisible);
        super.onPrepareOptionsMenu(menu);
    }

    private void ocultarMenuItems(Menu menu, boolean visible){
        for(int i = 0; i < menu.size(); i++){
            menu.getItem(i).setVisible(visible);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_add_movimiento:
                //TODO: Añadirlo al fichero de string y recuperaro con el getresources
                String titulo = "Añadir movimiento";
                Bundle formArguments = new Bundle();
                formArguments.putParcelable("cuenta", cuenta);
                formArguments.putString("anyMes", anyMes);
                FormMovimientoFragment formFragment = FormMovimientoFragment.newInstance(formArguments);

                loadFragment(formFragment, FormMovimientoFragment.TAG, titulo);
                break;
            case R.id.action_get_movimientos:
                //TODO: Movimientos habría que ponerlo en el drawer personalizado
                Bundle listArguments = new Bundle();
                listArguments.putString("tipoSearch", GetMovimientos.SEARCH_BY_ANYMES);
                String[] filtros = {anyMes};
                listArguments.putStringArray("filtros", filtros);
                listArguments.putParcelable("cuenta", cuenta);
                ListaMovimientosFragment listFragment = ListaMovimientosFragment.newInstance(listArguments);
                StringBuffer title = new StringBuffer(cuenta.getNombre());
                title.append(" (").append(actionBar.getSelectedTab().getText()).append(")");

                loadFragment(listFragment, ListaMovimientosFragment.TAG, title.toString());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void loadFragment(Fragment fragment, String tag, String title) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_frame, fragment, tag);
        transaction.addToBackStack(tag);

        getActivity().setTitle(title);
        transaction.commit();
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
