package com.esqueleto.esqueletoui.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.AddCuenta;
import com.esqueleto.esqueletosdk.command.impl.AddMovimiento;
import com.esqueleto.esqueletosdk.command.impl.AddResumen;
import com.esqueleto.esqueletosdk.command.impl.AddUsuario;
import com.esqueleto.esqueletosdk.command.impl.GetCuentas;
import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.command.impl.GetResumen;
import com.esqueleto.esqueletosdk.command.impl.GetUsuario;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorUsuario;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CuentaAdapter;
import com.esqueleto.esqueletoui.adapter.MovimientoAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import static android.widget.Toast.LENGTH_SHORT;
public class MainActivity extends ActionBarActivity {

//    public static UsuarioRepositoryDB usuarioRepositoryDB;
    public static GetUsuario getUsuario;
    public static AddUsuario addUsuario;
    public static GestorUsuario gestorUsuario;
    /*
     * Constants
     */
    private static final String MAIL_RUL = "rul@rul.rul";

    /*
     * Attributes
     */
    public static GestorCuenta gestorCuenta;
    public static GetCuentas getCuentas;
    public static AddCuenta addCuenta;

    public static GestorMovimiento gestorMovimiento;
    public static AddMovimiento addMovimiento;
    public static GetMovimientos getMovimientos;

    public static GestorResumen gestorResumen;
    public static AddResumen addResumen;
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
        addUsuario = new AddUsuario(gestorUsuario, MAIL_RUL);
        addUsuario.execute(this);

        getUsuario = new GetUsuario(gestorUsuario, MAIL_RUL);

        gestorCuenta = new GestorCuenta(getApplicationContext());
        addCuenta = new AddCuenta(gestorCuenta, "Casa", MAIL_RUL);
        addCuenta.execute(this);

        getCuentas = new GetCuentas(gestorCuenta, MAIL_RUL);
        List<Cuenta> cuentas = getCuentas.execute(getApplicationContext());
        gestorResumen = new GestorResumen(getApplicationContext());

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateIni = null;
        Date dateFin = null;
        try {
            dateIni = formatter.parse("01/06/2014");
            dateFin = formatter.parse("30/06/2014");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        addResumen = new AddResumen(gestorResumen, cuentas.get(0).get_id(), "2014/06", dateIni, dateFin);
        addResumen.execute(getApplicationContext());
        getResumen = new GetResumen(gestorResumen, cuentas.get(0).get_id(), "2014/06");
        Resumen resumen = getResumen.execute(getApplicationContext());

        //Falta añadir categorías y tipos, habrá que crear clase que inserte datos fijos en la instalación.
        //Falta añadir movimientos para probar movimientoAdapter
        gestorMovimiento = new GestorMovimiento(getApplicationContext());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }

    }

    @OnItemClick(R.id.lv_cuentas) void onItemClick(int position) {
        Toast.makeText(this, "You clicked: " + cuentaAdapter.getItem(position), LENGTH_SHORT).show();
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
        @InjectView(R.id.button)
        Button button;
        @InjectView(R.id.textView)
        TextView textView;
        @InjectView(R.id.editText)
        EditText editText;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.inject(this, rootView);
            initListView(getCuentas.execute(getActivity().getApplicationContext()));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  Usuario usuario = getUsuario.execute(getActivity().getApplicationContext());
                  textView.setText(String.format("Hello, %s!", usuario.getEmail()));
                }
            });

            return rootView;
        }

        /**
         * Initialize ListVideo with our RendererAdapter.
         */
        private void initListView(List<Cuenta> cuentas) {
            cuentaAdapter = new CuentaAdapter(getActivity().getApplicationContext(), R.layout.cuenta_item, cuentas);
            listView.setAdapter(cuentaAdapter);
        }
    }

}
