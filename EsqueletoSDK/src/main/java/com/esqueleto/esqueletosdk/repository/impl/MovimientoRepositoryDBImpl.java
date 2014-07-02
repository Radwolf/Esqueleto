package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;
import android.database.Cursor;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Diccionario;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.ColumnArg;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class MovimientoRepositoryDBImpl implements MovimientoRepositoryDB {

    private DatabaseHelper db;
    Dao<Movimiento, Integer> movimientoDao;
    Dao<Categoria, Integer> categoriaDao;
    Dao<TipoMovimiento, Integer> tipoMovimientoDao;
    Dao<Resumen, Integer> resumenDao;

    public MovimientoRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            movimientoDao = db.getMovimientoDao();
            categoriaDao = db.getCategoriaDao();
            tipoMovimientoDao = db.getTipoMovimientoDao();
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
    public List<Movimiento> getMovimientosByAnyMes(String anyMes) {
        List<Movimiento> movimientos = null;
        try {
            //TODO: Habría que tener en cuenta la cuenta
            QueryBuilder<Resumen, Integer> resumenQb = resumenDao.queryBuilder();
            resumenQb.where().eq(Resumen.COLUMN_NAME_ANYMES, anyMes);
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
        List<Movimiento> movimientos = null;
        try {

            QueryBuilder<Categoria, Integer> categoriaQb = categoriaDao.queryBuilder();
            categoriaQb.where().eq(Diccionario.COLUMN_NAME_CLAVE, claveDiccionario);
            QueryBuilder<Movimiento, Integer> movimientoQb = movimientoDao.queryBuilder();
            // join with the order query
            movimientos = movimientoQb.join(categoriaQb).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public List<Movimiento> getMovimientosByTipo(String claveDiccionario) {
        List<Movimiento> movimientos = null;
        try {

            QueryBuilder<TipoMovimiento, Integer> tipoMovimientoQb = tipoMovimientoDao.queryBuilder();
            tipoMovimientoQb.where().eq(Diccionario.COLUMN_NAME_CLAVE, claveDiccionario);
            QueryBuilder<Movimiento, Integer> movimientoQb = movimientoDao.queryBuilder();
            // join with the order query
            movimientos = movimientoQb.join(tipoMovimientoQb).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    @Override
    public Cursor getCursorMovimientosByAnyMes(String anyMes) {
        Cursor cursor = null;
        try {
            //TODO: Habría que tener en cuenta la cuenta
            QueryBuilder<Resumen, Integer> resumenQb = resumenDao.queryBuilder();
            resumenQb.where().eq(Resumen.COLUMN_NAME_ANYMES, anyMes);
            QueryBuilder<Movimiento, Integer> movimientoQb = movimientoDao.queryBuilder();

            CloseableIterator<Movimiento> iterator = movimientoDao.iterator(movimientoQb.join(resumenQb).prepare());
            try {
                // get the raw results which can be cast under Android
                AndroidDatabaseResults results =
                        (AndroidDatabaseResults)iterator.getRawResults();
                cursor = results.getRawCursor();
                return cursor;
            } finally {
                iterator.closeQuietly();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursor;
    }

}
