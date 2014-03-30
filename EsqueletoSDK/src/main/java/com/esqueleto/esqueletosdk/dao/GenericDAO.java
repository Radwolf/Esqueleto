package com.esqueleto.esqueletosdk.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.esqueleto.esqueletosdk.exception.OperationFailedException;
import com.esqueleto.esqueletosdk.exception.RecordNotFoundException;
import com.esqueleto.esqueletosdk.model.Basic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raúl on 27/03/2014.
 */
public class GenericDAO<T extends Basic> {

    public static final int COLIDX_DATE_CREATE = 1;
    public static final int COLIDX_DATE_UPDATE = 2;
    public static final int COLIDX_USER_CREATE = 3;
    public static final int COLIDX_USER_UPDATE = 4;
    public static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
    public SQLiteDatabase _db;

    private static String makePlaceholders(int len) {
        String ret = "";
        for (int idx = 1;idx < len;idx++) {
            ret = ret.concat("?");
        }
        ret = ret.substring(0, ret.length() - 1);
        return ret;
    }

    public T insert(T obj, ContentValues cv) {
        long res;
        res = _db.insert(obj.getClass().getSimpleName(), null, cv);
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        obj.set_id(res);
        return obj;
    }

    public T update(T obj, ContentValues cv) {
        int res = _db.update(obj.getClass().getSimpleName(), cv, "_id = ?", new String[]{Long.toString(obj.get_id())});
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        return obj;
    }

    public int delete(T obj) {
        int res = _db.delete(obj.getClass().getSimpleName(), "_id = ?", new String[]{Long.toString(obj.get_id())});
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        return res;
    }

    public int deleteAll(Class<T> clazz) {
        int res = _db.delete(clazz.getSimpleName(), "1", null);
        if (res == -1)
            throw new OperationFailedException();

        return res;
    }

    public int deleteMulti(Class<T> clazz, String[] ids) {
        int ret = 0;
        for (String id:ids) {
            int res = _db.delete(clazz.getSimpleName(), "_id = ?", new String[] {id});
            if (res == -1)
                throw new OperationFailedException();
            if (res == 0)
                throw new RecordNotFoundException();
            ret += res;
        }
        return ret;
    }

    public Cursor getAll(Class<T> clazz, String orderBy) {
        List<T> ret = new ArrayList<T>();
        Cursor cursor = _db.query(clazz.getSimpleName(), null, null, null, null, null, orderBy);
        return cursor;
    }

    public Cursor getById(Class<T> clazz, long id) {
        Cursor cursor = _db.query(clazz.getSimpleName(), null, "_id = ?", new String[]{Long.toString(id)}, null, null, null);
        return cursor;
    }

    public Cursor get(Class<T> clazz, String whereClause, String[] whereValues, String groupBy, String having, String orderBy) {
        Cursor cursor = _db.query(clazz.getSimpleName(), null, whereClause, whereValues, groupBy, having, orderBy);
        return cursor;
    }
}
