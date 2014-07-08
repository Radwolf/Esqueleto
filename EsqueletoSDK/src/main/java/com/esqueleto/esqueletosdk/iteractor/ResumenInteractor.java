package com.esqueleto.esqueletosdk.iteractor;

import android.content.Context;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 23/06/2014.
 */
public interface ResumenInteractor {
    Resumen addResumen(Context ctx, Cuenta cuenta, String anyMes, Date inicioPeriodo,
                    Date finPeriodo, double ahorro, double ahorroEstimado, double gasto,
                    double gastoEstimado, double ingreso, double ingresoEstimado, double saldo,
                    double saldoAnterior, double saldoEstimado);

    Resumen getResumen(Context ctx, Integer id);

    Resumen getResumen(Context ctx, Cuenta cuenta, String anyMes);

    List<Resumen> getResumenes(Context ctx, Cuenta cuenta);
}
