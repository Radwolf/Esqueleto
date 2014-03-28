package com.esqueleto.esqueletoui.ui.activity;

import android.content.ContentValues;
import android.database.Cursor;
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
import android.widget.TextView;

import com.esqueleto.esqueletosdk.database.cursor.UsuarioCursor;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.provider.AppContentProvider;
import com.esqueleto.esqueletoui.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentValues values = new ContentValues();
        values.put(UsuarioCursor.Columns.EMAIL, "rul@rul.rul");
        getContentResolver().insert(AppContentProvider.CONTENT_URI_USUARIO, values);
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

                    Cursor cUsuarios = getActivity().getContentResolver().query(
                            AppContentProvider.CONTENT_URI_USUARIO, null, null, null,
                            null);
                    List<Usuario> usuario = new ArrayList<Usuario>(
                            cUsuarios.getCount());
                    String email = "DbError";
                    if (cUsuarios != null && cUsuarios.moveToFirst()) {
                        email = cUsuarios.getString(cUsuarios
                                .getColumnIndex(UsuarioCursor.Columns.EMAIL));
                    }
                    textView.setText(String.format("Hello, %s!", email));
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

}
