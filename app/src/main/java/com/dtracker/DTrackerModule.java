package com.dtracker;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.location.LocationManager;
import android.preference.PreferenceManager;

import com.dtracker.core.bus.AndroidBus;
import com.dtracker.core.dagger.PerApp;
import com.dtracker.core.jobs.TimberJobQueueLogger;
import com.dtracker.core.store.PrefsDataStore;
import com.dtracker.core.store.impl.PrefsDataStoreImpl;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.squareup.otto.Bus;

import java.util.Locale;

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

    @Provides
    @PerApp
    JobManager provideJobManager(Context context) {
        Configuration configuration = new Configuration.Builder(context)
                .customLogger(new TimberJobQueueLogger())
                .loadFactor(1)
                .build();
        return new JobManager(context, configuration);
    }

    @Provides
    @PerApp
    Bus provideBus() {
        return new AndroidBus();
    }

    @Provides
    @PerApp
    Geocoder provideApiFactory(Context context) {
        return new Geocoder(context, Locale.getDefault());
    }

    @Provides
    @PerApp
    SharedPreferences provideDefaultSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @PerApp
    PrefsDataStore providePrefsDataStore(PrefsDataStoreImpl store) {
        return store;
    }
}
