package com.esqueleto.esqueletoui;

import android.app.Application;

import butterknife.ButterKnife;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class EsqueletoApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
