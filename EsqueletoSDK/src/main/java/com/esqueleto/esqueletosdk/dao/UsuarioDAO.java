package com.esqueleto.esqueletosdk.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.esqueleto.esqueletosdk.exception.OperationFailedException;
import com.esqueleto.esqueletosdk.exception.RecordNotFoundException;
import com.esqueleto.esqueletosdk.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rgonzalez on 20/02/14.
 */
public class UsuarioDAO {

    public static final String COL__ID = "_id";
    public static final int COLIDX__ID = 0;
    public static final String COL_EMAIL = "email";
    public static final int COLIDX_EMAIL = 1;
    public static final String COL_DATE_CREATE = "dateCreate";
    public static final int COLIDX_DATE_CREATE = 2;
    public static final String COL_DATE_UPDATE = "dateUpdate";
    public static final int COLIDX_DATE_UPDATE = 3;
    public static final String COL_USER_CREATE = "userCreate";
    public static final int COLIDX_USER_CREATE = 4;
    public static final String COL_USER_UPDATE = "userUpdate";
    public static final int COLIDX_USER_UPDATE = 5;
    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
    private SQLiteDatabase _db;

    public UsuarioDAO(SQLiteDatabase db) {
        _db = db;
    }
    private static String makePlaceholders(int len) {
        String ret = "";
        for (int idx = 1;idx < len;idx++) {
            ret = ret.concat("?");
        }
        ret = ret.substring(0, ret.length() - 1);
        return ret;
    }
    public Usuario insert(Usuario obj) {
        obj.setDateCreate(DATE_FORMATTER.format(new Date()));
        ContentValues cv = getValues(obj, new String[]{"_id","dateUpdate"});
        long res = _db.insert("Usuario", null, cv);
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        obj.set_id(res);
        return obj;
    }
    public Usuario update(Usuario obj) {
        obj.setDateUpdate(DATE_FORMATTER.format(new Date()));
        ContentValues cv = getValues(obj, new String[]{"_id","dateCreate"});
        int res = _db.update("Usuario", cv, "_id = ?", new String[]{Long.toString(obj.get_id())});
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        return obj;
    }
    public int delete(Usuario obj) {
        int res = _db.delete("Usuario", "_id = ?", new String[]{Long.toString(obj.get_id())});
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        return res;
    }
    public int deleteAll() {
        int res = _db.delete("Usuario", "1", null);
        if (res == -1)
            throw new OperationFailedException();

        return res;
    }
    public int deleteMulti(String[] ids) {
        int ret = 0;
        for (String id:ids) {
            int res = _db.delete("Usuario", "_id = ?", new String[] {id});
            if (res == -1)
                throw new OperationFailedException();
            if (res == 0)
                throw new RecordNotFoundException();
            ret += res;
        }
        return ret;
    }
    public List<Usuario> getAll(String orderBy) {
        List<Usuario> ret = new ArrayList<Usuario>();
        Cursor cursor = _db.query("Usuario", null, null, null, null, null, orderBy);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario Usuario = populate(cursor);
            ret.add(Usuario);
            cursor.moveToNext();
        }
        cursor.close();
        return ret;
    }
    public Usuario getById(long id) {
        Usuario ret = null;
        Cursor cursor = _db.query("Usuario", null, "_id = ?", new String[]{Long.toString(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            ret = populate(cursor);
        }
        cursor.close();
        return ret;
    }
    public List<Usuario> get(String whereClause, String[] whereValues, String groupBy, String having, String orderBy) {
        List<Usuario> ret = new ArrayList<Usuario>();
        Cursor cursor = _db.query("Usuario", null, whereClause, whereValues, groupBy, having, orderBy);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario Usuario = populate(cursor);
            ret.add(Usuario);
            cursor.moveToNext();
        }
        cursor.close();
        return ret;
    }
    public Usuario populate(android.database.Cursor cursor) {
        int[] columnIndices = this.getColumnIndices(cursor);

        Usuario ret = new Usuario();

        if (columnIndices[COLIDX__ID] >= 0 && ! cursor.isNull(columnIndices[COLIDX__ID])) {
            ret.setEmail(cursor.getString(columnIndices[COLIDX__ID]));
        }
        if (columnIndices[COLIDX_EMAIL] >= 0 && ! cursor.isNull(columnIndices[COLIDX_EMAIL])) {
            ret.setEmail(cursor.getString(columnIndices[COLIDX_EMAIL]));
        }
        if (columnIndices[COLIDX_DATE_CREATE] >= 0 && ! cursor.isNull(columnIndices[COLIDX_DATE_CREATE])) {
            ret.setDateCreate(cursor.getString(columnIndices[COLIDX_DATE_CREATE]));
        }
        if (columnIndices[COLIDX_DATE_UPDATE] >= 0 && ! cursor.isNull(columnIndices[COLIDX_DATE_UPDATE])) {
            ret.setDateUpdate(cursor.getString(columnIndices[COLIDX_DATE_UPDATE]));
        }
        if (columnIndices[COLIDX_USER_CREATE] >= 0 && ! cursor.isNull(columnIndices[COLIDX_USER_CREATE])) {
            ret.setUserCreate(cursor.getString(columnIndices[COLIDX_USER_CREATE]));
        }
        if (columnIndices[COLIDX_USER_UPDATE] >= 0 && ! cursor.isNull(columnIndices[COLIDX_USER_UPDATE])) {
            ret.setUserUpdate(cursor.getString(columnIndices[COLIDX_USER_UPDATE]));
        }
        return ret;
    }
    public android.content.ContentValues getValues(Usuario obj, String[] excludeCols) {
        Set<String> excluded = new HashSet<String>(Arrays.asList(excludeCols));
        android.content.ContentValues values = new android.content.ContentValues();
        if (excludeCols == null || !excluded.contains(COL__ID)) {
            values.put(COL__ID,obj.get_id());
        }
        if (excludeCols == null || !excluded.contains(COL_EMAIL)) {
            values.put(COL_EMAIL,obj.getEmail());
        }
        if (excludeCols == null || !excluded.contains(COL_DATE_CREATE)) {
            values.put(COL_DATE_CREATE,obj.getDateCreate().toString());
        }
        if (excludeCols == null || !excluded.contains(COL_DATE_UPDATE)) {
            values.put(COL_DATE_UPDATE,obj.getDateUpdate().toString());
        }
        if (excludeCols == null || !excluded.contains(COL_USER_CREATE)) {
            values.put(COL_USER_CREATE,obj.getUserCreate());
        }
        if (excludeCols == null || !excluded.contains(COL_USER_UPDATE)) {
            values.put(COL_USER_UPDATE,obj.getUserUpdate());
        }
        return values;
    }
    public int[] getColumnIndices(android.database.Cursor cursor) {
        int[] result=new int[6];
        result[0] = cursor.getColumnIndex(COL__ID);
        result[1] = cursor.getColumnIndex(COL_EMAIL);
        result[2] = cursor.getColumnIndex(COL_DATE_CREATE);
        result[3] = cursor.getColumnIndex(COL_DATE_UPDATE);
        result[4] = cursor.getColumnIndex(COL_USER_CREATE);
        result[5] = cursor.getColumnIndex(COL_USER_UPDATE);
        return result;
    }
}
