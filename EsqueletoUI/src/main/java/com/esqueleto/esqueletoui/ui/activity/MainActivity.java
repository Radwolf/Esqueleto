package com.esqueleto.esqueletoui.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.command.impl.AddUsuario;
import com.esqueleto.esqueletosdk.command.impl.GetUsuario;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorUsuario;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletoui.EsqueletoApplication;
import com.esqueleto.esqueletoui.R;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

//    public static UsuarioRepositoryDB usuarioRepositoryDB;
    public static String mailRul = "rul@rul.rul";
    public static GetUsuario getUsuario;
    public static AddUsuario addUsuario;
    public static GestorUsuario gestorUsuario;

    /*
     * Attributes
     */

    @Inject
    RendererAdapter<Cuenta> adapter;

    /*
     * Widgets
     */
    @InjectView(R.id.lv_cuentas)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestorUsuario = new GestorUsuario();
        addUsuario = new AddUsuario(gestorUsuario, mailRul);
        addUsuario.execute(this);

        getUsuario = new GetUsuario(gestorUsuario, mailRul);



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }

        initInjection();
        initListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MainFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button button = (Button) rootView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView textView = (TextView) rootView.findViewById(R.id.textView);
                    EditText editText = (EditText) rootView.findViewById(R.id.editText);

//                    Cursor cUsuarios = getActivity().getContentResolver().query(
//                            AppContentProvider.CONTENT_URI_USUARIO, null, null, null,
//                            null);
//                    List<Usuario> usuario = new ArrayList<Usuario>(
//                            cUsuarios.getCount());
//                    String email = "DbError";
//                    if (cUsuarios != null && cUsuarios.moveToFirst()) {
//                        email = cUsuarios.getString(cUsuarios
//                                .getColumnIndex(UsuarioCursor.Columns.EMAIL));
//                    }


                    Usuario usuario = getUsuario.execute(getActivity().getApplicationContext());
                    textView.setText(String.format("Hello, %s!", usuario.getEmail()));
                }
            });

            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    /**
     * Initialize ListVideo with our RendererAdapter.
     */
    private void initListView() {
        listView.setAdapter(adapter);
    }

    /**
     * Initialize injection from SampleApplication
     */
    private void initInjection() {
        EsqueletoApplication application = (EsqueletoApplication) getApplication();
        application.inject(this);
        ButterKnife.inject(this);
    }

}
