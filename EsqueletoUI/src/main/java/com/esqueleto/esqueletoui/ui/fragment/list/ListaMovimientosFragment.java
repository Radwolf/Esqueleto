package com.esqueleto.esqueletoui.ui.fragment.list;

import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.esqueleto.esqueletosdk.command.impl.GetCursorMovimientos;
import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.MovimientoAdapter;
import com.esqueleto.esqueletoui.receiver.MovimientoReceiver;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by RUL on 29/06/2014.
 */
public class ListaMovimientosFragment extends ListFragment{

    private MovimientoAdapter adapter;
    private MovimientoReceiver receiver;
    GestorMovimiento gestorMovimiento;
    GetMovimientos getMovimientos;

    @InjectView(R.id.listaMovimientos)
    ListView listaMovimientos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_movimientos, container, false);
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
        getMovimientos = new GetMovimientos(gestorMovimiento, "ANYMES", "2014/06");
        List<Movimiento> movimientos = getMovimientos.execute(getActivity());
        adapter = new MovimientoAdapter(getActivity(), movimientos);
        adapter.notifyDataSetChanged();
        setListAdapter(adapter);
    }

    private void inicializarCommands() {
        gestorMovimiento = new GestorMovimiento(getActivity());
    }
}
