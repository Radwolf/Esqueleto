package com.esqueleto.esqueletoui.ui.pager;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.esqueleto.esqueletoui.ui.fragment.form.FormMovimientoFragment;
import com.esqueleto.esqueletoui.ui.fragment.list.ListaMovimientosFragment;

/**
 * Created by RUL on 30/06/2014.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FormMovimientoFragment();
            case 1: return new ListaMovimientosFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
