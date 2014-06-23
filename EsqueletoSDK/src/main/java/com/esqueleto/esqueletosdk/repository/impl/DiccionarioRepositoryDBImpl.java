package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Diccionario;
import com.esqueleto.esqueletosdk.repository.DiccionarioRepositoryDB;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class DiccionarioRepositoryDBImpl implements DiccionarioRepositoryDB {

    private DatabaseHelper db;
    Dao<Diccionario, Integer> diccionarioDao;

    public DiccionarioRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            diccionarioDao = db.getDiccionarioDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    @Override
    public int create(Diccionario diccionario) {
        try {
            return diccionarioDao.create(diccionario);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Diccionario diccionario) {
        return 0;
    }

    @Override
    public int delete(Diccionario diccionario) {
        return 0;
    }

    @Override
    public Diccionario getDiccionario(Integer id) {
        try {
            Diccionario cuenta = diccionarioDao.queryForId(id);
            return cuenta;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Diccionario getDiccionarioByClave(String clave) {
        try {

            QueryBuilder<Diccionario, Integer> diccionarioQb = diccionarioDao.queryBuilder();
            diccionarioQb.where().eq(Diccionario.COLUMN_NAME_CLAVE, clave);
            // join with the order query
            List<Diccionario> diccionarios = diccionarioQb.query();
            if(diccionarios.size()>0){
                return diccionarios.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
