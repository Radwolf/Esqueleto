package com.esqueleto.esqueletosdk.command.impl;

import android.content.Context;

import com.esqueleto.esqueletosdk.command.ImportCommand;
import com.esqueleto.esqueletosdk.xml.ParseXMLImportDatosDB;

import java.io.InputStream;

/**
 * Created by Raúl on 14/08/2014.
 */
public class ImportCuenta implements ImportCommand {

    Context ctx;
    InputStream inXml;
    ParseXMLImportDatosDB parseXMLImportDatosDB;

    public ImportCuenta(Context ctx, InputStream inXml) {
        this.ctx = ctx;
        this.inXml = inXml;
    }

    @Override
    public void execute() {
        parseXMLImportDatosDB = new ParseXMLImportDatosDB(ctx, inXml);
        parseXMLImportDatosDB.parsearXml();
    }

    @Override
    public void undo() {

    }
}
