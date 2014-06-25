package com.esqueleto.esqueletosdk.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Diccionario;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DatabaseHelper<T, ID>  extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "ormLiteTutorial.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private RuntimeExceptionDao<T, ID> runtimeDao = null;
    private Dao<Cuenta, Integer> cuentaDao = null;
    private RuntimeExceptionDao<Cuenta, Integer> cuentaRuntimeDao = null;
    private Dao<Diccionario, Integer> diccionarioDao = null;
    private RuntimeExceptionDao<Diccionario, Integer> diccionarioRuntimeDao = null;
    private Dao<Movimiento, Integer> movimientoDao = null;
    private RuntimeExceptionDao<Movimiento, Integer> movimientoRuntimeDao = null;
    private Dao<Resumen, Integer> resumenDao = null;
    private RuntimeExceptionDao<Resumen, Integer> resumenRuntimeDao = null;
    private Dao<Usuario, Integer> usuarioDao = null;
    private RuntimeExceptionDao<Usuario, Integer> usuarioRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

//        DatabaseInitializer initializer = new DatabaseInitializer(context);
//        try {
//            initializer.createDatabase();
//            initializer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Cuenta.class);
            TableUtils.createTable(connectionSource, Diccionario.class);
            TableUtils.createTable(connectionSource, Movimiento.class);
            TableUtils.createTable(connectionSource, Resumen.class);
            TableUtils.createTable(connectionSource, Usuario.class);
            Log.i(DatabaseHelper.class.getName(), "Tablas creadas con exito");
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

        RuntimeExceptionDao<Diccionario, Integer> diccionarioDao = getDiccionarioDataDao();
        loadDataDiccionary(diccionarioDao);

        createDataTest();

