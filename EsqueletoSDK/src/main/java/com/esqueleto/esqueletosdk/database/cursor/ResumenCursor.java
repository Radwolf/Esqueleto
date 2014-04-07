package com.esqueleto.esqueletosdk.database.cursor;

import com.esqueleto.esqueletosdk.database.DbUtils;
import com.esqueleto.esqueletosdk.database.columns.BasicColumns;

public class ResumenCursor {

	public static final String TABLE = "RESUMEN" +
			"";

    public static final class Columns extends BasicColumns {
        public static final String CUENTA_ID = "CUENTA_ID";
        public static final String INGRESO = "INGRESO";
        public static final String GASTO = "GASTO";
        public static final String AHORRO = "AHORRO";
        public static final String SALDO = "SALDO";
        public static final String ANY_MES = "ANY_MES";
        public static final String INICIO_PERIODO = "INICIO_PERIODO";
        public static final String FIN_PERIODO = "FIN_PERIODO";
        public static final String INGRESO_ESTIMADO = "INGRESO_ESTIMADO";
        public static final String GASTO_ESTIMADO = "GASTO_ESTIMADO";
        public static final String AHORRO_ESTIMADO = "AHORRO_ESTIMADO";
        public static final String SALDO_ESTIMADO = "SALDO_ESTIMADO";
        public static final String SALDO_ANTERIOR = "SALDO_ANTERIOR";

    }

    public static String create() {
        DbUtils dbUtils = new DbUtils(TABLE);

        dbUtils.addParam(Columns.CUENTA_ID);
        dbUtils.addParam(Columns.INGRESO);
        dbUtils.addParam(Columns.GASTO);
        dbUtils.addParam(Columns.AHORRO);
        dbUtils.addParam(Columns.SALDO);
        dbUtils.addParam(Columns.ANY_MES);
        dbUtils.addParam(Columns.INICIO_PERIODO);
        dbUtils.addParam(Columns.FIN_PERIODO);
        dbUtils.addParam(Columns.INGRESO_ESTIMADO);
        dbUtils.addParam(Columns.GASTO_ESTIMADO);
        dbUtils.addParam(Columns.AHORRO_ESTIMADO);
        dbUtils.addParam(Columns.SALDO_ESTIMADO);
        dbUtils.addParam(Columns.SALDO_ANTERIOR);
        dbUtils.addParamAudit();

        return dbUtils.create();
    }
}
