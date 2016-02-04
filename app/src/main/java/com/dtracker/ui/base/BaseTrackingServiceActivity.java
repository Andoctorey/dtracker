package com.dtracker.ui.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.dtracker.core.service.TrackingService;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public abstract class BaseTrackingServiceActivity extends BaseActivity {

    protected boolean boundToTrackingService;
    protected TrackingService trackingService;

    protected abstract TrackingService.OnTrackingListener getOnTrackingListener();

    @Inject
    Bus bus;

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
        Intent intent = new Intent(this, TrackingService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
        if (boundToTrackingService) {
            unbindService(connection);
            trackingService.removeOnTrackingListener(getOnTrackingListener());
            boundToTrackingService = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            TrackingService.LocalBinder binder = (TrackingService.LocalBinder) service;
            trackingService = binder.getService();
            trackingService.addOnTrackingListener(getOnTrackingListener());
            getOnTrackingListener().onDistanceChange(trackingService.getDistance());
            getOnTrackingListener().onTrackingStatusChanged(trackingService.isTracking());
            getOnTrackingListener().onLocationChanged(trackingService.getLocation());
            boundToTrackingService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            boundToTrackingService = false;
        }
    };
}
