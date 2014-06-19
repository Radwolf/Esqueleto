package com.esqueleto.esqueletoui.ui.fragment.list;

import android.accounts.AccountManager;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.esqueleto.esqueletoui.adapter.CuentaAdapter;

/**
 * Created by rgonzalez on 19/06/2014.
 */
public class CuentaList extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    CuentaAdapter dataAdapter = null;
    String usuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        usuario = preferences.getString(AccountManager.KEY_ACCOUNT_NAME,
                "");

//        Cursor cEncuentros = getActivity().getContentResolver().query(
//                AppContentProvider.CONTENT_URI_ENCUENTRO, null, selection,
//                null, null);
//        List<Encuentro> encuentros = new ArrayList<Encuentro>(
//                cEncuentros.getCount());
//        if (cEncuentros != null && cEncuentros.moveToFirst()) {
//            do {
//                String retador = cEncuentros.getString(cEncuentros
//                        .getColumnIndex(EncuentroCursor.Columns.RETADOR));
//                String actividad = cEncuentros.getString(cEncuentros
//                        .getColumnIndex(EncuentroCursor.Columns.ACTIVIDAD));
//                String idWeb = cEncuentros.getString(cEncuentros
//                        .getColumnIndex(EncuentroCursor.Columns.IDWEB));
//                String latitud = cEncuentros.getString(cEncuentros
//                        .getColumnIndex(EncuentroCursor.Columns.LATITUD));
//                String longitud = cEncuentros.getString(cEncuentros
//                        .getColumnIndex(EncuentroCursor.Columns.LONGITUD));
//                List<Address> addresses;
//                String ubicacion = "Ubicación no disponible";
//                StringBuffer str = new StringBuffer();
//                try {
//                    addresses = gc.getFromLocation(Double.valueOf(latitud)
//                            .doubleValue(), Double.valueOf(longitud)
//                            .doubleValue(), 2);
//                    for (Address address : addresses) {
//                        str.append(address.getAddressLine(0));
//                        str.append("\r");
//                    }
//                    ubicacion = str.toString();
//                } catch (IOException e) {
//                    Log.e("EncuentroAdapter",
//                            "Error en el servicio de geocodificar", e);
//                }
//                String direccion = ubicacion;
//                String resultado = cEncuentros.getString(cEncuentros
//                        .getColumnIndex(EncuentroCursor.Columns.RESULTADO));
//                String retado = cEncuentros.getString(cEncuentros
//                        .getColumnIndex(EncuentroCursor.Columns.RETADO));
//                Encuentro encuentro = new Encuentro();
//                encuentro.setRetador(retador);
//                encuentro.setActividad(actividad);
//                encuentro.setIdWeb(idWeb);
//                encuentro.setDireccion(direccion);
//                encuentro.setResultado(resultado);
//                encuentro.setRetado(retado);
//                encuentros.add(encuentro);
//            } while (cEncuentros.moveToNext());
//        }
//
//        // create an ArrayAdaptar from the String Array
//        dataAdapter = new MisRetosAdapter(
//                getActivity().getApplicationContext(),
//                R.layout.fragment_versus_dummy, encuentros);
//
//        // Le decimos al ListFragment que utilice nuestro adapter
//        setListAdapter(dataAdapter);
//        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
