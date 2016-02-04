package com.dtracker.core.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.dtracker.DTrackerApp;
import com.dtracker.ui.base.BaseService;

public class TrackingService extends BaseService {

    @Override
    protected void inject() {
        DTrackerApp.getComponent(this).inject(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public TrackingService getService() {
            return TrackingService.this;
        }
    }
}
