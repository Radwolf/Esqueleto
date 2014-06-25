package com.esqueleto.esqueletosdk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rgonzalez on 20/02/14.
 */
@DatabaseTable(tableName = "cuenta")
public class Cuenta extends Basic {

    public static final String COLUMN_NAME_USUARIO = "USUARIO_ID";
    private static final String COLUMN_NAME_DATESINC = "DATE_SINC";
    private static final String COLUMN_NAME_NOMBRE = "NOMBRE";

    @DatabaseField(foreign = true, columnName = COLUMN_NAME_USUARIO, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=2)
    private Usuario usuario;
    @DatabaseField(columnName = COLUMN_NAME_DATESINC)
    private Date dateSinc;
    @DatabaseField(columnName = COLUMN_NAME_NOMBRE)
    private String nombre;

    public Cuenta() {
    }

    public Cuenta(int _id, String dateCreate, String dateUpdate, String userCreate, String userUpdate, Usuario usuario, Date dateSinc, String nombre) {
        super(_id, dateCreate, dateUpdate, userCreate, userUpdate);
        this.usuario = usuario;
        this.dateSinc = dateSinc;
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDateSinc() {
        return dateSinc;
    }

    public void setDateSinc(Date dateSinc) {
        this.dateSinc = dateSinc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
