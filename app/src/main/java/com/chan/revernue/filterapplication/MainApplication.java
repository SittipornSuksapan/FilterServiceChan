package com.chan.revernue.filterapplication;

import android.app.Application;

import com.chan.revernue.filterapplication.manager.Contextor;


public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
