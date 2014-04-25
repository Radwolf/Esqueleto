package com.esqueleto.esqueletoui;

import android.app.Application;

import com.esqueleto.esqueletoui.module.MainModule;

import dagger.ObjectGraph;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class EsqueletoApplication extends Application {

    /*
     * Attributes
     */

        private ObjectGraph objectGraph;

    /*
     * Application lifecycle methods.
     */

        @Override
        public void onCreate() {
            super.onCreate();
            initInjection();
        }

    /*
     * Auxiliary methods
     */

    private void initInjection() {
        MainModule mainModule = new MainModule(getBaseContext());
        objectGraph = ObjectGraph.create(mainModule);
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
