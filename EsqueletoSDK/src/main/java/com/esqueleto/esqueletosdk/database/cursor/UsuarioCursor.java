package com.esqueleto.esqueletosdk.database.cursor;

import android.provider.BaseColumns;

import com.esqueleto.esqueletosdk.database.DbUtils;
import com.esqueleto.esqueletosdk.database.columns.BasicColumns;

public class UsuarioCursor {
	public static final String TABLE = "USUARIO" +
			"";

    public static final class Columns extends BasicColumns{
        public static final String EMAIL = "EMAIL";
    }

    public static String create() {
        DbUtils dbUtils = new DbUtils(TABLE);

        dbUtils.addParam(Columns.EMAIL);
        dbUtils.addParamAudit();

        return dbUtils.create();
    }
}