//        // here we try inserting data in the on-create as a test
//        RuntimeExceptionDao<Cuenta, Integer> dao = getCuentaDataDao();
//        // create some entries in the onCreate
//        Cuenta cuenta = new Cuenta();
//        dao.create(cuenta);
        Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate");
    }

    private void createDataTest() {
        double CERO_DOUBLE = 0.00;

        RuntimeExceptionDao<Cuenta, Integer> cuentaDao = getCuentaDataDao();
        RuntimeExceptionDao<Usuario, Integer> usuarioDao = getUsuarioDataDao();
        RuntimeExceptionDao<Resumen, Integer> resumenDao = getResumenDataDao();
        RuntimeExceptionDao<Movimiento, Integer> movimientoDao = getMovimientoDataDao();

        Usuario usuario = new Usuario();
        usuario.setEmail("raul.gomo@gmail.com");
        usuarioDao.create(usuario);

        Cuenta cuenta = new Cuenta();
        cuenta.setDateSinc(new Date());
        cuenta.setNombre("Cuenta de casa");
        cuenta.setUsuario(usuario);
        cuentaDao.create(cuenta);

        Resumen resumen = new Resumen();
        resumen.setAhorro(CERO_DOUBLE);
        resumen.setAhorroEstimado(CERO_DOUBLE);
        resumen.setGasto(CERO_DOUBLE);
        resumen.setGastoEstimado(CERO_DOUBLE);
        resumen.setIngreso(CERO_DOUBLE);
        resumen.setIngresoEstimado(CERO_DOUBLE);
        resumen.setSaldo(CERO_DOUBLE);
        resumen.setSaldoAnterior(CERO_DOUBLE);
        resumen.setSaldoEstimado(CERO_DOUBLE);
        resumen.setAnyMes("2014/06");
        resumen.setCuenta(cuenta);
        resumen.setInicioPeriodo(createDateFormat("01/06/2014"));
        resumen.setFinPeriodo(createDateFormat("30/06/2014"));
        resumenDao.create(resumen);

        createDataMovimientosTest(movimientoDao, resumen);

    }

    private Date createDateFormat(String s) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createDataMovimientosTest(RuntimeExceptionDao<Movimiento, Integer> movimientoDao, Resumen resumen) {
        Diccionario dCategoriaNomina = getDiccionarioByClave("CATEGORIA_NOMINA");
        Diccionario dCategoriaCombustible = getDiccionarioByClave("CATEGORIA_COMBUSTIBLE");
        Diccionario dTipoIngreso = getDiccionarioByClave("TIPO_INGRESO");
        Diccionario dTipoGasto = getDiccionarioByClave("TIPO_GASTO");


        Movimiento movimiento = new Movimiento();
        movimiento.setCategoria(dCategoriaNomina);
        movimiento.setTipoMovimiento(dTipoIngreso);
        movimiento.setConcepto("Raúl");
        movimiento.setResumen(resumen);
        movimiento.setImporte(1235.42);
        movimiento.setFechaEstimada(createDateFormat("01/06/2014"));
        movimiento.setFechaMovimiento(createDateFormat("01/06/2014"));
        movimientoDao.create(movimiento);

        movimiento.setCategoria(dCategoriaCombustible);
        movimiento.setTipoMovimiento(dTipoGasto);
        movimiento.setConcepto("Coche");
        movimiento.setResumen(resumen);
        movimiento.setImporte(120);
        movimiento.setFechaEstimada(createDateFormat("01/06/2014"));
        movimiento.setFechaMovimiento(createDateFormat("01/06/2014"));
        movimientoDao.create(movimiento);

    }

    private Diccionario getDiccionarioByClave(String clave) {

        RuntimeExceptionDao<Diccionario, Integer> diccionarioDao = getDiccionarioDataDao();
        try {
            QueryBuilder<Diccionario, Integer> diccionarioQb = diccionarioDao.queryBuilder();
            diccionarioQb.where().eq(Diccionario.COLUMN_NAME_CLAVE, clave);
            List<Diccionario> diccionarios = diccionarioQb.query();
            if(diccionarios.size()>0){
                return diccionarios.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadDataDiccionary(RuntimeExceptionDao<Diccionario, Integer> diccionarioDao) {
        //TODO: Faltará tratamiento de acento para las claves
        String[] tipoMovimientos = new String[]{"Ingreso", "Gasto", "Ahorro"};
        String[] categorias = new String[]{"Nomina", "Recibo", "Prestamo", "Combustible", "Farmacia"};

        for (String tipoMovimiento: tipoMovimientos){
            Diccionario diccionario = new Diccionario();
            diccionario.setValor(tipoMovimiento);
            diccionario.setClave(String.format("TIPO_%s", tipoMovimiento.toUpperCase()));
            diccionario.setTipo("CADENA");
            diccionarioDao.create(diccionario);
        }

        for (String categoria: categorias){
            Diccionario diccionario = new Diccionario();
            diccionario.setValor(categoria);
            diccionario.setClave(String.format("CATEGORIA_%s", categoria.toUpperCase()));
            diccionario.setTipo("CADENA");
            diccionarioDao.create(diccionario);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Cuenta.class, true);
            TableUtils.dropTable(connectionSource, Diccionario.class, true);
            TableUtils.dropTable(connectionSource, Movimiento.class, true);
            TableUtils.dropTable(connectionSource, Resumen.class, true);
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Cuenta, Integer> getCuentaDao() throws SQLException {
        if (cuentaDao == null) {
            cuentaDao = getDao(Cuenta.class);
        }
        return cuentaDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Cuenta, Integer> getCuentaDataDao() {
        if (cuentaRuntimeDao == null) {
            cuentaRuntimeDao = getRuntimeExceptionDao(Cuenta.class);
        }
        return cuentaRuntimeDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Diccionario, Integer> getDiccionarioDao() throws SQLException {
        if (diccionarioDao == null) {
            diccionarioDao = getDao(Diccionario.class);
        }
        return diccionarioDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Diccionario, Integer> getDiccionarioDataDao() {
        if (diccionarioRuntimeDao == null) {
            diccionarioRuntimeDao = getRuntimeExceptionDao(Diccionario.class);
        }
        return diccionarioRuntimeDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Movimiento, Integer> getMovimientoDao() throws SQLException {
        if (movimientoDao == null) {
            movimientoDao = getDao(Movimiento.class);
        }
        return movimientoDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Movimiento, Integer> getMovimientoDataDao() {
        if (movimientoRuntimeDao == null) {
            movimientoRuntimeDao = getRuntimeExceptionDao(Movimiento.class);
        }
        return movimientoRuntimeDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Resumen, Integer> getResumenDao() throws SQLException {
        if (resumenDao == null) {
            resumenDao = getDao(Resumen.class);
        }
        return resumenDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Resumen, Integer> getResumenDataDao() {
        if (resumenRuntimeDao == null) {
            resumenRuntimeDao = getRuntimeExceptionDao(Resumen.class);
        }
        return resumenRuntimeDao;
    }
    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Usuario, Integer> getUsuarioDao() throws SQLException {
        if (usuarioDao == null) {
            usuarioDao = getDao(Usuario.class);
        }
        return usuarioDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Usuario, Integer> getUsuarioDataDao() {
        if (usuarioRuntimeDao == null) {
            usuarioRuntimeDao = getRuntimeExceptionDao(Usuario.class);
        }
        return usuarioRuntimeDao;
    }
    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        cuentaDao = null;
        diccionarioDao = null;
        movimientoDao = null;
        resumenDao = null;
        usuarioDao = null;
    }

}