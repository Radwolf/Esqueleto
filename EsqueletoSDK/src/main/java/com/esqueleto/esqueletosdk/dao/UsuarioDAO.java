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
public class UsuarioDAO extends GenericDAO<Usuario> {

    public static final String COL__ID = "_id";
    public static final int COLIDX__ID = 0;
    public static final String COL_EMAIL = "email";
    public static final int COLIDX_EMAIL = 1;

    public UsuarioDAO(SQLiteDatabase db) {
        _db = db;
    }

    public Usuario insert(Usuario usuario) {
        usuario.setDateCreate(DATE_FORMATTER.format(new Date()));
        ContentValues cv = getValues(usuario, new String[]{"_id","dateUpdate"});
        return super.insert(usuario, cv);
    }

    public Usuario update(Usuario usuario) {
        usuario.setDateUpdate(DATE_FORMATTER.format(new Date()));
        ContentValues cv = getValues(usuario, new String[]{"_id","dateCreate"});
        return super.update(usuario, cv);
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
