package com.dtracker;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import timber.log.Timber;


public class DTrackerApp extends Application {

    private DTrackerAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogging();
        initStrictMode();
        component = DaggerDTrackerAppComponent.builder()
                .dTrackerModule(new DTrackerModule(this))
                .build();
    }

    public static DTrackerAppComponent getComponent(Context context) {
        return ((DTrackerApp) context.getApplicationContext()).component;
    }

    private void initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll()
                    .penaltyLog().build());
        }
    }
}
