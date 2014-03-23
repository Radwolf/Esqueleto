package com.esqueleto.esqueletosdk.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esqueleto.esqueletosdk.database.cursor.UsuarioCursor;

public class DatabaseHelper  extends SQLiteOpenHelper{

    private static final String DATABASE_NAME  = "data.sqlite";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, 1);
//        cargaAuxActividades(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 0 && newVersion == 1) {
            db.execSQL(UsuarioCursor.create());
        }
    }

//    private void cargaAuxActividades(SQLiteDatabase db){
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            for (int i = 0; i < ActividadCursor.Valores.NOMBRE.length; i++) {
//                values.put(ActividadCursor.Columns.NOMBRE, ActividadCursor.Valores.NOMBRE[i]);
//                values.put(ActividadCursor.Columns.CLAVE, ActividadCursor.Valores.CLAVE[i]);
//                db.insert(ActividadCursor.TABLE, null, values);
//            }
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
}
