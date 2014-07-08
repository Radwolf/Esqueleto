package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.GetResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorResumen;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletoui.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Raúl on 29/06/2014.
 */
public class MesesFragment extends Fragment{

    public static final String TAG = "MesesFragment";

    //TODO: Temporal hay que recuperar la cuenta de algo como una sesion
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Cuenta cuenta = getArguments().getParcelable("cuenta");
        CharSequence anyMes = getArguments().getCharSequence("anyMes");
        Toast.makeText(getActivity(), anyMes, Toast.LENGTH_LONG).show();
        gestorResumen = new GestorResumen(getActivity());
        getResumen = new GetResumen(gestorResumen, cuenta, anyMes.toString());
        Resumen resumen = getResumen.execute(getActivity());
        final View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);
        ButterKnife.inject(this, rootView);
        inicializarComponentesResumen(resumen);
//        fechaSincro.setText(String.valueOf(cuenta.getDateSinc()));
        return rootView;
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
