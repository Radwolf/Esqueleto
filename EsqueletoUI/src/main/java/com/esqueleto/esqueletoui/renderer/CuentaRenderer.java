package com.esqueleto.esqueletoui.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletoui.R;
import com.pedrogomez.renderers.Renderer;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class CuentaRenderer extends Renderer<Cuenta> {

    private final Context context;
    private OnCuentaClicked listener;

    @Inject
    public CuentaRenderer(Context context) {
        this.context = context;
    }

    @InjectView(R.id.tvCuenta)
    TextView label;
    Usuario usuario;

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.cuenta_renderer, parent, false);
        ButterKnife.inject(this, inflatedView);
        return inflatedView;
    }


//    @OnClick(R.id.iv_thumbnail)
//    void onVideoClicked() {
//        if (listener != null) {
//            C video = getContent();
//            listener.onVideoClicked(video);
//        }
//    }

    @Override
    protected void render() {
        Cuenta cuenta = getContent();
        renderLabel(cuenta);
    }



    protected TextView getLabel() {
        return label;
    }

    protected Context getContext() {
        return context;
    }

    private void renderLabel(Cuenta cuenta){
        this.label.setText(cuenta.getNombre());
        this.usuario = cuenta.getUsuario();
    }

    public void setListener(OnCuentaClicked listener) {
        this.listener = listener;
    }

    public interface OnCuentaClicked {
        void onCuentaClicked(final Cuenta cuenta);
    }

    @OnClick(R.id.tvCuenta) void onCuentaClicked() {
        if (listener != null) {
            Cuenta cuenta = getContent();
            listener.onCuentaClicked(cuenta);
        }
    }

}