package com.esqueleto.esqueletosdk.database.cursor;

import android.provider.BaseColumns;

import com.esqueleto.esqueletosdk.database.DbUtils;
import com.esqueleto.esqueletosdk.database.columns.BasicColumns;

public class CuentaCursor {

	public static final String TABLE = "CUENTA" +
			"";

    public static final class Columns extends BasicColumns {
        public static final String EMAIL = "EMAIL";
        public static final String DATE_SYNC = "DATE_SYNC";
        public static final String NOMBRE = "NOMBRE";

    }

    public static String create() {
        DbUtils dbUtils = new DbUtils(TABLE);

        dbUtils.addParam(Columns.EMAIL);
        dbUtils.addParam(Columns.DATE_SYNC, "TIMESTAMP");
        dbUtils.addParam(Columns.NOMBRE);
        dbUtils.addParamAudit();

        return dbUtils.create();
    }
}
