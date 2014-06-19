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
import com.esqueleto.esqueletosdk.command.impl.AddUsuario;
import com.esqueleto.esqueletosdk.command.impl.GetCuentas;
import com.esqueleto.esqueletosdk.command.impl.GetUsuario;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorUsuario;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CuentaAdapter;

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
    /*
     * Attributes
     */
    public static CuentaAdapter cuentaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestorUsuario = new GestorUsuario();
        addUsuario = new AddUsuario(gestorUsuario, MAIL_RUL);
        addUsuario.execute(this);

        getUsuario = new GetUsuario(gestorUsuario, MAIL_RUL);

        gestorCuenta = new GestorCuenta();
        addCuenta = new AddCuenta(gestorCuenta, "Casa", MAIL_RUL);
        addCuenta.execute(this);

        getCuentas = new GetCuentas(gestorCuenta, MAIL_RUL);

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
