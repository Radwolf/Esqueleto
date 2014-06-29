package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.CategoriaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class CategoriaRepositoryDBImpl implements CategoriaRepositoryDB {

    private DatabaseHelper db;
    Dao<Categoria, Integer> categoriaDao;

    public CategoriaRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            categoriaDao = db.getCategoriaDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    @Override
    public int create(Categoria categoria) {
        try {
            return categoriaDao.create(categoria);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Categoria categoria) {
        return 0;
    }

    @Override
    public int delete(Categoria categoria) {
        return 0;
    }

    @Override
    public Categoria getCategoria(Integer id) {
        try {
            Categoria categoria = categoriaDao.queryForId(id);
            return categoria;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Categoria> getCategorias() {
        List<Categoria> categorias = null;
        try {

            QueryBuilder<Categoria, Integer> categoriaQb = categoriaDao.queryBuilder();
            // join with the order query
            categorias = categoriaQb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public Categoria getCategoriaByClave(String clave){
        List<Categoria> categorias = null;
        try {

            QueryBuilder<Categoria, Integer> categoriaQb = categoriaDao.queryBuilder();
            categoriaQb.where().eq(Categoria.COLUMN_NAME_CLAVE, clave);
            // join with the order query
            categorias = categoriaQb.query();
            if(categorias.size() > 0){
                return categorias.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
