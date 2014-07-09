package com.esqueleto.esqueletoui.ui.fragment.list;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esqueleto.esqueletosdk.command.impl.GetCategorias;
import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.command.impl.GetTipoMovimientos;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorTipoDato;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.MovimientoAdapter;
import com.esqueleto.esqueletoui.adapter.TipoMovimientoSpinnerAdapter;
import com.esqueleto.esqueletoui.receiver.MovimientoReceiver;
import com.esqueleto.esqueletoui.ui.activity.MainActivity;
import com.esqueleto.esqueletoui.ui.fragment.form.FormMovimientoFragment;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by RUL on 29/06/2014.
 */
public class ListaMovimientosFragment extends ListFragment implements ActionBar.OnNavigationListener{

    public static final String TAG = "ListaMovimientosFragment";
    private MovimientoAdapter adapter;
    private MovimientoReceiver receiver;
    private String claveTipoMovimiento;
    private HashMap<String, Integer> tiposMovimientos = new HashMap<String, Integer>();
    private String tipoSearch;
    private String[] filtros;
    private Cuenta cuenta;
    private String anyMes;

    GestorMovimiento gestorMovimiento;
    GetMovimientos getMovimientos;
    GestorTipoDato gestorTipoDato;
    GetCategorias getCategorias;
    GetTipoMovimientos getTipoMovimientos;


    TipoMovimientoSpinnerAdapter dropDownActionBar;

    @InjectView(R.id.listaMovimientos)
    ListView listaMovimientos;

    private FragmentIterationListener mCallback = null;

    public interface FragmentIterationListener{
        public void onFragmentIteration(Bundle parameters);
    }

    public static ListaMovimientosFragment newInstance(Bundle arguments){
        ListaMovimientosFragment f = new ListaMovimientosFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public ListaMovimientosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tipoSearch = getArguments().getString("tipoSearch");
        filtros = getArguments().getStringArray("filtros");
        anyMes = (filtros.length > 1)?filtros[1]:filtros[0];
        cuenta = getArguments().getParcelable("cuenta");

        View rootView = inflater.inflate(R.layout.fragment_lista_movimientos, container, false);

        ButterKnife.inject(this, rootView);
        //TODO: Sacar a contastante estatica
        inicializarTipos();
        inicializarCommands();
        setHasOptionsMenu(true);

        return rootView;
    }

    private void inicializarTipos() {
        tiposMovimientos.put("TODOS", Integer.valueOf(0));
        tiposMovimientos.put("TIPO_INGRESO", Integer.valueOf(1));
        tiposMovimientos.put("TIPO_GASTO", Integer.valueOf(2));
        tiposMovimientos.put("TIPO_AHORRO", Integer.valueOf(3));
    }

    @Override
    public void onResume() {
        super.onResume();
//        receiver = new MovimientoReceiver(adapter);
//        getActivity().registerReceiver(receiver, new IntentFilter("listamovimientos"));
    }


    @Override
    public void onPause() {
        super.onPause();
//        getActivity().unregisterReceiver(receiver);
    }

//    @OnItemClick(R.id.listaMovimientos)
//    void onItemClick(int position){
//        Toast.makeText(getActivity(), String.format("Has clickado en el movimiento %s",
//                adapter.getItem(position).getConcepto()), Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        inflater.inflate(R.menu.list_movimientos, menu);
        inicializarSpinnerActionBar(getActivity().getActionBar(), getActivity());
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

    private void inicializarCommands() {
        gestorMovimiento = new GestorMovimiento(getActivity());
        gestorTipoDato = new GestorTipoDato(getActivity());
    }

    private void inicializarSpinnerActionBar(ActionBar actionBar, Context ctx) {
        gestorTipoDato = new GestorTipoDato(ctx);
        getTipoMovimientos = new GetTipoMovimientos(gestorTipoDato);
        // Specify a SpinnerAdapter to populate the dropdown list.
        dropDownActionBar = new TipoMovimientoSpinnerAdapter(ctx, getTipoMovimientos.execute(ctx), true);
        actionBar.setListNavigationCallbacks(dropDownActionBar, this);

        //Si traemos ya un filtro por tipo establecido, lo seleccionamos en el combo para disparar la busqueda
        if(filtros.length > 1) {
            claveTipoMovimiento = filtros[0];
        }else{
            claveTipoMovimiento = "TODOS";
        }
        actionBar.setSelectedNavigationItem(tiposMovimientos.get(claveTipoMovimiento).intValue());
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.
//        if (claveTipoMovimiento  == null){
            if (position > 0) {
                TipoMovimiento tvTipoMovimiento = (TipoMovimiento) dropDownActionBar.getItem(position);
                claveTipoMovimiento = tvTipoMovimiento.getClave();
                if ("TIPO_INGRESO".equals(claveTipoMovimiento)) {
                    String[] filtros = {claveTipoMovimiento, "2014/06"};
                    getMovimientos = new GetMovimientos(gestorMovimiento, GetMovimientos.SEARCH_BY_TIPO_ANYMES, filtros);
                } else if ("TIPO_GASTO".equals(claveTipoMovimiento)) {
                    String[] filtros = {claveTipoMovimiento, "2014/06"};
                    getMovimientos = new GetMovimientos(gestorMovimiento, GetMovimientos.SEARCH_BY_TIPO_ANYMES, filtros);
                } else if ("TIPO_AHORRO".equals(claveTipoMovimiento)) {
                    String[] filtros = {claveTipoMovimiento, "2014/06"};
                    getMovimientos = new GetMovimientos(gestorMovimiento, GetMovimientos.SEARCH_BY_TIPO_ANYMES, filtros);
                }
            } else {
                String[] filtros = {"2014/06"};
                getMovimientos = new GetMovimientos(gestorMovimiento, GetMovimientos.SEARCH_BY_ANYMES, filtros);
            }

            List<Movimiento> movimientos = getMovimientos.execute(getActivity());
            adapter = new MovimientoAdapter(getActivity(), movimientos);
            setListAdapter(adapter);
//        }
        return true;
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
}
