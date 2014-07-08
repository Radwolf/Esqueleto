package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.GetCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;

import java.util.Calendar;

/**
 * Created by Raúl on 29/06/2014.
 */
public class ResumenFragment extends Fragment implements ActionBar.TabListener{

    public static final String TAG = "ResumenFragment";

    GestorCuenta gestorCuenta;
    GetCuenta getCuenta;

    //TODO: Temporal hay que recuperar la cuenta de algo como una sesion
    private Cuenta cuenta;

    private ActionBar actionBar;
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
        gestorCuenta = new GestorCuenta(getActivity());
        getCuenta = new GetCuenta(gestorCuenta, 1);
        cuenta = getCuenta.execute(getActivity());
        actionBar = getActivity().getActionBar();

        inicializarTabNavigation();
        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);
        return rootView;
    }

    private void inicializarTabNavigation() {
        // Set up the dropdown list navigation in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Calendar calendar = Calendar.getInstance();
        int any = calendar.get(Calendar.YEAR);
        for(int i = 1; i <= 12; i++) {
            StringBuffer nombreTab = new StringBuffer();
            nombreTab.append(any).append("/").append((i<10)?"0":"").append(i);
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(nombreTab);
            tab.setTabListener(this);
//            tab.setCustomView(R.layout.tab_resumen_mes);
            actionBar.addTab(tab);
            if("2014/06".equals(nombreTab.toString())) {
                actionBar.selectTab(tab);
            }
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        ft.replace(R.id.content_frame, this);
        Bundle arguments = new Bundle();
        arguments.putParcelable("cuenta", cuenta);
        arguments.putCharSequence("anyMes", tab.getText());
        MesesFragment newFragment = MesesFragment.newInstance(arguments);
        ft.replace(R.id.content_frame, newFragment, ResumenFragment.TAG);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.remove(this);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        Toast.makeText(getActivity(), "Reselected!", Toast.LENGTH_LONG).show();
    }

}
