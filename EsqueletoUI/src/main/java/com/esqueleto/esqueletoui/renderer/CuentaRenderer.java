package com.esqueleto.esqueletoui.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;
import com.pedrogomez.renderers.Renderer;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class CuentaRenderer extends Renderer<Cuenta> {

    private final Context context;

    public CuentaRenderer(Context context) {
        this.context = context;
    }

    @InjectView(R.id.tvCuenta)
    TextView label;

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
    }



}