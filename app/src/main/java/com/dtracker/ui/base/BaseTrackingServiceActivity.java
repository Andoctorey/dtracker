package com.dtracker.ui.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.dtracker.core.service.TrackingService;

public abstract class BaseTrackingServiceActivity extends BaseActivity {

    protected boolean boundToTrackingService;
    protected TrackingService trackingService;

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(this, TrackingService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (boundToTrackingService) {
            unbindService(connection);
            boundToTrackingService = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            TrackingService.LocalBinder binder = (TrackingService.LocalBinder) service;
            trackingService = binder.getService();
            boundToTrackingService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            boundToTrackingService = false;
        }
    };
}
