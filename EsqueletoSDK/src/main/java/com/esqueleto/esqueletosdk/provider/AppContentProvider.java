package com.esqueleto.esqueletosdk.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.esqueleto.esqueletosdk.dao.UsuarioDAO;
import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.cursor.UsuarioCursor;
import com.esqueleto.esqueletosdk.model.Usuario;


public class AppContentProvider  extends ContentProvider {

    private static UriMatcher sUriMatcher;
    private SQLiteDatabase db;
    private UsuarioDAO usuarioDAO;
    public static final String AUTHORITY = "com.esqueleto.esqueletosdk.provider";
    public static final Uri CONTENT_URI_USUARIO = Uri.parse("content://" + AUTHORITY + "/" + UsuarioCursor.TABLE);
    public static final Uri CONTENT_ITEM_URI_USUARIO = Uri.parse("content://" + AUTHORITY + "/" + UsuarioCursor.TABLE + "/#");

    @Override
    public boolean onCreate() {
        int i = 0;
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, UsuarioCursor.TABLE, 1);
        sUriMatcher.addURI(AUTHORITY, UsuarioCursor.TABLE + "/#", 2);

        db = new DatabaseHelper(getContext()).getWritableDatabase();
        usuarioDAO = new UsuarioDAO(db);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        switch (sUriMatcher.match(uri)) {
            case 1:
                c = usuarioDAO.getAll(Usuario.class, sortOrder);
                break;
            case 2:
                c = usuarioDAO.getById(Usuario.class, Long.valueOf(uri.getLastPathSegment()).longValue());
                break;
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
            default:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.android.element";
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri newUri = null;
        switch (sUriMatcher.match(uri)) {
            case 1:
                Usuario usuario = populateContentToValues(values);
                Long idUsuario = usuarioDAO.insert(usuario);

                newUri = Uri.withAppendedPath(uri, String.valueOf(idUsuario));
                break;
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

    private Usuario populateContentToValues(ContentValues cv){
        Usuario usuario = new Usuario();
        //Si no tiene id es un Usuario nuevo
        if(cv.getAsLong(UsuarioCursor.Columns._ID) != null) {
            usuario.set_id(cv.getAsLong(UsuarioCursor.Columns._ID).longValue());
            usuario.setUserCreate(cv.getAsString(UsuarioCursor.Columns.USER_CREATE));
            usuario.setDateCreate(cv.getAsString(UsuarioCursor.Columns.DATE_CREATE));
        }
        usuario.setEmail(cv.getAsString(UsuarioCursor.Columns.EMAIL));
        usuario.setUserUpdate(cv.getAsString(UsuarioCursor.Columns.USER_UPDATE));
        usuario.setDateUpdate(cv.getAsString(UsuarioCursor.Columns.DATE_UPDATE));
        return usuario;
    }
}