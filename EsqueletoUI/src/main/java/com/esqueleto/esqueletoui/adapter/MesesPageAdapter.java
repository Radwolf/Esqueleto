package com.esqueleto.esqueletoui.adapter;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.ui.fragment.form.MesesFragment;
import com.esqueleto.esqueletoui.ui.fragment.form.ResumenFragment;

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
                Bundle arguments = new Bundle();
                arguments.putParcelable("cuenta", cuenta);
                arguments.putCharSequence("anyMes", actionBar.getTabAt(i).getText());
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

        return actionBar.getTabAt(position).getText();
    }

}
