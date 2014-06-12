package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class CuentaRepositoryDBImpl implements CuentaRepositoryDB {

    private DatabaseHelper db;
    Dao<Cuenta, Integer> cuentaDao;

    public CuentaRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            cuentaDao = db.getCuentaDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    @Override
    public int create(Cuenta cuenta) {
        try {
            return cuentaDao.create(cuenta);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Cuenta cuenta) {
        return 0;
    }

    @Override
    public int delete(Cuenta cuenta) {
        return 0;
    }

    @Override
    public Cuenta getCuenta(Integer id) {
        try {
            Cuenta cuenta = cuentaDao.queryForId(id);
            return cuenta;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cuenta> getCuentas(String email) {
        try {
            List<Cuenta> cuentas = cuentaDao.queryForEq("email", email);
            return cuentas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
