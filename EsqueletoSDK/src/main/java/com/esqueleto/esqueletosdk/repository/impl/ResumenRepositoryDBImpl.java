package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class ResumenRepositoryDBImpl implements MovimientoRepositoryDB {

    private DatabaseHelper db;
    Dao<Movimiento, Integer> movimientoDao;
    Dao<Usuario, Integer> diccionarioDao;
    Dao<Resumen, Integer> resumenDao;

    public ResumenRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            movimientoDao = db.getMovimientoDao();
            diccionarioDao = db.getDiccionarioDao();
            resumenDao = db.getResumenDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    @Override
    public int create(Movimiento movimiento) {
        try {
            return movimientoDao.create(movimiento);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Movimiento movimiento) {
        return 0;
    }

    @Override
    public int delete(Movimiento movimiento) {
        return 0;
    }

    @Override
    public Movimiento getMovimiento(Integer id) {
        try {
            Movimiento movimiento = movimientoDao.queryForId(id);
            return movimiento;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByMesAny(String mesAny) {
        List<Movimiento> movimientos = null;
        try {

            QueryBuilder<Resumen, Integer> resumenQb = resumenDao.queryBuilder();
            resumenQb.where().eq(Resumen.COLUMN_NAME_ANYMES, mesAny);
            QueryBuilder<Movimiento, Integer> movimientoQb = movimientoDao.queryBuilder();
            // join with the order query
            movimientos = movimientoQb.join(resumenQb).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public List<Movimiento> getMovimientosByCategoria(String claveDiccionario) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByTipo(String claveDiccionario) {
        return null;
    }
}
