package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esqueleto.esqueletoui.R;

/**
 * Created by Raúl on 29/06/2014.
 */
public class ResumenFragment extends Fragment{

    public static final String TAG = "ResumenFragment";

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
        actionBar = getActivity().getActionBar();
        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);
        inicializarTabNavigation();
        setHasOptionsMenu(true);
        return rootView;
    }

    private void inicializarTabNavigation() {
        // Set up the dropdown list navigation in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }

}
