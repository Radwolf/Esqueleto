package com.esqueleto.esqueletoui.ui.fragment.form;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.command.impl.AddMovimiento;
import com.esqueleto.esqueletosdk.command.impl.AddResumen;
import com.esqueleto.esqueletosdk.command.impl.GetCategorias;
import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.command.impl.GetResumen;
import com.esqueleto.esqueletosdk.command.impl.GetTipoMovimientos;
import com.esqueleto.esqueletosdk.command.impl.UpdateCuenta;
import com.esqueleto.esqueletosdk.command.impl.UpdateResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorResumen;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorTipoDato;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CategoriaSpinnerAdapter;
import com.esqueleto.esqueletoui.adapter.TipoMovimientoSpinnerAdapter;
import com.esqueleto.esqueletoui.ui.activity.MainActivity;
import com.esqueleto.esqueletoui.ui.fragment.list.ListaMovimientosFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Raúl on 29/06/2014.
 */
public class FormMovimientoFragment extends Fragment {

    public static final String TAG = "FormMovimientoFragment";
    private Context ctx;
    private ActionBar actionBar;
    private Cuenta cuenta;
    private String anyMes;

    GestorCuenta gestorCuenta;
    UpdateCuenta updateCuenta;
    GestorResumen gestorResumen;
    GetResumen getResumen;
    UpdateResumen updateResumen;
    AddResumen addResumen;

    GestorTipoDato gestorTipoDato;
    GetCategorias getCategorias;
    GetTipoMovimientos getTipoMovimientos;

    GestorMovimiento gestorMovimiento;
    AddMovimiento addMovimiento;

    private int request_code = 1;

    @InjectView(R.id.eTConcepto)
    EditText concepto;
    @InjectView(R.id.sTipoMovimiento)
    Spinner tipoMovimientos;
    @InjectView(R.id.sCategoria)
    Spinner categorias;
    @InjectView(R.id.eTImporte)
    EditText importe;
    @InjectView(R.id.eTFechaMovimiento)
    EditText fechaMovimiento;
    @InjectView(R.id.titulo_form_movimiento)
    TextView tituloForm;

    private FragmentIterationListener mCallback = null;
    public interface FragmentIterationListener{
        public void onFragmentIteration(Bundle parameters);
    }

    public static FormMovimientoFragment newInstance(Bundle arguments){
        FormMovimientoFragment f = new FormMovimientoFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public FormMovimientoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ctx = getActivity();
        actionBar = getActivity().getActionBar();
        cuenta = getArguments().getParcelable("cuenta");
        anyMes = getArguments().getString("anyMes");

        final View rootView = inflater.inflate(R.layout.fragment_form_movimiento, container, false);
        ButterKnife.inject(this, rootView);
        inicializarCommands();
        inicializarForm();
        //TODO: Crear los adapter para rellenar los dos spinners
        inicializarSpinners(getActivity());
        setHasOptionsMenu(true);

//        getFragmentManager().addOnBackStackChangedListener(
//                new FragmentManager.OnBackStackChangedListener() {
//                    public void onBackStackChanged() {
//                        cargarListado();
//                    }
//                });
        return rootView;
    }

    private void insertarMovimiento(){
        TextView tvCategoria = (TextView) categorias.getSelectedView();
        Categoria categoria = (Categoria) tvCategoria.getTag();
        TextView tvTipoMovimiento = (TextView) tipoMovimientos.getSelectedView();
        TipoMovimiento tipoMovimiento = (TipoMovimiento) tvTipoMovimiento.getTag();
        double importeValue = Double.valueOf(importe.getText().toString()).doubleValue();
        Date dFechaEstimada = stringToDate(fechaMovimiento.getText().toString());
        Date dFechaMovimiento = stringToDate(fechaMovimiento.getText().toString());
        String sConcepto = concepto.getText().toString();

        Resumen resumen = getResumen.execute(ctx);
        if(resumen==null){
            addResumen = new AddResumen(gestorResumen, cuenta, anyMes);
            resumen = addResumen.execute(ctx);
        }
        addMovimiento = new AddMovimiento(gestorMovimiento, resumen, tipoMovimiento.getClave(), importeValue,
                dFechaEstimada, dFechaMovimiento, categoria.getClave(), sConcepto);
        Movimiento movimiento = addMovimiento.execute(getActivity());

        limpiarFormulario();
        cargarListado();
    }

    private void cargarListado() {
        Bundle listArguments = new Bundle();
        listArguments.putString("tipoSearch", GetMovimientos.SEARCH_BY_ANYMES);
        String[] filtros = {anyMes};
        listArguments.putStringArray("filtros", filtros);
        listArguments.putParcelable("cuenta", cuenta);
        ListaMovimientosFragment listFragment = ListaMovimientosFragment.newInstance(listArguments);
        StringBuffer titulo = new StringBuffer(cuenta.getNombre());
        titulo.append(" (").append(anyMes).append(")");

        loadFragment(listFragment, ListaMovimientosFragment.TAG, titulo.toString());
    }

    private void loadFragment(Fragment fragment, String tag, String title) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.content_frame, fragment, tag);

        getActivity().setTitle(title);
        transaction.commit();
    }

    private void limpiarFormulario() {
        categorias.setSelection(0);
        tipoMovimientos.setSelection(0);
        importe.setText("");
        concepto.setText("");
        fechaMovimiento.setText("");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == request_code){
            Log.i("FromMovimiento", "Todo OK");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        //TODO:Recuperar todas las opciones de menú para ocultarlas si se abre el drawer, o recuperarlo desde el fragment
        inflater.inflate(R.menu.form_movimiento, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        ocultarMenuItems(menu, !MainActivity.shouldGoInvisible);
        super.onPrepareOptionsMenu(menu);
    }

    private void ocultarMenuItems(Menu menu, boolean visible){
        for(int i = 0; i < menu.size(); i++){
            menu.getItem(i).setVisible(visible);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_save_movimiento:
                insertarMovimiento();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inicializarForm() {
        StringBuffer titulo = new StringBuffer(cuenta.getNombre()).append(" (").append(anyMes).append(")");
        tituloForm.setText(titulo.toString());
    }

    private void inicializarCommands() {
        gestorCuenta = new GestorCuenta(ctx);
        gestorResumen = new GestorResumen(ctx);
        gestorMovimiento = new GestorMovimiento(ctx);
        gestorTipoDato = new GestorTipoDato(ctx);
        getResumen = new GetResumen(gestorResumen, cuenta, anyMes);
        getCategorias = new GetCategorias(gestorTipoDato);
        getTipoMovimientos = new GetTipoMovimientos(gestorTipoDato);
    }

    private void inicializarSpinners(Context ctx) {
        //TODO: Categoria tendría que ir filtrado por tipo movimiento.
        categorias.setAdapter(new CategoriaSpinnerAdapter(ctx, getCategorias.execute(ctx)));
        tipoMovimientos.setAdapter(new TipoMovimientoSpinnerAdapter(ctx, getTipoMovimientos.execute(ctx), false));
    }

    private Date stringToDate(String sDate){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(sDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
}
