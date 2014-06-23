package com.esqueleto.esqueletosdk.iteractor;

import android.content.Context;

import com.esqueleto.esqueletosdk.model.Resumen;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 23/06/2014.
 */
public interface ResumenInteractor {
    void addResumen(Context ctx, Integer cuentaId, String anyMes, Date inicioPeriodo,
                    Date finPeriodo);

    Resumen getResumen(Context ctx, Integer id);

    Resumen getResumen(Context ctx, Integer cuentaId, String anyMes);

    List<Resumen> getResumenes(Context ctx, Integer cuentaId);
}
