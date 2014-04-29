package com.esqueleto.esqueletoui.module;

/**
 * Created by rgonzalez on 25/04/2014.
 */

import android.content.Context;
import android.view.LayoutInflater;

import com.esqueleto.esqueletosdk.command.impl.AddCuenta;
import com.esqueleto.esqueletosdk.command.impl.GetCuentas;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorCuenta;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.EsqueletoApplication;
import com.esqueleto.esqueletoui.builder.CuentaRendererBuilder;
import com.esqueleto.esqueletoui.model.CuentaCollection;
import com.esqueleto.esqueletoui.renderer.CuentaRenderer;
import com.esqueleto.esqueletoui.ui.activity.MainActivity;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger main module created to provide main dependencies.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
@Module(injects = {
            EsqueletoApplication.class,
            MainActivity.class
        },
        library = true)
public class MainModule {

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
    private Context context;

    /*
     * Constructor
     */

    public MainModule(Context context) {
        this.context = context;
        gestorCuenta = new GestorCuenta();
        addCuenta = new AddCuenta(gestorCuenta, "Casa", MAIL_RUL);
        addCuenta.execute(context);

        getCuentas = new GetCuentas(gestorCuenta, MAIL_RUL);
    }

    /*
     * Provisioning methods
     */

    @Provides
    RendererAdapter<Cuenta> provideCuentaRendererAdapter(LayoutInflater layoutInflater, CuentaRendererBuilder rendererBuilder) {
        CuentaCollection cuentaCollection = new CuentaCollection(getCuentas.execute(this.context));
        RendererAdapter<Cuenta> adapter = new RendererAdapter<Cuenta>(layoutInflater, rendererBuilder, cuentaCollection);
        return adapter;
    }

    @Provides
    CuentaRendererBuilder provideCuentaRendererBuilder() {
        List<Renderer<Cuenta>> prototypes = getPrototypes();
        return new CuentaRendererBuilder(prototypes);
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }

    @Provides
    Context provideContext() {
        return context;
    }

    /*
     * Auxiliary methods
     */

    /**
     * Create a list of prototypes to configure RendererBuilder.
     * The list of Renderer<Video> that contains all the possible renderers that our RendererBuilder is going to use.
     *
     * @return Renderer<Video> prototypes for RendererBuilder.
     */
    private List<Renderer<Cuenta>> getPrototypes() {
        List<Renderer<Cuenta>> prototypes = new LinkedList<Renderer<Cuenta>>();
        CuentaRenderer cuentaRenderer = new CuentaRenderer(context);
        prototypes.add(cuentaRenderer);

        return prototypes;
    }


}
