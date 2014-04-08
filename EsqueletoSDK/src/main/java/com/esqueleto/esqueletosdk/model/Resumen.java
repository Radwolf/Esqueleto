package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "resumen")
public class Resumen extends Basic {

    @DatabaseField
    private long cuentaId;
    @DatabaseField
    private double ingreso;
    @DatabaseField
    private double gasto;
    @DatabaseField
    private double ahorro;
    @DatabaseField
    private double saldo;
    @DatabaseField
    private String anyMes;
    @DatabaseField
    private String inicioPeriodo;
    @DatabaseField
    private String finPeriodo;
    @DatabaseField
    private double ingresoEstimado;
    @DatabaseField
    private double gastoEstimado;
    @DatabaseField
    private double ahorroEstimado;
    @DatabaseField
    private double saldoEstimado;
    @DatabaseField
    private double saldoAnterior;

    public Resumen() {
    }

    public Resumen(long _id, String dateCreate, String dateUpdate, String userCreate,
                   String userUpdate, long cuentaId, double ingreso, double gasto, double ahorro,
                   double saldo, String anyMes, String inicioPeriodo, String finPeriodo,
                   double ingresoEstimado, double gastoEstimado, double ahorroEstimado,
                   double saldoEstimado, double saldoAnterior) {
        super(_id, dateCreate, dateUpdate, userCreate, userUpdate);
        this.cuentaId = cuentaId;
        this.ingreso = ingreso;
        this.gasto = gasto;
        this.ahorro = ahorro;
        this.saldo = saldo;
        this.anyMes = anyMes;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.ingresoEstimado = ingresoEstimado;
        this.gastoEstimado = gastoEstimado;
        this.ahorroEstimado = ahorroEstimado;
        this.saldoEstimado = saldoEstimado;
        this.saldoAnterior = saldoAnterior;
    }

    public long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getGasto() {
        return gasto;
    }

    public void setGasto(double gasto) {
        this.gasto = gasto;
    }

    public double getAhorro() {
        return ahorro;
    }

    public void setAhorro(double ahorro) {
        this.ahorro = ahorro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getAnyMes() {
        return anyMes;
    }

    public void setAnyMes(String anyMes) {
        this.anyMes = anyMes;
    }

    public String getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(String inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public String getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(String finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public double getIngresoEstimado() {
        return ingresoEstimado;
    }

    public void setIngresoEstimado(double ingresoEstimado) {
        this.ingresoEstimado = ingresoEstimado;
    }

    public double getGastoEstimado() {
        return gastoEstimado;
    }

    public void setGastoEstimado(double gastoEstimado) {
        this.gastoEstimado = gastoEstimado;
    }

    public double getAhorroEstimado() {
        return ahorroEstimado;
    }

    public void setAhorroEstimado(double ahorroEstimado) {
        this.ahorroEstimado = ahorroEstimado;
    }

    public double getSaldoEstimado() {
        return saldoEstimado;
    }

    public void setSaldoEstimado(double saldoEstimado) {
        this.saldoEstimado = saldoEstimado;
    }

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }
}
