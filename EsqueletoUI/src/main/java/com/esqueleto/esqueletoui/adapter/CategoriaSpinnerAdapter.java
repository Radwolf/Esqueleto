package com.esqueleto.esqueletoui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rgonzalez on 19/06/2014.
 */
public class CategoriaSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private List<Categoria> categorias;
    private final LayoutInflater inflater;

    public CategoriaSpinnerAdapter(Context context, List<Categoria> categorias){
        this.categorias = categorias;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return categorias.get(position);
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
        text.setText(categorias.get(position).getNombre());
        text.setTag(categorias.get(position));

        return text;
    }
}
