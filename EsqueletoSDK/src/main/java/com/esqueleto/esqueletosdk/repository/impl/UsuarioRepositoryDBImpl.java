package com.esqueleto.esqueletosdk.repository.impl;

import android.content.Context;
import android.util.Log;

import com.esqueleto.esqueletosdk.database.DatabaseHelper;
import com.esqueleto.esqueletosdk.database.DatabaseManager;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.UsuarioRepositoryDB;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rgonzalez on 09/04/2014.
 */
public class UsuarioRepositoryDBImpl implements UsuarioRepositoryDB {

    public static final String ERROR_SQL = "ERROR_SQL";
    private DatabaseHelper db;
    Dao<Usuario, Integer> usuarioDao;

    public UsuarioRepositoryDBImpl(Context ctx){
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            usuarioDao = db.getUsuarioDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
            Log.e(ERROR_SQL, e.getMessage());
        }

    }

    @Override
    public int create(Usuario usuario){
        try {
            return usuarioDao.create(usuario);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
            Log.e(ERROR_SQL, e.getMessage());
        }
        return 0;
    }
    @Override
    public int update(Usuario usuario){
        try {
            return usuarioDao.update(usuario);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
            Log.e(ERROR_SQL, e.getMessage());
        }
        return 0;
    }
    @Override
    public int delete(Usuario usuario){
        try {
            return usuarioDao.delete(usuario);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
            Log.e(ERROR_SQL, e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Usuario> getUsuarios(){
        try {
            return usuarioDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
            Log.e(ERROR_SQL, e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario getUsuario(Integer idUsuario){
        try {
            return usuarioDao.queryForId(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(ERROR_SQL, e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario getUsuario(String email){
        try {
            List<Usuario> usuarios = usuarioDao.queryForEq(Usuario.COLUMN_NAME_EMAIL, email);
            if(usuarios.size()>0){
                return usuarios.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(ERROR_SQL, e.getMessage());
        }
        return null;
    }
}
