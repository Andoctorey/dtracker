package com.dtracker;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.dtracker.core.dagger.PerApp;

import dagger.Module;
import dagger.Provides;

@Module
public class DTrackerModule {

    final DTrackerApp app;

    public DTrackerModule(DTrackerApp app) {
        this.app = app;
    }

    @Provides
    Context provideContext() {
        return app.getBaseContext();
    }

    @Provides
    @PerApp
    DTrackerApp provideDTrackerApp() {
        return app;
    }

    @Provides
    @PerApp
    Application provideApplication(DTrackerApp app) {
        return app;
    }

    @Provides
    @PerApp
    LocationManager provideLocationManager(final Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
