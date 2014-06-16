package com.esqueleto.esqueletoui.listener;

import android.content.Context;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.renderer.CuentaRenderer;

import javax.inject.Inject;

/**
 * Created by rgonzalez on 16/06/2014.
 */
public class OnCuentaClickedListener implements CuentaRenderer.OnCuentaClicked {

    private Context context;

    @Inject
    public OnCuentaClickedListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCuentaClicked(Cuenta cuenta) {
        Toast.makeText(context, "Cuenta clicked. Usuario = " + cuenta.getUsuario().getEmail(), Toast.LENGTH_LONG).show();
    }
}