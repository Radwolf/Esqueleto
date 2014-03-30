package com.esqueleto.esqueletosdk.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.esqueleto.esqueletosdk.database.cursor.UsuarioCursor;
import com.esqueleto.esqueletosdk.model.Usuario;

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

    public static final int COLIDX__ID = 0;
    public static final int COLIDX_EMAIL = 5;

    public UsuarioDAO(SQLiteDatabase db) {
        _db = db;
    }

    //TODO: Mejor retornar el objeto o solo el ID?
    public Long insert(Usuario usuario) {
        usuario.setDateCreate(DATE_FORMATTER.format(new Date()));
        ContentValues cv = getValues(usuario, new String[]{UsuarioCursor.Columns._ID,UsuarioCursor.Columns.DATE_UPDATE});
        Usuario u = super.insert(usuario, cv);
        return u.get_id();
    }

    public Usuario update(Usuario usuario) {
        usuario.setDateUpdate(DATE_FORMATTER.format(new Date()));
        ContentValues cv = getValues(usuario, new String[]{UsuarioCursor.Columns._ID,UsuarioCursor.Columns.DATE_CREATE});
        return super.update(usuario, cv);
    }

    public List<Usuario> getUsuarios(String orderBy){
        List<Usuario> ret = new ArrayList<Usuario>();
        Cursor cursor = super.getAll(Usuario.class, orderBy);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario obj = populate(cursor);
            ret.add(obj);
            cursor.moveToNext();
        }
        cursor.close();
        return ret;
    }

    public Usuario getUsuarioById(long id) {
        Usuario ret = null;
        Cursor cursor = super.getById(Usuario.class, id);
        if (cursor.moveToFirst()) {
            ret = populate(cursor);
        }
        cursor.close();
        return ret;
    }

    public List<Usuario> getUsuario(String whereClause, String[] whereValues, String groupBy, String having, String orderBy) {
        List<Usuario> ret = new ArrayList<Usuario>();
        Cursor cursor = super.get(Usuario.class, whereClause, whereValues, groupBy, having, orderBy);
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
        if (excludeCols == null || !excluded.contains(UsuarioCursor.Columns._ID)) {
            values.put(UsuarioCursor.Columns._ID,obj.get_id());
        }
        if (excludeCols == null || !excluded.contains(UsuarioCursor.Columns.EMAIL)) {
            values.put(UsuarioCursor.Columns.EMAIL,obj.getEmail());
        }
        if (excludeCols == null || !excluded.contains(UsuarioCursor.Columns.DATE_CREATE)) {
            values.put(UsuarioCursor.Columns.DATE_CREATE,obj.getDateCreate().toString());
        }
        if (excludeCols == null || !excluded.contains(UsuarioCursor.Columns.DATE_UPDATE)) {
            values.put(UsuarioCursor.Columns.DATE_UPDATE,obj.getDateUpdate().toString());
        }
        if (excludeCols == null || !excluded.contains(UsuarioCursor.Columns.USER_CREATE)) {
            values.put(UsuarioCursor.Columns.USER_CREATE,obj.getUserCreate());
        }
        if (excludeCols == null || !excluded.contains(UsuarioCursor.Columns.USER_UPDATE)) {
            values.put(UsuarioCursor.Columns.USER_UPDATE,obj.getUserUpdate());
        }
        return values;
    }

    public int[] getColumnIndices(android.database.Cursor cursor) {
        int[] result=new int[6];
        result[0] = cursor.getColumnIndex(UsuarioCursor.Columns._ID);
        result[1] = cursor.getColumnIndex(UsuarioCursor.Columns.EMAIL);
        result[2] = cursor.getColumnIndex(UsuarioCursor.Columns.DATE_CREATE);
        result[3] = cursor.getColumnIndex(UsuarioCursor.Columns.DATE_UPDATE);
        result[4] = cursor.getColumnIndex(UsuarioCursor.Columns.USER_CREATE);
        result[5] = cursor.getColumnIndex(UsuarioCursor.Columns.USER_UPDATE);
        return result;
    }
}
