package com.dtracker.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;

import com.dtracker.DTrackerApp;
import com.dtracker.R;
import com.dtracker.core.service.TrackingService;
import com.dtracker.ui.base.BaseTrackingServiceActivity;
import com.dtracker.ui.dialog.ConfirmationDialogFragment;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseTrackingServiceActivity implements TrackingService.OnTrackingListener {

    private static final int LOCATION_PERMISSION_CODE = 101;

    @InjectView(R.id.activity_main_bt_tracking)
    AppCompatButton btTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void inject() {
        DTrackerApp.getComponent(this).inject(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected TrackingService.OnTrackingListener getOnTrackingListener() {
        return this;
    }

    @OnClick(R.id.activity_main_bt_tracking)
    void onTrackingClick() {
        checkLocationPermission();
        if (trackingService.isTracking()) {
            trackingService.stopTracking();
        } else {
            ConfirmationDialogFragment.showDialog(getSupportFragmentManager(), R.string.start_tracking, R.string.start_tracking_warning, new ConfirmationDialogFragment.OnConfirmListener() {
                @Override
                public void onClick() {
                    trackingService.startTracking();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case LOCATION_PERMISSION_CODE: {
                    trackingService.startTracking();
                    break;
                }
            }
        }
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onTrackingStatusChanged(boolean isTracking) {
        btTracking.setText(isTracking?R.string.stop_tracking:R.string.start_tracking);
    }
}
