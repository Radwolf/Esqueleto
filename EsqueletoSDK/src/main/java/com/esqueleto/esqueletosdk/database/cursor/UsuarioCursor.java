package com.esqueleto.esqueletosdk.database.cursor;

import android.provider.BaseColumns;

import com.esqueleto.esqueletosdk.database.DbUtils;

public class UsuarioCursor {
	public static final String TABLE = "USUARIO" +
			"";

    public static final class Columns implements BaseColumns {
        public static final String EMAIL = "EMAIL";
        public static final String DATE_CREATE = "DATE_CREATE";
        public static final String DATE_UPDATE = "DATE_UPDATE";
        public static final String USER_CREATE = "USER_CREATE";
        public static final String USER_UPDATE = "USER_UPDATE";
    }

    public static String create() {
        DbUtils dbUtils = new DbUtils(TABLE);

        dbUtils.addParam(Columns.EMAIL);
        dbUtils.addParam(Columns.DATE_CREATE, "TIMESTAMP");
        dbUtils.addParam(Columns.DATE_UPDATE, "TIMESTAMP");
        dbUtils.addParam(Columns.USER_CREATE);
        dbUtils.addParam(Columns.USER_UPDATE);

        return dbUtils.create();
    }
}
