package com.esqueleto.esqueletosdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "resumen")
public class Resumen implements Parcelable{

    public static final String COLUMN_NAME_ID = "resumen_id";
    public static final String COLUMN_NAME_CUENTA = "cuenta_id";
    private static final String COLUMN_NAME_INGRESO = "ingreso";
    private static final String COLUMN_NAME_GASTO = "gasto";
    private static final String COLUMN_NAME_AHORRO = "ahorro";
    private static final String COLUMN_NAME_SALDO = "saldo";
    public static final String COLUMN_NAME_ANYMES = "any_mes";
    private static final String COLUMN_NAME_INICIOPERIODO = "inicio_periodo";
    private static final String COLUMN_NAME_FINPERIODO = "fin_periodo";
    private static final String COLUMN_NAME_INGRESOESTIMADO = "ingreso_estimado";
    private static final String COLUMN_NAME_GASTOESTIMADO = "gasto_estimado";
    private static final String COLUMN_NAME_AHORROESTIMADO = "ahorro_estimado";
    private static final String COLUMN_NAME_SALDOESTIMADO = "saldo_estimado";
    private static final String COLUMN_NAME_SALDOANTERIOR = "saldo_anterior";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = COLUMN_NAME_ID)
    private int _id;
    @DatabaseField(canBeNull = false, foreign = true, columnName = COLUMN_NAME_CUENTA)
    private Cuenta cuenta;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_INGRESO)
    private double ingreso;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_GASTO)
    private double gasto;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_AHORRO)
    private double ahorro;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_SALDO)
    private double saldo;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_ANYMES)
    private String anyMes;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_INICIOPERIODO)
    private Date inicioPeriodo;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_FINPERIODO)
    private Date finPeriodo;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_INGRESOESTIMADO)
    private double ingresoEstimado;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_GASTOESTIMADO)
    private double gastoEstimado;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_AHORROESTIMADO)
    private double ahorroEstimado;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_SALDOESTIMADO)
    private double saldoEstimado;
    @DatabaseField(canBeNull = false, columnName = COLUMN_NAME_SALDOANTERIOR)
    private double saldoAnterior;

    public Resumen() {
    }

    public Resumen(int _id, String dateCreate, String dateUpdate, String userCreate,
                   String userUpdate, Cuenta cuenta, double ingreso, double gasto, double ahorro,
                   double saldo, String anyMes, Date inicioPeriodo, Date finPeriodo,
                   double ingresoEstimado, double gastoEstimado, double ahorroEstimado,
                   double saldoEstimado, double saldoAnterior) {
        this._id = _id;
        this.cuenta = cuenta;
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

    //<editor-fold desc="SETTERS Y GETTERS">
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
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
    //</editor-fold>

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this._id);
        parcel.writeParcelable(this.cuenta, flags);
        parcel.writeDouble(this.ingreso);
        parcel.writeDouble(this.gasto);
        parcel.writeDouble(this.ahorro);
        parcel.writeDouble(this.saldo);
        parcel.writeString(this.anyMes);
        parcel.writeSerializable(this.inicioPeriodo);
        parcel.writeSerializable(this.finPeriodo);
        parcel.writeDouble(this.ingresoEstimado);
        parcel.writeDouble(this.gastoEstimado);
        parcel.writeDouble(this.ahorroEstimado);
        parcel.writeDouble(this.saldoEstimado);
        parcel.writeDouble(this.saldoAnterior);
    }
}
