package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esqueleto.esqueletoui.R;

/**
 * Created by Raúl on 29/06/2014.
 */
public class MesesFragment extends Fragment{

    public static final String TAG = "MesesFragment";

    //TODO: Temporal hay que recuperar la cuenta de algo como una sesion


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
        CharSequence anyMes = getArguments().getCharSequence("anyMes");
        Toast.makeText(getActivity(), anyMes, Toast.LENGTH_LONG).show();
        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);
        return rootView;
    }

}
