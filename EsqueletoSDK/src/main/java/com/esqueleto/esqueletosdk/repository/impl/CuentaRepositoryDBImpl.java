package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class CuentaRepositoryDBImpl implements CuentaRepositoryDB {

    private DatabaseHelper db;
    Dao<Cuenta, Integer> cuentaDao;
    Dao<Usuario, Integer> usuarioDao;

    public CuentaRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            cuentaDao = db.getCuentaDao();
            usuarioDao = db.getUsuarioDao();
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
        List<Cuenta> cuentas = null;
        try {

            QueryBuilder<Usuario, Integer> usuarioQb = usuarioDao.queryBuilder();
            usuarioQb.where().eq(Usuario.COLUMN_NAME_EMAIL, email);
            QueryBuilder<Cuenta, Integer> cuentaQb = cuentaDao.queryBuilder();
            // join with the order query
            cuentas = cuentaQb.join(usuarioQb).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }
}
