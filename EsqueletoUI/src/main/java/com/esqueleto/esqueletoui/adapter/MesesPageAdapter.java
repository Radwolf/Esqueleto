package com.esqueleto.esqueletoui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.ui.fragment.form.MesesFragment;
import com.esqueleto.esqueletoui.ui.fragment.form.ResumenFragment;

/**
 * Created by rgonzalez on 10/07/2014.
 */
public class MesesPageAdapter extends FragmentPagerAdapter{

    public Context ctx;
    public Cuenta cuenta;

    public MesesPageAdapter(FragmentManager fm, Context ctx, Cuenta cuenta) {
        super(fm);
        this.ctx = ctx;
        this.cuenta = cuenta;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                // The first section of the app is the most interesting -- it offers
                // a launchpad into the other demonstrations in this example application.
                return new ResumenFragment();

            default:
                // The other sections of the app are dummy placeholders.
                Bundle arguments = new Bundle();
                arguments.putParcelable("cuenta", cuenta);
//                arguments.putCharSequence("anyMes", tab.getText());
                MesesFragment fragment = MesesFragment.newInstance(arguments);
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Section " + (position + 1);
    }

}
