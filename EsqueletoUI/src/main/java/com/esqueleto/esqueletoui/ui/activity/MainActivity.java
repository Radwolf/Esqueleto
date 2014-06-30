package com.esqueleto.esqueletoui.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.GetCuentas;
import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.command.impl.GetResumen;
import com.esqueleto.esqueletosdk.command.impl.GetUsuario;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorUsuario;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CuentaAdapter;
import com.esqueleto.esqueletoui.adapter.MovimientoAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import static android.widget.Toast.LENGTH_SHORT;
public class MainActivity extends FragmentActivity {

//    public static UsuarioRepositoryDB usuarioRepositoryDB;
    public static GetUsuario getUsuario;
//    public static AddUsuario addUsuario;
    public static GestorUsuario gestorUsuario;
    /*
     * Constants
     */
    private static final String MAIL_RUL = "raul.gomo@gmail.com";

    /*
     * Attributes
     */
    public static GestorCuenta gestorCuenta;
    public static GetCuentas getCuentas;
//    public static AddCuenta addCuenta;

    public static GestorMovimiento gestorMovimiento;
//    public static AddMovimiento addMovimiento;
    public static GetMovimientos getMovimientos;

    public static GestorResumen gestorResumen;
//    public static AddResumen addResumen;
    public static GetResumen getResumen;

    /*
     * Attributes
     */
    public static CuentaAdapter cuentaAdapter;
    public static MovimientoAdapter movimientoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestorUsuario = new GestorUsuario(getApplicationContext());
        getUsuario = new GetUsuario(gestorUsuario, MAIL_RUL);

        gestorCuenta = new GestorCuenta(getApplicationContext());
        getCuentas = new GetCuentas(gestorCuenta, MAIL_RUL);
//        List<Cuenta> cuentas = getCuentas.execute(getApplicationContext());

        gestorResumen = new GestorResumen(getApplicationContext());
//        getResumen = new GetResumen(gestorResumen, cuentas.get(0).get_id(), "2014/06");
//        Resumen resumen = getResumen.execute(getApplicationContext());

        gestorMovimiento = new GestorMovimiento(getApplicationContext());
        getMovimientos = new GetMovimientos(gestorMovimiento, "CATEGORIA", "CATEGORIA_NOMINA");
//        List<Movimiento> movimientos = getMovimientos.execute(getApplicationContext());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public static class MainFragment extends Fragment {

       /*
        * Widgets
        */
        @InjectView(R.id.lv_cuentas)
        ListView listView;
        @InjectView(R.id.lv_movimientos)
        ListView movimientoListView;
        @InjectView(R.id.button)
        Button button;
        @InjectView(R.id.textView)
        TextView textView;
        @InjectView(R.id.editText)
        EditText editText;

        @OnItemClick(R.id.lv_cuentas)
        void onItemClick(int position) {
            Toast.makeText(getActivity(), "You clicked: " + cuentaAdapter.getItem(position).getNombre(), LENGTH_SHORT).show();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.inject(this, rootView);
            initListViewCuentas(getCuentas.execute(getActivity().getApplicationContext()));
            initListViewMovimientos(getMovimientos.execute(getActivity().getApplicationContext()));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Usuario usuario = getUsuario.execute(getActivity().getApplicationContext());
                  textView.setText(String.format("Hello, %s!", usuario.getEmail()));
                }
            });

            return rootView;
        }

        private void initListViewMovimientos(List<Movimiento> movimientos) {
            movimientoAdapter = new MovimientoAdapter(getActivity().getApplicationContext(), R.layout.movimiento_item_list, movimientos);
            movimientoListView.setAdapter(movimientoAdapter);
        }

        /**
         * Initialize ListVideo with our RendererAdapter.
         */
        private void initListViewCuentas(List<Cuenta> cuentas) {
            cuentaAdapter = new CuentaAdapter(getActivity().getApplicationContext(), R.layout.cuenta_item, cuentas);
            listView.setAdapter(cuentaAdapter);
        }
    }

}
