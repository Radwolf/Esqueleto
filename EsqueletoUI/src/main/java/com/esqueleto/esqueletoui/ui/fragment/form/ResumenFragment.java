package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esqueleto.esqueletosdk.command.impl.GetCuenta;
import com.esqueleto.esqueletosdk.command.impl.UpdateCuentaSeleccionada;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.MesesPageAdapter;
import com.esqueleto.esqueletoui.listener.TabResumenListener;
import com.esqueleto.esqueletoui.ui.activity.MainActivity;

import java.util.Calendar;

/**
 * Created by Raúl on 29/06/2014.
 */
public class ResumenFragment extends Fragment{

    public static final String TAG = "ResumenFragment";
    MesesPageAdapter mesesPageAdapter;
    ViewPager mViewPager;

    private ActionBar actionBar;

    GestorCuenta gestorCuenta;
    GetCuenta getCuenta;
    private Cuenta cuenta;
    UpdateCuentaSeleccionada updateCuentaSeleccionada;

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
        actionBar = getActivity().getActionBar();
        //TODO: Crear tratamiento para recuperar el usuario
        cuenta = getArguments().getParcelable("cuenta");
        gestorCuenta = new GestorCuenta(getActivity());
        updateCuentaSeleccionada = new UpdateCuentaSeleccionada(gestorCuenta, cuenta, "raul.gomo@gmail.com");
        updateCuentaSeleccionada.execute(getActivity());

        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);
        mesesPageAdapter = new MesesPageAdapter(getFragmentManager(), getActivity(), actionBar, cuenta);
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mesesPageAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
                StringBuffer title = new StringBuffer(cuenta.getNombre());
                title.append(" (").append(actionBar.getSelectedTab().getText()).append(")");
                actionBar.setTitle(title);
            }
        });

        inicializarTabNavigation();
        setHasOptionsMenu(true);
        return rootView;
    }

    private void inicializarTabNavigation() {
        // Set up the dropdown list navigation in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Calendar calendar = Calendar.getInstance();
        int any = calendar.get(Calendar.YEAR);
        for(int i = 1; actionBar.getTabCount() < 12 && i <= 12; i++) {
            StringBuffer nombreTab = new StringBuffer();
            nombreTab.append(any).append("/").append((i<10)?"0":"").append(i);
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(nombreTab);
            tab.setTabListener(new TabResumenListener(getActivity(), new MesesFragment(), mViewPager, cuenta));
//            tab.setCustomView(R.layout.tab_resumen_mes);
            actionBar.addTab(tab);
            if("2014/06".equals(nombreTab.toString())) {
                actionBar.selectTab(tab);
            }
        }
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

}
