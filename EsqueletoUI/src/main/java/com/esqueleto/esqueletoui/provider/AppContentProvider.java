package com.esqueleto.esqueletoui.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.cursor.UsuarioCursor;


public class AppContentProvider  extends ContentProvider {

    private static UriMatcher sUriMatcher;
    private SQLiteDatabase db;
    public static final String AUTHORITY = "com.esqueleto.esqueletoui.provider";
    public static final Uri CONTENT_URI_USUARIO = Uri.parse("content://" + AUTHORITY + "/" + UsuarioCursor.TABLE);
    public static final Uri CONTENT_ITEM_URI_USUARIO = Uri.parse("content://" + AUTHORITY + "/" + UsuarioCursor.TABLE + "/#");
//    public static final Uri CONTENT_URI_ACTIVIDAD = Uri.parse("content://" + AUTHORITY + "/" + ActividadCursor.TABLE);
//    public static final Uri CONTENT_ITEM_URI_ACTIVIDAD = Uri.parse("content://" + AUTHORITY + "/" + ActividadCursor.TABLE + "/#");
//    public static final Uri CONTENT_URI_AFICION = Uri.parse("content://" + AUTHORITY + "/" + AficionCursor.TABLE);
//    public static final Uri CONTENT_ITEM_URI_AFICION = Uri.parse("content://" + AUTHORITY + "/" + AficionCursor.TABLE + "/#");
//    public static final Uri CONTENT_URI_ENCUENTRO = Uri.parse("content://" + AUTHORITY + "/" + EncuentroCursor.TABLE);
//    public static final Uri CONTENT_ITEM_URI_ENCUENTRO = Uri.parse("content://" + AUTHORITY + "/" + EncuentroCursor.TABLE + "/#");
    
    @Override
    public boolean onCreate() {
        int i = 0;
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, UsuarioCursor.TABLE, 1);
        sUriMatcher.addURI(AUTHORITY, UsuarioCursor.TABLE + "/#", 2);
//        sUriMatcher.addURI(AUTHORITY, ActividadCursor.TABLE, 1);
//        sUriMatcher.addURI(AUTHORITY, ActividadCursor.TABLE + "/#", 2);
//        sUriMatcher.addURI(AUTHORITY, AficionCursor.TABLE, 3);
//        sUriMatcher.addURI(AUTHORITY, AficionCursor.TABLE + "/#", 4);
//        sUriMatcher.addURI(AUTHORITY, EncuentroCursor.TABLE, 5);
//        sUriMatcher.addURI(AUTHORITY, EncuentroCursor.TABLE + "/#", 6);
        
        db = new DatabaseHelper(getContext()).getWritableDatabase();
        
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        switch (sUriMatcher.match(uri)) {
            case 1:
                c = db.query(UsuarioCursor.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 2:
                c = db.query(UsuarioCursor.TABLE, projection, BaseColumns._ID + "=" + uri.getLastPathSegment(), selectionArgs, null, null, sortOrder);
                break;
//            case 1:
//                c = db.query(ActividadCursor.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
//                break;
//            case 2:
//                c = db.query(ActividadCursor.TABLE, projection, BaseColumns._ID + "=" + uri.getLastPathSegment(), selectionArgs, null, null, sortOrder);
//                break;
//            case 3:
//                c = db.query(AficionCursor.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
//                break;
//            case 4:
//                c = db.query(AficionCursor.TABLE, projection, BaseColumns._ID + "=" + uri.getLastPathSegment(), selectionArgs, null, null, sortOrder);
//                break;
//            case 5:
//                c = db.query(EncuentroCursor.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
//                break;
//            case 6:
//                c = db.query(EncuentroCursor.TABLE, projection, BaseColumns._ID + "=" + uri.getLastPathSegment(), selectionArgs, null, null, sortOrder);
//                break;
            default:
                c = null;
        }

        if (c != null) {
            c.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 1:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.android.element";
            case 2:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.android.element";
//            case 1:
//                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.android.element";
//            case 2:
//                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.android.element";
//            case 3:
//                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.android.element";
//            case 4:
//                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.android.element";
//            case 5:
//                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.android.element";
//            case 6:
//                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.android.element";
            default:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.android.element";
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri newUri = null;
        switch (sUriMatcher.match(uri)) {
            case 1:
                Long newIdActividad = db.insert(UsuarioCursor.TABLE, null, values);

                newUri = Uri.withAppendedPath(uri, String.valueOf(newIdActividad));
                break;
//            case 1:
//                Long newIdActividad = db.insert(ActividadCursor.TABLE, null, values);
//
//                newUri = Uri.withAppendedPath(uri, String.valueOf(newIdActividad));
//                break;
//            case 3:
//                Long newIdAficion = db.insert(AficionCursor.TABLE, null, values);
//
//                newUri = Uri.withAppendedPath(uri, String.valueOf(newIdAficion));
//                break;
//            case 5:
//                Long newIdEncuentro = db.insert(EncuentroCursor.TABLE, null, values);
//
//                newUri = Uri.withAppendedPath(uri, String.valueOf(newIdEncuentro));
//                break;
        }

        getContext().getContentResolver().notifyChange(uri, null, true);

        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted = 0;
        switch (sUriMatcher.match(uri)) {
            case 1:
                deleted = db.delete(UsuarioCursor.TABLE, selection, selectionArgs);
                break;
            case 2:
                deleted = db.delete(UsuarioCursor.TABLE, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
                break;
//            case 1:
//                deleted = db.delete(ActividadCursor.TABLE, selection, selectionArgs);
//                break;
//            case 2:
//                deleted = db.delete(ActividadCursor.TABLE, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
//                break;
//            case 3:
//            	deleted = db.delete(AficionCursor.TABLE, selection, selectionArgs);
//                break;
//            case 4:
//            	deleted = db.delete(AficionCursor.TABLE, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
//                break;
//            case 5:
//            	deleted = db.delete(EncuentroCursor.TABLE, selection, selectionArgs);
//                break;
//            case 6:
//            	deleted = db.delete(EncuentroCursor.TABLE, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
//                break;
            default:
                deleted = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null, true);

        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updated = 0;
        switch (sUriMatcher.match(uri)) {
            case 1:
                updated = db.update(UsuarioCursor.TABLE, values, selection, selectionArgs);
                break;
            case 2:
                updated = db.update(UsuarioCursor.TABLE, values, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
                break;
//            case 1:
//                updated = db.update(ActividadCursor.TABLE, values, selection, selectionArgs);
//                break;
//            case 2:
//                updated = db.update(ActividadCursor.TABLE, values, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
//                break;
//            case 3:
//                updated = db.update(AficionCursor.TABLE, values, selection, selectionArgs);
//                break;
//            case 4:
//                updated = db.update(AficionCursor.TABLE, values, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
//                break;
//            case 5:
//                updated = db.update(EncuentroCursor.TABLE, values, selection, selectionArgs);
//                break;
//            case 6:
//                updated = db.update(EncuentroCursor.TABLE, values, BaseColumns._ID + "=" + uri.getLastPathSegment(), null);
//                break;
            default:
                updated = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null, true);

        return updated;
    }
}