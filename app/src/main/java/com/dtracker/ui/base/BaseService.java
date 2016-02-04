package com.dtracker.ui.base;

import android.app.Service;

public abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    protected abstract void inject();
}
