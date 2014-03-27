package com.esqueleto.esqueletosdk.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.esqueleto.esqueletosdk.exception.OperationFailedException;
import com.esqueleto.esqueletosdk.exception.RecordNotFoundException;
import com.esqueleto.esqueletosdk.model.Basic;
import com.esqueleto.esqueletosdk.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Raúl on 27/03/2014.
 */
public class GenericDAO<T extends Basic> {

    public static final String COL_DATE_CREATE = "dateCreate";
    public static final int COLIDX_DATE_CREATE = 2;
    public static final String COL_DATE_UPDATE = "dateUpdate";
    public static final int COLIDX_DATE_UPDATE = 3;
    public static final String COL_USER_CREATE = "userCreate";
    public static final int COLIDX_USER_CREATE = 4;
    public static final String COL_USER_UPDATE = "userUpdate";
    public static final int COLIDX_USER_UPDATE = 5;
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
        res = _db.insert(obj.getClass().getName(), null, cv);
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        obj.set_id(res);
        return obj;
    }

    public T update(T obj, ContentValues cv) {
        int res = _db.update(obj.getClass().getName(), cv, "_id = ?", new String[]{Long.toString(obj.get_id())});
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        return obj;
    }

    public int delete(T obj) {
        int res = _db.delete(obj.getClass().getName(), "_id = ?", new String[]{Long.toString(obj.get_id())});
        if (res == -1)
            throw new OperationFailedException();
        if (res == 0)
            throw new RecordNotFoundException();
        return res;
    }

//    public int deleteAll() {
//        int res = _db.delete(T.getClass().getName(), "1", null);
//        if (res == -1)
//            throw new OperationFailedException();
//
//        return res;
//    }

//    public int deleteMulti(String[] ids) {
//        int ret = 0;
//        for (String id:ids) {
//            int res = _db.delete("Usuario", "_id = ?", new String[] {id});
//            if (res == -1)
//                throw new OperationFailedException();
//            if (res == 0)
//                throw new RecordNotFoundException();
//            ret += res;
//        }
//        return ret;
//    }
}
