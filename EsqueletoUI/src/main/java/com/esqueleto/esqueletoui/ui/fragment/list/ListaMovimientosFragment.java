package com.esqueleto.esqueletoui.ui.fragment.list;

import android.app.ActionBar;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.GetCategorias;
import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorTipoDato;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CategoriaSpinnerAdapter;
import com.esqueleto.esqueletoui.adapter.MovimientoAdapter;
import com.esqueleto.esqueletoui.receiver.MovimientoReceiver;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by RUL on 29/06/2014.
 */
public class ListaMovimientosFragment extends ListFragment{

    public static final String TAG = "ListaMovimientosFragment";
    ActionBar actionBar;
    private MovimientoAdapter adapter;
    private MovimientoReceiver receiver;

    GestorMovimiento gestorMovimiento;
    GetMovimientos getMovimientos;
    GestorTipoDato gestorTipoDato;
    GetCategorias getCategorias;

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
        View rootView = inflater.inflate(R.layout.fragment_lista_movimientos, container, false);
//        actionBar = getFragmentActivity().getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        ButterKnife.inject(this, rootView);
        inicializarCommands();
        inicializarComponentes(rootView);
        setHasOptionsMenu(true);

        return rootView;
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

    private void inicializarComponentes(View rootView) {
        //TODO: calcular el any_mes por defecto
        getCategorias = new GetCategorias(gestorTipoDato);
        getMovimientos = new GetMovimientos(gestorMovimiento, "ANYMES", "2014/06");
        List<Movimiento> movimientos = getMovimientos.execute(getActivity());
        adapter = new MovimientoAdapter(getActivity(), movimientos);
        adapter.notifyDataSetChanged();
        setListAdapter(adapter);
    }

    private void inicializarCommands() {
        gestorMovimiento = new GestorMovimiento(getActivity());
        gestorTipoDato = new GestorTipoDato(getActivity());
    }

    private void inicializarActionBar(){
        actionBar.setListNavigationCallbacks(new CategoriaSpinnerAdapter(getActivity(),
                        getCategorias.execute(getActivity())),
                        new ActionBar.OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {

                return true;
            }
        });
    }
}
