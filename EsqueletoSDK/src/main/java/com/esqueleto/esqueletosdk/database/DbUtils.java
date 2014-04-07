package com.esqueleto.esqueletosdk.database;

import java.util.HashMap;

import android.provider.BaseColumns;

import com.esqueleto.esqueletosdk.database.columns.BasicColumns;

public class DbUtils {
	private String table;
    private HashMap<String, String> params;

    /**
     * Constructor of DbTablesUtils.
     * <p/>
     * _ID column is not need to set.
     *
     * @param String table:  name of table to be created.
     */
    public DbUtils(String table) {
        this.table = table;
        params = new HashMap<String, String>();
    }

    /**
     * Method to add new param into the script.
     *
     * @param String col: column name.
     * @param String type: type of column: TEXT, INTEGER; NUMBER, ...
     */
    public DbUtils addParam(String col, String type) {
        params.put(col, type);
        return this;
    }
    /**
     * Method to add new param into the script.
     *
     * @param String col: column name.
     */
    public DbUtils addParam(String col) {
        params.put(col, "text");
        return this;
    }

    /**
     * Method to add params audit into the script.
     *
     */
    public void addParamAudit() {
        this.addParam(BasicColumns.DATE_CREATE, "TIMESTAMP");
        this.addParam(BasicColumns.DATE_UPDATE, "TIMESTAMP");
        this.addParam(BasicColumns.USER_CREATE);
        this.addParam(BasicColumns.USER_UPDATE);
    }

    /**
     * Method to generate "create table script" with _ID param, and columns.
     *
     * @return String with "create table ..." script.
     */
    public String create() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("CREATE TABLE ");
        buffer.append(table);
        buffer.append(" ");
        buffer.append("(");
        buffer.append(BaseColumns._ID + " INTEGER PRIMARY KEY");
        for (String key : params.keySet()) {
            buffer.append(", ");
            buffer.append(key);
            buffer.append(" ");
            buffer.append(params.get(key));
        }
        buffer.append(");");

        return buffer.toString();
    }

    @Override
    public String toString() {
        return create();
    }
}
