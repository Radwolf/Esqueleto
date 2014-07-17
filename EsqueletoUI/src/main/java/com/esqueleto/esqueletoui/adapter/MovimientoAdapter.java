package com.esqueleto.esqueletoui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletoui.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by rgonzalez on 19/06/2014.
 */
public class MovimientoAdapter extends ArrayAdapter<Movimiento> {

    private Activity ctx;
    private List<Movimiento> movimientos;


    public MovimientoAdapter(Activity context, List<Movimiento> movimientos) {
        super(context, R.layout.movimiento_item_list, movimientos);
        this.ctx = context;
        this.movimientos = new ArrayList<Movimiento>();
        this.movimientos.addAll(movimientos);
    }

    static class ViewHolder {
        @InjectView(R.id.tv_data_movimiento)
        TextView tvDataMovimiento;
        @InjectView(R.id.tv_concepto)
        TextView tvConcepto;
        @InjectView(R.id.tv_otros_datos)
        TextView tvOtrosDatos;
        @InjectView(R.id.tv_importe)
        TextView tvImporte;
        @InjectView(R.id.tv_importe_estimado)
        TextView tvImporteEstimado;
        @InjectView(R.id.layout_movimiento_importe)
        LinearLayout lMovimientoImporte;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            view = ctx.getLayoutInflater().inflate(R.layout.movimiento_item_list, parent, false);
        }

        Movimiento movimiento = getItem(position);
        inicializarCampos(view, movimiento);
        return view;
    }

    private void inicializarCampos(View view, Movimiento movimiento) {
        ViewHolder holder = new ViewHolder(view);
        holder.tvDataMovimiento.setText(dateToString(getFechaMovimientoCalculada(movimiento)));
        holder.tvConcepto.setText(movimiento.getConcepto());
        holder.tvOtrosDatos.setText(movimiento.getCategoria().getNombre());
        Double dImporte = Double.valueOf(movimiento.getImporte());
        DecimalFormat dec = new DecimalFormat("0.00");
        dec.setMinimumFractionDigits(2);
        String importe = dec.format(dImporte);
        holder.tvImporte.setText(importe);
        Double dImporteEstimado = Double.valueOf(movimiento.getImporteEstimado());
        String importeEstimado = dec.format(dImporteEstimado);
        holder.tvImporteEstimado.setText(importeEstimado);
        if("TIPO_GASTO".equals(movimiento.getTipoMovimiento().getClave())) {
            holder.lMovimientoImporte.setBackgroundResource(R.drawable.bg_corner_gasto);
        }else if("TIPO_INGRESO".equals(movimiento.getTipoMovimiento().getClave())){
            holder.lMovimientoImporte.setBackgroundResource(R.drawable.bg_corner_ingreso);
        }else if("TIPO_AHORRO".equals(movimiento.getTipoMovimiento().getClave())){
            holder.lMovimientoImporte.setBackgroundResource(R.drawable.bg_corner_ahorro);
        }
    }


    @Override
    public int getCount() {
        return movimientos.size();
    }

    @Override
    public Movimiento getItem(int position) {
        return movimientos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Date getFechaMovimientoCalculada(Movimiento movimiento){
        Date fechaMovimientoCalculada = movimiento.getFechaMovimiento();
        if(fechaMovimientoCalculada == null){
            fechaMovimientoCalculada = movimiento.getFechaEstimada();
        }
        return fechaMovimientoCalculada;
    }

    private String dateToString(Date date){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yy");
        String sFecha;
        sFecha = formatoDelTexto.format(date);
        return sFecha;
    }
}
