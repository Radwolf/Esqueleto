package com.esqueleto.esqueletosdk.storage.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by rgonzalez on 09/04/2014.
 */
public class StorageAbstractImpl<T, ID> {

    private DatabaseHelper db;
    Dao<T, ID> dao;

    public StorageAbstractImpl(Context ctx)
    {
        DatabaseManager dbManager = new DatabaseManager();
        db = dbManager.getHelper(ctx);
    }


    public int update(T entity)
    {
        try {
            return dao.update(entity);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(T entity)
    {
        try {
            return dao.delete(entity);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<T> getAll()
    {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }


    public DatabaseHelper getDb() {
        return db;
    }

    public void setDb(DatabaseHelper db) {
        this.db = db;
    }
}
