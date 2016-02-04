package com.dtracker.core.store;

public interface PrefsDataStore {

    String TRACKING_ENABLED = "TRACKING_ENABLED";
    String DISTANCE = "DISTANCE";

    void setTrackingEnabled(boolean enable);

    boolean isTrackingEnable();

    void setDistance(float distance);

    float getDistance();

}
