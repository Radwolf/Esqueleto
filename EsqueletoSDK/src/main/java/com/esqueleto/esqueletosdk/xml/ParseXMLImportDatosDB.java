package com.esqueleto.esqueletosdk.xml;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.esqueleto.esqueletosdk.iteractor.MovimientoInteractor;
import com.esqueleto.esqueletosdk.iteractor.UsuarioInteractor;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorMovimiento;
import com.esqueleto.esqueletosdk.iteractor.impl.GestorUsuario;
import com.esqueleto.esqueletosdk.model.Categoria;
import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletosdk.model.Movimiento;
import com.esqueleto.esqueletosdk.model.Resumen;
import com.esqueleto.esqueletosdk.model.TipoMovimiento;
import com.esqueleto.esqueletosdk.model.Usuario;
import com.esqueleto.esqueletosdk.repository.CategoriaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.CuentaRepositoryDB;
import com.esqueleto.esqueletosdk.repository.MovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.ResumenRepositoryDB;
import com.esqueleto.esqueletosdk.repository.TipoMovimientoRepositoryDB;
import com.esqueleto.esqueletosdk.repository.UsuarioRepositoryDB;
import com.esqueleto.esqueletosdk.repository.impl.CategoriaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.CuentaRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.MovimientoRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.ResumenRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.TipoMovimientoRepositoryDBImpl;
import com.esqueleto.esqueletosdk.repository.impl.UsuarioRepositoryDBImpl;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Raúl on 06/08/2014.
 */
public class ParseXMLImportDatosDB {

    private static final String TAG_LOG_ERROR_CONTROLADO = "ERROR_CONTROLADO";
    XmlPullParserFactory pullParserFactory;
    private Context context;
    private InputStream inXml;
    private final String TAG_LOG_IMPORT = "LOG_IMPORT";

    private static CuentaRepositoryDB cuentaRepositoryDB;
    private static UsuarioRepositoryDB usuarioRepositoryDB;
    private static ResumenRepositoryDB resumenRepositoryDB;
    private static MovimientoRepositoryDB movimientoRepositoryDB;
    private static CategoriaRepositoryDB categoriaRepositoryDB;
    private static TipoMovimientoRepositoryDB tipoMovimientoRepositoryDB;
    private static MovimientoInteractor movimientoInteractor;

    public ParseXMLImportDatosDB(Context context, InputStream inXml) {
        this.context = context;
        this.inXml = inXml;
        this.cuentaRepositoryDB = new CuentaRepositoryDBImpl(context);
        this.usuarioRepositoryDB = new UsuarioRepositoryDBImpl(context);
        this.resumenRepositoryDB = new ResumenRepositoryDBImpl(context);
        this.movimientoRepositoryDB = new MovimientoRepositoryDBImpl(context);
        this.categoriaRepositoryDB = new CategoriaRepositoryDBImpl(context);
        this.tipoMovimientoRepositoryDB = new TipoMovimientoRepositoryDBImpl(context);
        this.movimientoInteractor = new GestorMovimiento(context);
    }

