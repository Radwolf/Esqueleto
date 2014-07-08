package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esqueleto.esqueletoui.R;

import java.util.Calendar;

/**
 * Created by Raúl on 29/06/2014.
 */
public class ResumenFragment extends Fragment implements ActionBar.TabListener{

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
        ActionBar actionBar = getActivity().getActionBar();
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
            actionBar.addTab(tab);
            if("2014/06".equals(nombreTab.toString())) {
                actionBar.selectTab(tab);
            }
        }
        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);

        return rootView;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        ft.replace(R.id.content_frame, this);
        Bundle arguments = new Bundle();
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
