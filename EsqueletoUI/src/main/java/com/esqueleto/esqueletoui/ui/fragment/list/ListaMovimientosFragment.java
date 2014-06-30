package com.esqueleto.esqueletoui.ui.fragment.list;

import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.receiver.MovimientoReceiver;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by RUL on 29/06/2014.
 */
public class ListaMovimientosFragment extends Fragment{

    private ArrayAdapter<Movimiento> adapter;
    private MovimientoReceiver receiver;

    @InjectView(R.id.lv_movimientos)
    private ListView movimientos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmeng_lista_movimientos, container, false);
        inicializarComponentes(rootView);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new MovimientoReceiver(adapter);
        getActivity().registerReceiver(receiver, new IntentFilter("listamovimientos"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }


    private void inicializarComponentes(View rootView) {
        ButterKnife.inject(rootView);
//        adapter = new MovimientoAdapter(getActivity(), R.layout.fragmeng_lista_movimientos, )
    }


}
