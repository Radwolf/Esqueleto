package com.esqueleto.esqueletoui.adapter;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.ui.fragment.form.MesesFragment;

import java.util.Calendar;

/**
 * Created by rgonzalez on 10/07/2014.
 */
public class MesesPageAdapter extends FragmentStatePagerAdapter{

    public Context ctx;
    public Cuenta cuenta;
    private ActionBar actionBar;

    public MesesPageAdapter(FragmentManager fm, Context ctx, ActionBar actionBar, Cuenta cuenta) {
        super(fm);
        this.ctx = ctx;
        this.cuenta = cuenta;
        this.actionBar = actionBar;
    }

    @Override
    public Fragment getItem(int i) {
//        switch (i) {
//            case 0:
//                // The first section of the app is the most interesting -- it offers
//                // a launchpad into the other demonstrations in this example application.
//                return new MesesFragment();
//
//            default:
                Calendar calendar = Calendar.getInstance();
                int any = calendar.get(Calendar.YEAR);
                int mes = i+1;
                StringBuffer nombreTab = new StringBuffer();
                nombreTab.append(any).append("/").append((mes<10)?"0":"").append(mes);
                Bundle arguments = new Bundle();
                arguments.putParcelable("cuenta", cuenta);
                arguments.putCharSequence("anyMes", nombreTab);
                MesesFragment fragment = MesesFragment.newInstance(arguments);
                return fragment;
//        }

    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Calendar calendar = Calendar.getInstance();
        int any = calendar.get(Calendar.YEAR);
        int mes = position+1;
        StringBuffer nombreTab = new StringBuffer();
        nombreTab.append(any).append("/").append((mes<10)?"0":"").append(mes);
        return nombreTab;
    }

}
