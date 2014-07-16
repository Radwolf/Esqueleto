package com.esqueleto.esqueletosdk.iteractor.impl;

import android.content.Context;
import android.database.Cursor;

import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletosdk.repository.CategoriaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.TipoMovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CategoriaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.CuentaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.MovimientoRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.ResumenRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.TipoMovimientoRepositoryDBImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class GestorMovimiento implements MovimientoInteractor {

    public static MovimientoRepositoryDB movimientoRepositoryDB;
    public static ResumenRepositoryDB resumenRepositoryDB;
    public static CategoriaRepositoryDB categoriaRepositoryDB;
    public static TipoMovimientoRepositoryDB tipoMovimientoRepositoryDB;
    public static CuentaRepositoryDB cuentaRepositoryDB;

    public GestorMovimiento(Context ctx) {
        movimientoRepositoryDB = new MovimientoRepositoryDBImpl(ctx);
        resumenRepositoryDB = new ResumenRepositoryDBImpl(ctx);
        categoriaRepositoryDB = new CategoriaRepositoryDBImpl(ctx);
        tipoMovimientoRepositoryDB = new TipoMovimientoRepositoryDBImpl(ctx);
        cuentaRepositoryDB = new CuentaRepositoryDBImpl(ctx);
    }

    @Override
    public Movimiento addMovimiento(Resumen resumen, String tipoMovimiento, double importe,
                              Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        Movimiento movimiento = new Movimiento();
        Categoria dCategoria = categoriaRepositoryDB.getCategoriaByClave(categoria);
        TipoMovimiento dTipoMovimiento = tipoMovimientoRepositoryDB.getTipoMovimientoByClave(tipoMovimiento);
        movimiento.setCategoria(dCategoria);
        movimiento.setTipoMovimiento(dTipoMovimiento);
        movimiento.setConcepto(concepto);
        movimiento.setResumen(resumen);
        movimiento.setFechaEstimada(fechaEstimada);
        movimiento.setFechaMovimiento(fechaMovimiento);
        if(fechaMovimiento==null){
            movimiento.setImporteEstimado(importe);
        }else{
            movimiento.setImporte(importe);
        }
        movimientoRepositoryDB.create(movimiento);
        updatearResumen(resumen, dTipoMovimiento, importe, fechaMovimiento);
        return movimiento;
    }

    private void updatearResumen(Resumen resumen, TipoMovimiento tipoMovimiento, double importeValue, Date dFechaMovimiento) {
        String clave = tipoMovimiento.getClave();
        double saldo = 0;
        double saldoEstimado = 0;
        //TODO: Tenemos que asegurarnos que resumen exista.
        if("TIPO_INGRESO".equals(clave)){
            if(dFechaMovimiento==null) {
                resumen.setIngresoEstimado(resumen.getIngresoEstimado() + importeValue);
                saldoEstimado = resumen.getSaldoEstimado() + importeValue;
            }else{
                resumen.setIngreso(resumen.getIngreso() + importeValue);
                saldo = resumen.getSaldo() + importeValue;
            }
        }else if("TIPO_GASTO".equals(clave)){
            if(dFechaMovimiento==null) {
                resumen.setGastoEstimado(resumen.getGastoEstimado() + importeValue);
                saldoEstimado = resumen.getSaldoEstimado() - importeValue;
            }else{
                resumen.setGasto(resumen.getGasto() + importeValue);
                saldo = resumen.getSaldo() - importeValue;
            }
        }else if("TIPO_AHORRO".equals(clave)){
            if(dFechaMovimiento==null) {
                resumen.setAhorroEstimado(resumen.getAhorroEstimado() + importeValue);
                saldoEstimado = resumen.getSaldoEstimado() - importeValue;
            }else{
                resumen.setAhorro(resumen.getAhorro() + importeValue);
                saldo = resumen.getSaldo() - importeValue;
            }
        }

        resumen.setSaldo(saldo + resumen.getSaldoAnterior());
        resumen.setSaldoEstimado(saldoEstimado +  resumen.getSaldoAnterior());
        //TODO: como calcular o para que registrar el saldo anterior?
        //TODO: habria que actualizar el saldo del siguiente resumen, o sino en el momento de la consulta
        resumenRepositoryDB.update(resumen);
        Cuenta cuenta = cuentaRepositoryDB.getCuenta(resumen.getCuenta().get_id());
        cuenta.setDateSinc(new Date());
        cuentaRepositoryDB.update(cuenta);
    }

    @Override
    public Movimiento getMovimiento(Integer id) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByAnyMes(String anyMes) {
        return movimientoRepositoryDB.getMovimientosByAnyMes(anyMes);
    }

    @Override
    public List<Movimiento> getMovimientosByTipo(String tipoMovimiento) {
        return null;
    }

    @Override
    public List<Movimiento> getMovimientosByCategoria(String categoria) {
        return movimientoRepositoryDB.getMovimientosByCategoria(categoria);
    }

    @Override
    public List<Movimiento> getMovimientosByTipoInMes(String tipoMovimiento, String anyMes) {
        return movimientoRepositoryDB.getMovimientosByTipoInMes(tipoMovimiento, anyMes);
    }

    @Override
    public List<Movimiento> getMovimientosByCategoriaInMes(String categoria, String anyMes) {
        return movimientoRepositoryDB.getMovimientosByCategoriaInMes(categoria, anyMes);
    }

    @Override
    public Cursor getCursorMovimientosByAnyMes(String anyMes) {
        return movimientoRepositoryDB.getCursorMovimientosByAnyMes(anyMes);
    }

    @Override
    public Movimiento updateMovimiento(Integer id, String tipoMovimiento, double importe, Date fechaEstimada, Date fechaMovimiento, String categoria, String concepto) {
        return null;
    }

    @Override
    public Movimiento confirmMovimiento(Integer id, double importe, Date fechaMovimiento) {
        return null;
    }

    @Override
    public void deleteMovimiento(Integer id) {

    }
}
