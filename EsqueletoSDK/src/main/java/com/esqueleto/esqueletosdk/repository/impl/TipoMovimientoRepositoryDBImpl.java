package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletosdk.repository.TipoMovimientoRepositoryDB;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class TipoMovimientoRepositoryDBImpl implements TipoMovimientoRepositoryDB {

    private DatabaseHelper db;
    Dao<TipoMovimiento, Integer> tipoMovimientoDao;

    public TipoMovimientoRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            tipoMovimientoDao = db.getTipoMovimientoDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    @Override
    public int create(TipoMovimiento tipoMovimiento) {
        try {
            return tipoMovimientoDao.create(tipoMovimiento);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(TipoMovimiento tipoMovimiento) {
        return 0;
    }

    @Override
    public int delete(TipoMovimiento tipoMovimiento) {
        return 0;
    }

    @Override
    public TipoMovimiento getTipoMovimiento(Integer id) {
        try {
            TipoMovimiento tipoMovimiento = tipoMovimientoDao.queryForId(id);
            return tipoMovimiento;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TipoMovimiento> getTipoMovimientos() {
        List<TipoMovimiento> tipoMovimientos = null;
        try {

            QueryBuilder<TipoMovimiento, Integer> tipoMovimientoQb = tipoMovimientoDao.queryBuilder();
            // join with the order query
            tipoMovimientos = tipoMovimientoQb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoMovimientos;
    }

    @Override
    public TipoMovimiento getTipoMovimientoByClave(String clave){
        List<TipoMovimiento> tipoMovimientos = null;
        try {

            QueryBuilder<TipoMovimiento, Integer> tipoMovimientoQb = tipoMovimientoDao.queryBuilder();
            tipoMovimientoQb.where().eq(TipoMovimiento.COLUMN_NAME_CLAVE, clave);
            // join with the order query
            tipoMovimientos = tipoMovimientoQb.query();
            if(tipoMovimientos.size() > 0){
                return tipoMovimientos.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