    public void parsearXml(){
//        if("cuenta".equals(tipoXML)) {

            XmlPullParserFactory pullParserFactory;
            try {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(inXml, null);

                parseAppCuentaXML(parser);

            } catch (XmlPullParserException e) {

                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//        }
    }

    public void parseAppCuentaXML(XmlPullParser parser) throws XmlPullParserException,IOException
    {
        ArrayList<Cuenta> cuentas = null;
        ArrayList<Resumen> resumenes = null;
        int eventType = parser.getEventType();
        Usuario usuario = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    Log.i(TAG_LOG_IMPORT, "Inicio de la importación.");
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if ("usuario".equals(name)){
                        usuario = new Usuario();
                        eventType = importarUsuario(parser, usuario);
                    }else if("cuentas".equals(name)){
                        cuentas = new ArrayList<Cuenta>();
                        eventType = importarCuentas(parser, cuentas);
                    }else if("resumenes".equals(name)){
                        resumenes = new ArrayList<Resumen>();
                        eventType = importarResumenes(parser, resumenes);
                    }
                    break;
//                case XmlPullParser.END_TAG:
//                    name = parser.getName();
////                    if (name.equalsIgnoreCase("product") && currentProduct != null){
////                        products.add(currentProduct);
////                    }
//                    if ("usuario".equalsIgnoreCase(name) && usuario != null) {
//                        Log.i(TAG_LOG_IMPORT, "Usuario parseado.");
//                    }
            }
            eventType = parser.next();
        }

//        printProducts(products);
    }

    private int importarMovimientos(XmlPullParser parser, ArrayList<Movimiento> movimientos) {
        boolean movimientosParseados = false;
        Movimiento movimiento = null;
        try {
            int eventType = parser.next();
            while (!movimientosParseados && eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                if(movimientos != null) {
                    if("movimiento".equals(name)){
                        if(eventType != XmlPullParser.END_TAG){
                            movimiento = new Movimiento();
                            Log.i(TAG_LOG_IMPORT, String.format("Iniciamos el parseo de una movimiento."));
                        }else {
                            movimientos.add(movimiento);
                            Log.i(TAG_LOG_IMPORT, String.format("Añadimos el movimiento"));

                        }
                    }else if("categoria".equals(name) && eventType != XmlPullParser.END_TAG){
                        String categoria = parser.nextText();
                        movimiento.setCategoria(categoriaRepositoryDB.getCategoriaByClave(categoria));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos la categoria %s asociada al movimiento.", categoria));
                    }else if("tipo_movimiento".equals(name) && eventType != XmlPullParser.END_TAG){
                        String tipoMovimiento = parser.nextText();
                        movimiento.setTipoMovimiento(tipoMovimientoRepositoryDB.getTipoMovimientoByClave(tipoMovimiento));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el tipo movimiento %s asociado al movimiento.",tipoMovimiento));
                    }else if("concepto".equals(name) && eventType != XmlPullParser.END_TAG){
                        movimiento.setConcepto(parser.nextText());
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos la categoria %s asociada al movimiento.", movimiento.getConcepto()));
                    }else if("importe".equals(name) && eventType != XmlPullParser.END_TAG){
                        movimiento.setImporte(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos la importe %f asociado al movimiento.", movimiento.getImporte()));
                    }else if("importe_estimado".equals(name) && eventType != XmlPullParser.END_TAG){
                        movimiento.setImporteEstimado(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos la importe_estimado %f asociado al movimiento.", movimiento.getImporteEstimado()));
                    }else if("fecha_estimada".equals(name) && eventType != XmlPullParser.END_TAG){
                        movimiento.setFechaEstimada(stringToDate(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos la fecha_estimada %s del movimiento.", (movimiento.getFechaEstimada()==null)?"":movimiento.getFechaEstimada().toString()));
                    }else if("fecha_movimiento".equals(name) && eventType != XmlPullParser.END_TAG) {
                        movimiento.setFechaMovimiento(stringToDate(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos la fecha_movimiento %s del movimiento.", (movimiento.getFechaMovimiento()==null)?"":movimiento.getFechaMovimiento().toString()));
                    }
                }
                if ("movimientos".equalsIgnoreCase(name) && movimientos != null) {
                    Log.i(TAG_LOG_IMPORT, "Movimientos parseados.");
                    movimientosParseados = true;
                }
                eventType = parser.next();
            }
            return eventType;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int importarResumenes(XmlPullParser parser, ArrayList<Resumen> resumenes) {
        boolean resumenesParseados = false;
        Resumen resumen = null;
        ArrayList<Movimiento> movimientos = null;
        try {
            int eventType = parser.next();
            while (!resumenesParseados && eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                if(resumenes != null) {
                    if("resumen".equals(name)){
                        if(eventType != XmlPullParser.END_TAG){
                            resumen = new Resumen();
                            Log.i(TAG_LOG_IMPORT, String.format("Iniciamos el parseo de una resumen."));
                        }else {
                            resumenes.add(resumen);
                            Log.i(TAG_LOG_IMPORT, String.format("Añadimos el resumen %d", resumen.get_id()));
                            Resumen resumenDB = resumenRepositoryDB.getResumen(resumen.getCuenta(), resumen.getAnyMes());
                            if(resumenDB!=null){
                                populateResumenXMLToDB(resumenDB, resumen);
                                resumenRepositoryDB.update(resumenDB);
                                Log.i(TAG_LOG_IMPORT, String.format("Actualizamos el resumen %d", resumenDB.get_id()));
                            }else {
                                resumenRepositoryDB.create(resumen);
                                Log.i(TAG_LOG_IMPORT, String.format("Creamos el resumen %d", resumen.get_id()));
                            }
                            if(movimientos!=null) {
                                for (Movimiento movimiento : movimientos) {
                                    movimiento.setResumen(resumen);
                                    movimientoInteractor.addMovimiento(resumen, movimiento.getTipoMovimiento().getClave(), movimiento.getImporte(),
                                            movimiento.getFechaEstimada(), movimiento.getFechaMovimiento(), movimiento.getCategoria().getClave(),
                                            movimiento.getConcepto());
                                }
                            }
                        }
                    }else if("id_resumen".equals(name) && eventType != XmlPullParser.END_TAG){
                        resumen.set_id(Integer.parseInt(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el _id del resumen '%d'.", resumen.get_id()));
                    }else if("cuenta".equals(name) && eventType != XmlPullParser.END_TAG){
                        int idCuenta = Integer.parseInt(parser.nextText());
                        resumen.setCuenta(cuentaRepositoryDB.getCuenta(idCuenta));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos la cuenta con id '%d' del resumen '%d'.", idCuenta, resumen.get_id()));
                    }else if("any_mes".equals(name) && eventType != XmlPullParser.END_TAG){
                        resumen.setAnyMes(parser.nextText());
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el any_mes %s del resumen '%d'.", resumen.getAnyMes(), resumen.get_id()));
                    }else if("ahorro".equals(name)) {
                        resumen.setAhorro(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe ahorro %f del resumen '%d'.", resumen.getAhorro(), resumen.get_id()));
                    }else if("ahorro_estimado".equals(name)) {
                        resumen.setAhorroEstimado(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe ahorro_estimado %f del resumen '%d'.", resumen.getAhorroEstimado(), resumen.get_id()));
                    }else if("gasto".equals(name)) {
                        resumen.setGasto(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe gasto %f del resumen '%d'.", resumen.getGasto(), resumen.get_id()));
                    }else if("gasto_estimado".equals(name) && eventType != XmlPullParser.END_TAG) {
                        resumen.setGastoEstimado(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe gasto_estimado %f del resumen '%d'.", resumen.getGastoEstimado(), resumen.get_id()));
                    }else if("ingreso".equals(name)) {
                        resumen.setIngreso(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe ingreso %f del resumen '%d'.", resumen.getIngreso(), resumen.get_id()));
                    }else if("ingreso_estimado".equals(name) && eventType != XmlPullParser.END_TAG) {
                        resumen.setIngresoEstimado(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe ingreso_estimado %f del resumen '%d'.", resumen.getIngresoEstimado(), resumen.get_id()));
                    }else if("saldo".equals(name)) {
                        resumen.setSaldo(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe saldo %f del resumen '%d'.", resumen.getSaldo(), resumen.get_id()));
                    }else if("saldo_anterior".equals(name) && eventType != XmlPullParser.END_TAG) {
                        resumen.setSaldoAnterior(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe saldo_anterior %f del resumen '%d'.", resumen.getSaldoAnterior(), resumen.get_id()));
                    }else if("saldo_estimado".equals(name) && eventType != XmlPullParser.END_TAG) {
                        resumen.setSaldoEstimado(Double.parseDouble(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe saldo_estimado %f del resumen '%d'.", resumen.getSaldoEstimado(), resumen.get_id()));
                    }else if("inicio_periodo".equals(name) && eventType != XmlPullParser.END_TAG) {
                        resumen.setInicioPeriodo(stringToDate(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe inicio_periodo %s del resumen '%d'.", resumen.getInicioPeriodo().toString(), resumen.get_id()));
                    }else if("fin_periodo".equals(name) && eventType != XmlPullParser.END_TAG) {
                        resumen.setFinPeriodo(stringToDate(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el importe fin_periodo %s del resumen '%d'.", resumen.getFinPeriodo().toString(), resumen.get_id()));
                    }else if("movimientos".equals(name) && eventType != XmlPullParser.END_TAG){
                        movimientos = new ArrayList<Movimiento>();
                        eventType = importarMovimientos(parser, movimientos);
                        movimientoRepositoryDB.deleteAllByResumen(resumen);
                    }
                }
                if ("resumenes".equalsIgnoreCase(name) && resumenes != null) {
                    Log.i(TAG_LOG_IMPORT, "Resumenes parseados.");
                    resumenesParseados = true;
                }
                eventType = parser.next();
            }
            return eventType;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void populateResumenXMLToDB(Resumen resumenDB, Resumen resumen) {
        resumenDB.setFinPeriodo(resumen.getFinPeriodo());
        resumenDB.setInicioPeriodo(resumen.getInicioPeriodo());
        resumenDB.setSaldoEstimado(resumen.getSaldoEstimado());
        resumenDB.setSaldo(resumen.getSaldo());
        resumenDB.setSaldoAnterior(resumen.getSaldoAnterior());
        resumenDB.setAhorro(resumen.getAhorro());
        resumenDB.setAhorroEstimado(resumen.getAhorroEstimado());
        resumenDB.setGasto(resumen.getGasto());
        resumenDB.setGastoEstimado(resumen.getGastoEstimado());
        resumenDB.setIngreso(resumen.getIngreso());
        resumenDB.setIngresoEstimado(resumen.getIngresoEstimado());
    }

    private int importarCuentas(XmlPullParser parser, ArrayList<Cuenta> cuentas) {
        boolean cuentasParseadas = false;
        Cuenta cuenta = null;
        boolean cuentaExistente = false;
        try {
            int eventType = parser.next();
            while (!cuentasParseadas && eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                if(cuentas != null) {
                    if("cuenta".equals(name)){
                        if(eventType != XmlPullParser.END_TAG){
                            cuenta = new Cuenta();
                            Log.i(TAG_LOG_IMPORT, String.format("Iniciamos el parseo de una cuenta."));
                        }else {
                            cuentas.add(cuenta);
                            Log.i(TAG_LOG_IMPORT, String.format("Añadimos la cuenta %d", cuenta.get_id()));
                            Cuenta cuentaDB = cuentaRepositoryDB.getCuenta(cuenta.get_id());
                            if(cuentaDB != null){
                                populateCuentaXMLToDB(cuentaDB, cuenta);
                                cuentaRepositoryDB.update(cuentaDB);
                                Log.i(TAG_LOG_IMPORT, String.format("Actualizamos la cuenta %d", cuentaDB.get_id()));
                            }else {
                                cuentaRepositoryDB.create(cuenta);
                                Log.i(TAG_LOG_IMPORT, String.format("Creamos la cuenta %d", cuenta.get_id()));
                            }
                        }
                    }else if("id_cuenta".equals(name) && eventType != XmlPullParser.END_TAG){
                        int idCuenta = Integer.parseInt(parser.nextText());
                        cuenta.set_id(idCuenta);
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el _id de la cuenta '%d'.", cuenta.get_id()));
                    }else if("usuario".equals(name) && eventType != XmlPullParser.END_TAG){
                        Usuario usuario = usuarioRepositoryDB.getUsuario(Integer.parseInt(parser.nextText()));
                        cuenta.setUsuario(usuario);
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el usuario con id '%d' de la cuenta '%d'.", usuario.get_id(), cuenta.get_id()));
                    }else if("nombre".equals(name) && eventType != XmlPullParser.END_TAG){
                        cuenta.setNombre(parser.nextText());
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el nombre %s de la cuenta '%d'.", cuenta.getNombre(), cuenta.get_id()));
                    }else if("seleccionada".equals(name) && eventType != XmlPullParser.END_TAG){
                        Boolean bSeleccionada = Boolean.parseBoolean(parser.nextText());
                        cuenta.setSeleccionada(bSeleccionada.booleanValue());
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos si la cuenta '%d', esta o no seleccionada %b.", cuenta.get_id(), cuenta.isSeleccionada()));
                    }
                }
                if ("cuentas".equalsIgnoreCase(name) && cuentas != null) {
                    Log.i(TAG_LOG_IMPORT, "Cuentas parseadas.");
                    cuentasParseadas = true;
                }
                eventType = parser.next();
            }
            return eventType;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void populateCuentaXMLToDB(Cuenta cuentaDB, Cuenta cuenta) {
        cuentaDB.setSeleccionada(cuenta.isSeleccionada());
        cuentaDB.setNombre(cuenta.getNombre());
        cuentaDB.setDateSinc((cuenta.getDateSinc()!=null)?cuenta.getDateSinc():new Date());
    }

    private int importarUsuario(XmlPullParser parser, Usuario usuario) {
        try {
            boolean usuarioParseado = false;
            int eventType = parser.next();
            while (!usuarioParseado && eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                if (usuario != null) {
                    if ("email".equals(name) && eventType != XmlPullParser.END_TAG) {
                        usuario.setEmail(parser.nextText());
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el email '%s' de un usuario.", usuario.getEmail()));
                    } else if ("id_usuario".equals(name) && eventType != XmlPullParser.END_TAG) {
                        usuario.set_id(Integer.parseInt(parser.nextText()));
                        Log.i(TAG_LOG_IMPORT, String.format("Parseamos el _id '%d' de un usuario.", usuario.get_id()));
                    }
                }
                if ("usuario".equalsIgnoreCase(name) && usuario != null) {
                    Log.i(TAG_LOG_IMPORT, "Usuario parseado.");
                    usuarioParseado = true;
                    if(usuarioRepositoryDB.getUsuario(usuario.getEmail()) == null) {
                        usuarioRepositoryDB.create(usuario);
                    }else{
                        Log.d(TAG_LOG_ERROR_CONTROLADO, String.format("El usuario %s ya existe y no lo añadimos.", usuario.getEmail()));
                    }

                }
                eventType = parser.next();
            }
            return eventType;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private Date stringToDate(String sDate){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(sDate);
        } catch (ParseException ex) {
//            ex.printStackTrace();
            return null;
        }
        return fecha;
    }
}
