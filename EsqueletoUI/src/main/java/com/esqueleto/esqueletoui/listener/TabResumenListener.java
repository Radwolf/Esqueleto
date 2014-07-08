package com.esqueleto.esqueletoui.listener;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.Toast;

import com.esqueleto.esqueletoui.R;

/**
 * Created by rgonzalez on 08/07/2014.
 */
public class TabResumenListener implements ActionBar.TabListener {
    public Fragment fragment;
    public Context ctx;

    public TabResumenListener(Context ctx, Fragment fragment) {
        this.ctx = ctx;
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.content_frame, fragment);
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