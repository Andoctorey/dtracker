package com.dtracker.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dtracker.core.service.TrackingService;

public class BootReceiver extends BroadcastReceiver {


    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        TrackingService.start(context);
    }
}
