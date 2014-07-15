package com.esqueleto.esqueletoui.ui.activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.command.impl.GetCuentaSeleccionada;
import com.esqueleto.esqueletosdk.command.impl.GetCuentas;
import com.esqueleto.esqueletosdk.command.impl.GetMovimientos;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.adapter.CustomDrawerAdapter;
import com.esqueleto.esqueletoui.adapter.item.DrawerItem;
import com.esqueleto.esqueletoui.adapter.item.SpinnerItem;
import com.esqueleto.esqueletoui.ui.fragment.form.ResumenFragment;
import com.esqueleto.esqueletoui.ui.fragment.list.ListaMovimientosFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemSelected;

public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener {

    private ActionBar actionBar;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDrawerTitles;

    private GestorCuenta gestorCuenta;
    private GetCuentas getCuentas;
    private GetCuentaSeleccionada getCuentaSeleccionada;
    private Cuenta cuentaSeleccionada;

    CustomDrawerAdapter adapter;
    List<DrawerItem> dataList;
    List<SpinnerItem> cuentasList;

    //TODO: recuperar el mail del Account
    private String emailUsuario = "raul.gomo@gmail.com";

    public static boolean shouldGoInvisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nd);

        inicializarCommands();
        inicializarNavigationActionBar();
        inicializarDrawerMenu(savedInstanceState);

        actionBar = getActionBar();

    }

    private void inicializarCommands() {
        gestorCuenta = new GestorCuenta(this);
        getCuentas = new GetCuentas(gestorCuenta, emailUsuario);
        getCuentaSeleccionada = new GetCuentaSeleccionada(gestorCuenta, emailUsuario);
        cuentaSeleccionada = getCuentaSeleccionada.execute(this);
    }

    private void inicializarNavigationActionBar() {
        actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    private void inicializarDrawerMenu(Bundle savedInstanceState) {
        mTitle = mDrawerTitle = getTitle();
        dataList = new ArrayList<DrawerItem>();
        mDrawerTitles = getResources().getStringArray(R.array.drawer_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
//        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//                R.layout.drawer_list_item, mDrawerTitles));

//TODO: sacar a metodo inicializar
        // Add Drawer Item to dataList
        dataList.add(new DrawerItem(true)); // adding a spinner to the list

        dataList.add(new DrawerItem("Acciones"));// adding a header to the list
        dataList.add(new DrawerItem("Movimientos", R.drawable.ic_action_view_as_grid));
        dataList.add(new DrawerItem("Importar / Exportar", R.drawable.ic_action_import_export));

        //TODO: recuperar el email de usuario de account
        inicializarSpinnerCuentas();

        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList, cuentasList);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                shouldGoInvisible = false;
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                shouldGoInvisible = true;
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {

            if (dataList.get(0).isSpinner()
                    & dataList.get(1).getTitle() != null) {
                selectItem(2);
            } else if (dataList.get(0).getTitle() != null) {
                selectItem(1);
            } else {
                selectItem(0);
            }
        }
    }

    private void inicializarSpinnerCuentas() {
        List<Cuenta> cuentas = getCuentas.execute(this);
        cuentasList = new ArrayList<SpinnerItem>();
        for (Cuenta cuenta : cuentas) {
            cuentasList.add(new SpinnerItem(R.drawable.ic_drawer, cuenta.getNombre(),
                    emailUsuario, cuenta));
        }
    }

    @OnItemSelected(R.id.drawerSpinner)
    void onClickIngreso(Spinner drawerSpinner){
        Toast.makeText(this, ((SpinnerItem)drawerSpinner.getSelectedItem()).getName(), Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_add_movimiento).setVisible(!drawerOpen);
        ocultarMenuItems(menu, !MainActivity.shouldGoInvisible);
        return super.onPrepareOptionsMenu(menu);
    }

    private void ocultarMenuItems(Menu menu, boolean visible){
        for(int i = 0; i < menu.size(); i++){
            menu.getItem(i).setVisible(visible);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(int i, long l) {
        return false;
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (dataList.get(position).getTitle() == null) {
                selectItem(position);
            }
        }
    }

    private void selectItem(int position) {
        switch (position) {
            case 2:
                Bundle arguments = new Bundle();
                //TODO: El id de la cuenta lo obtendremos del spinner del navigation drawer
                arguments.putString("tipoSearch", GetMovimientos.SEARCH_BY_ANYMES);
                String anyMes = "2014/07";
                if(actionBar.getSelectedTab()!=null){
                    anyMes = actionBar.getSelectedTab().getText().toString();
                }
                String[] filtros = {anyMes};
                arguments.putStringArray("filtros", filtros);
                arguments.putParcelable("cuenta", cuentaSeleccionada);
                ListaMovimientosFragment listFragment = ListaMovimientosFragment.newInstance(arguments);
                StringBuffer title = new StringBuffer(cuentaSeleccionada.getNombre());
                title.append(" (").append(anyMes).append(")");

                loadFragment(listFragment, ListaMovimientosFragment.TAG, title.toString());
                break;
            case 3:
                String anyMesResumen = "2014/07";
                if(actionBar.getSelectedTab()!=null){
                    anyMes = actionBar.getSelectedTab().getText().toString();
                }
                Bundle argumentsResumen = new Bundle();
                //TODO: El id de la cuenta lo obtendremos del spinner del navigation drawer
                argumentsResumen.putParcelable("cuenta", cuentaSeleccionada);
                // Crear un nuevo fragmento y transacción
                ResumenFragment newFragment2 = ResumenFragment.newInstance(argumentsResumen);
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                StringBuffer titulo = new StringBuffer(cuentaSeleccionada.getNombre());
                titulo.append(" (").append(anyMesResumen).append(")");

                loadFragment(newFragment2, ResumenFragment.TAG, titulo.toString());
                break;
        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void loadFragment(Fragment fragment, String tag, String title) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.content_frame, fragment);

        setTitle(title);
        transaction.commit();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        // If the fragment exists and has some back-stack entry
        if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() > 0){
            // Get the fragment fragment manager - and pop the backstack
            getFragmentManager().popBackStack();
        }
        // Else, nothing in the direct fragment back stack
        else{
            // Let super handle the back press
            super.onBackPressed();
        }
    }
}
