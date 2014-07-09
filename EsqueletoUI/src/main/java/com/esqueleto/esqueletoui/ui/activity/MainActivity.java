package com.esqueleto.esqueletoui.ui.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.esqueleto.esqueletosdk.command.impl.GetCuenta;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.listener.TabResumenListener;
import com.esqueleto.esqueletoui.ui.fragment.form.FormMovimientoFragment;
import com.esqueleto.esqueletoui.ui.fragment.form.MesesFragment;
import com.esqueleto.esqueletoui.ui.fragment.form.ResumenFragment;
import com.esqueleto.esqueletoui.ui.fragment.list.ListaMovimientosFragment;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener {

    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDrawerTitles;

    GestorCuenta gestorCuenta;
    GetCuenta getCuenta;
    private Cuenta cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nd);

        //TODO: Crear tratamiento para recuperar la cuenta activa añadir flag en bd y recuperar por usuario
        gestorCuenta = new GestorCuenta(this);
        getCuenta = new GetCuenta(gestorCuenta, 1);
        cuenta = getCuenta.execute(this);

        inicializarNavigationActionBar();
        inicializarDrawerMenu();
        inicializarTabNavigation();

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void inicializarNavigationActionBar() {
        actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    private void inicializarDrawerMenu() {
        mTitle = mDrawerTitle = getTitle();
        mDrawerTitles = getResources().getStringArray(R.array.drawer_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mDrawerTitles));
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
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void inicializarTabNavigation() {
        // Set up the dropdown list navigation in the action bar.
        Calendar calendar = Calendar.getInstance();
        int any = calendar.get(Calendar.YEAR);
        for(int i = 1; i <= 12; i++) {
            StringBuffer nombreTab = new StringBuffer();
            nombreTab.append(any).append("/").append((i<10)?"0":"").append(i);
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(nombreTab);
            tab.setTabListener(new TabResumenListener(this, new MesesFragment(), cuenta));
//            tab.setCustomView(R.layout.tab_resumen_mes);
            actionBar.addTab(tab);
            if("2014/06".equals(nombreTab.toString())) {
                actionBar.selectTab(tab);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //TODO:Recuperar todas las opciones de menú para ocultarlas si se abre el drawer, o recuperarlo desde el fragment
//        menu.findItem(R.id.action_add_movimiento).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_add_movimiento:
//                Bundle arguments = new Bundle();
//                FormMovimientoFragment fragment = FormMovimientoFragment.newInstance(arguments);
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(android.R.id.content, fragment, FormMovimientoFragment
//                        .TAG);
//                ft.commit();
                // Crear un nuevo fragmento y transacción
                FormMovimientoFragment formFragmen = new FormMovimientoFragment();

                // Reemplazar lo que esté en el fragment_container view con este fragmento,
                // y añadir transacción al back stack
                transaction.replace(R.id.content_frame, formFragmen, FormMovimientoFragment.TAG);
                transaction.addToBackStack(ListaMovimientosFragment.TAG);

                //commit la trasacción
                transaction.commit();
                setTitle("Añadir movimiento");
                return true;
            case R.id.action_get_movimientos:
                //                Bundle arguments = new Bundle();
//                ListaMovimientosFragment fragment = ListaMovimientosFragment.newInstance(arguments);
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(android.R.id.content, fragment, ListaMovimientosFragment.TAG);
//                ft.commit();
                // Crear un nuevo fragmento y transacción
                ListaMovimientosFragment listFragment = new ListaMovimientosFragment();

                // Reemplazar lo que esté en el fragment_container view con este fragmento,
                // y añadir transacción al back stack
                transaction.replace(R.id.content_frame, listFragment, ListaMovimientosFragment.TAG);
                transaction.addToBackStack(ResumenFragment.TAG);
                //TODO: Movimientos debería salir del drawer pero el drawer deberia ser personalizado por cuenta
                StringBuffer title = new StringBuffer(cuenta.getNombre());
                title.append(" (").append(actionBar.getSelectedTab().getText()).append(")");
                setTitle(title.toString());
                //commit la trasacción
                transaction.commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(int i, long l) {
        return false;
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        switch(position){
            case 0:
                Bundle arguments = new Bundle();
                arguments.putParcelable("cuenta", cuenta);
                // Crear un nuevo fragmento y transacción
                ResumenFragment newFragment2 = ResumenFragment.newInstance(arguments);
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();

                // Reemplazar lo que esté en el fragment_container view con este fragmento,
                // y añadir transacción al back stack
                transaction2.replace(R.id.content_frame, newFragment2, ResumenFragment.TAG);
                transaction2.addToBackStack(null);

                //commit la trasacción
                transaction2.commit();
                //TODO: Hay que recuperar el nombre de la cuenta para pasar a los resume
                setTitle("Casa");
                break;
            case 1:
                break;
            case 2:
                break;
        }
//        // update the main content by replacing fragments
//        Fragment fragment = new PlanetFragment();
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
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

}
