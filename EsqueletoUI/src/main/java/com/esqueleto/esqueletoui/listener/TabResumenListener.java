package com.esqueleto.esqueletoui.listener;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.R;
import com.esqueleto.esqueletoui.ui.fragment.form.MesesFragment;

/**
 * Created by rgonzalez on 08/07/2014.
 */
public class TabResumenListener implements ActionBar.TabListener {
    public Fragment fragment;
    public Context ctx;
    public Cuenta cuenta;

    public TabResumenListener(Context ctx, Fragment fragment, Cuenta cuenta) {
        this.ctx = ctx;
        this.fragment = fragment;
        this.cuenta = cuenta;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        Bundle arguments = new Bundle();
        arguments.putParcelable("cuenta", cuenta);
        arguments.putCharSequence("anyMes", tab.getText());
        MesesFragment newFragment = MesesFragment.newInstance(arguments);
        ft.replace(R.id.content_frame, newFragment, MesesFragment.TAG);
//        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        Toast.makeText(ctx, "Reselected!", Toast.LENGTH_LONG).show();
    }

}