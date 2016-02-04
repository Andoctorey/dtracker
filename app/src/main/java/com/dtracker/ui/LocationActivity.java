package com.dtracker.ui;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.dtracker.DTrackerApp;
import com.dtracker.R;
import com.dtracker.core.jobs.GetLocationJob;
import com.dtracker.core.jobs.OnGetLocationEvent;
import com.dtracker.core.service.TrackingService;
import com.dtracker.ui.base.BaseTrackingServiceActivity;
import com.path.android.jobqueue.JobManager;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.InjectView;

public class LocationActivity extends BaseTrackingServiceActivity implements TrackingService.OnTrackingListener {

    @InjectView(R.id.activity_location_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.activity_location_tv_location)
    TextView tvLocation;

    @Inject
    JobManager jobManager;

    @Inject
    GetLocationJob getLocationJob;

    public static void start(Context context) {
        context.startActivity(new Intent(context, LocationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
    }

    @Override
    protected void inject() {
        DTrackerApp.getComponent(this).inject(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_location;
    }

    @Override
    protected TrackingService.OnTrackingListener getOnTrackingListener() {
        return this;
    }

    @Override
    public void onTrackingStatusChanged(boolean isTracking) {

    }

    @Override
    public void onDistanceChange(float location) {
    }

    @Override
    public void onLocationChanged(Location location) {
        jobManager.addJob(getLocationJob.init(location));
    }

    @Subscribe
    public void onGetLocationEvent(OnGetLocationEvent event) {
        if (event.location != null) {
           tvLocation.setText(event.location);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
