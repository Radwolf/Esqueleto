package com.esqueleto.esqueletoui.model;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.pedrogomez.renderers.AdapteeCollection;

import java.util.Collection;
import java.util.List;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class CuentaCollection implements AdapteeCollection<Cuenta> {

    /*
     * Attributes
     */
    private final List<Cuenta> cuentas;

    public CuentaCollection(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    /*
     * Implemented methods
     */

    @Override
    public int size() {
        return cuentas.size();
    }

    @Override
    public Cuenta get(final int index) {
        return cuentas.get(index);
    }

    @Override
    public void add(Cuenta element) {
        cuentas.add(element);
    }

    @Override
    public void remove(Cuenta element) {
        cuentas.remove(element);
    }

    @Override
    public void addAll(Collection<Cuenta> elements) {
        cuentas.addAll(elements);
    }

    @Override
    public void removeAll(Collection<Cuenta> elements) {
        cuentas.removeAll(elements);
    }
}
