package com.dtracker.core.store.impl;

import com.dtracker.core.store.PrefsDataStore;
import com.dtracker.core.store.base.ObscuredSharedPreferences;

import javax.inject.Inject;

public class PrefsDataStoreImpl implements PrefsDataStore {

    private final ObscuredSharedPreferences obscuredSharedPreferences;

    @Inject
    public PrefsDataStoreImpl(ObscuredSharedPreferences obscuredSharedPreferences) {
        this.obscuredSharedPreferences = obscuredSharedPreferences;
    }


    @Override
    public void setTrackingEnabled(boolean enable) {
        obscuredSharedPreferences.edit().putBoolean(TRACKING_ENABLED, enable).apply();
    }

    @Override
    public boolean isTrackingEnable() {
        return obscuredSharedPreferences.getBoolean(TRACKING_ENABLED, false);
    }

    @Override
    public void setDistance(float distance) {
        obscuredSharedPreferences.edit().putFloat(DISTANCE, distance).apply();
    }

    @Override
    public float getDistance() {
        return obscuredSharedPreferences.getFloat(DISTANCE, 0);
    }
}
