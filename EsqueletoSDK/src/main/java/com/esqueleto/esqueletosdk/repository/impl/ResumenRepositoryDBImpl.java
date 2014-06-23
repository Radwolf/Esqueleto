package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Diccionario;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class ResumenRepositoryDBImpl implements ResumenRepositoryDB {

    private DatabaseHelper db;
    Dao<Resumen, Integer> resumenDao;
    Dao<Movimiento, Integer> movimientoDao;
    Dao<Diccionario, Integer> diccionarioDao;
    Dao<Cuenta, Integer> cuentaDao;

    public ResumenRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            movimientoDao = db.getMovimientoDao();
            diccionarioDao = db.getDiccionarioDao();
            resumenDao = db.getResumenDao();
            cuentaDao = db.getCuentaDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    @Override
    public int create(Resumen resumen) {
        try {
            return resumenDao.create(resumen);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Resumen resumen) {
        return 0;
    }

    @Override
    public int delete(Resumen resumen) {
        return 0;
    }

    @Override
    public Resumen getResumen(Integer id) {
        try {
            Resumen resumen = resumenDao.queryForId(id);
            return resumen;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Resumen getResumen(Cuenta cuenta, String anyMes) {
        try {
            QueryBuilder<Resumen, Integer> resumenQb = resumenDao.queryBuilder();
            resumenQb.where().eq(Resumen.COLUMN_NAME_CUENTA, cuenta);
            resumenQb.where().eq(Resumen.COLUMN_NAME_ANYMES, anyMes);
            List<Resumen> resumenes = resumenQb.query();
            if(resumenes.size()>0) {
                return resumenes.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Resumen> getResumenes(Cuenta cuenta) {
        List<Resumen> resumenes = null;
        try {
            QueryBuilder<Resumen, Integer> resumenQb = resumenDao.queryBuilder();
            resumenQb.where().eq(Resumen.COLUMN_NAME_CUENTA, cuenta);
            // join with the order query
            resumenes = resumenQb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resumenes;
    }

}
