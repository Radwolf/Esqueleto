package com.esqueleto.esqueletoui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.model.TipoMovimiento;

import java.util.List;

/**
 * Created by rgonzalez on 19/06/2014.
 */
public class TipoMovimientoSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private List<TipoMovimiento> tipoMovimientos;
    private final LayoutInflater inflater;

    public TipoMovimientoSpinnerAdapter(Context context, List<TipoMovimiento> tipoMovimientos){
        this.tipoMovimientos = tipoMovimientos;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return tipoMovimientos.size();
    }

    @Override
    public Object getItem(int position) {
        return tipoMovimientos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView text;
        if(convertView != null){
            text = (TextView)convertView;
        }else{
            text = (TextView) inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }
        text.setText(tipoMovimientos.get(position).getNombre());
        text.setTag(tipoMovimientos.get(position));

        return text;
    }

}
