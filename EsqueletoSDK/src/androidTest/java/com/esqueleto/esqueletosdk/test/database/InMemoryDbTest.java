package com.esqueleto.esqueletosdk.test.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.cursor.UsuarioCursor;
import com.esqueleto.esqueletosdk.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDbTest extends AndroidTestCase {

    private DbStore dbStore;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        dbStore = new DbStore(getContext());
    }

    public void testStoreOneName() throws Exception {
        dbStore.insert("MyName");
        assertEquals(1, dbStore.allNames().length);
    }

    public void testStoreTwoName() throws Exception {
        dbStore.insert("MyName");
        dbStore.insert("YourName");
        assertEquals(2, dbStore.allNames().length);
    }

}