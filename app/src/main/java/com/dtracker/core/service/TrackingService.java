package com.dtracker.core.service;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

import com.dtracker.DTrackerApp;
import com.dtracker.ui.base.BaseService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class TrackingService extends BaseService implements LocationListener {

    public interface OnTrackingListener {
        void onTrackingStatusChanged(boolean isTracking );

        void onDistanceChange(float distance);

        void onLocationChanged(Location location);
    }

    private static final long MIN_TIME_MS = 10000;
    private static final float MIN_DISTANCE_M = 1;

    @Inject
    LocationManager locationManager;

    private Location prevLocation;
    private float distance;
    private boolean isTracking;
    private List<OnTrackingListener> trackingListeners = new ArrayList<>();

    @Override
    protected void inject() {
        DTrackerApp.getComponent(this).inject(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Timber.i("onBind");
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public TrackingService getService() {
            return TrackingService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.i("onStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy");
    }

    public void startTracking() {
        Timber.i("startTracking");
        isTracking = true;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_MS, MIN_DISTANCE_M, this);
        onTrackingStatusChanged(true);
    }

    private void onTrackingStatusChanged(boolean enabled) {
        if (trackingListeners!=null&&trackingListeners.size()>0){
            for (OnTrackingListener trackingListener : trackingListeners) {
                trackingListener.onTrackingStatusChanged(enabled);
            }
        }
    }

    public void stopTracking() {
        Timber.i("stopTracking");
        isTracking = false;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(this);
        onTrackingStatusChanged(false);
    }

    @Override
    public void onLocationChanged(Location location) {
        Timber.i("onLocationChanged "+location.toString());
        if (prevLocation!=null){
            distance+=location.distanceTo(prevLocation);
            Timber.i("distance "+distance);
            if (trackingListeners!=null&&trackingListeners.size()>0){
                for (OnTrackingListener trackingListener : trackingListeners) {
                    trackingListener.onDistanceChange(distance);
                }
                for (OnTrackingListener trackingListener : trackingListeners) {
                    trackingListener.onLocationChanged(location);
                }
            }
        }
        prevLocation=location;

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Timber.i("onStatusChanged " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Timber.i("onProviderEnabled "+provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Timber.i("onProviderDisabled "+provider);
    }

    public boolean isTracking() {
        return isTracking;
    }


    public void addOnTrackingListener(OnTrackingListener onTrackingListener) {
        trackingListeners.add(onTrackingListener);
    }

    public void removeOnTrackingListener(OnTrackingListener onTrackingListener) {
        trackingListeners.remove(onTrackingListener);
    }

    public float getDistance() {
        return distance;
    }

    public Location getLocation() {
        return prevLocation;
    }
}

