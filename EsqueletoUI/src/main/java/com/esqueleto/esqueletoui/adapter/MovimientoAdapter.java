package com.esqueleto.esqueletoui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rgonzalez on 19/06/2014.
 */
public class MovimientoAdapter extends ArrayAdapter<Cuenta> {

    private List<Cuenta> cuentas;
    private final LayoutInflater inflater;


    public MovimientoAdapter(Context context, int textViewResourceId,
                             List<Cuenta> cuentas) {
        super(context, textViewResourceId, cuentas);
        this.cuentas = new ArrayList<Cuenta>();
        this.cuentas.addAll(cuentas);
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        @InjectView(R.id.tv_nombre_cuenta)
        TextView tvNombreCuenta;
        @InjectView(R.id.tv_usuario_cuenta)
        TextView tvUsuarioCuenta;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.cuenta_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Cuenta cuenta = getItem(position);
        holder.tvNombreCuenta.setText(cuenta.getNombre());
        holder.tvUsuarioCuenta.setText(cuenta.getUsuario().getEmail());
        // Note: don't actually do string concatenation like this in an adapter's getView.

        return view;
    }

    @Override public int getCount() {
        return cuentas.size();
    }

    @Override public Cuenta getItem(int position) {
        return cuentas.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }
}
