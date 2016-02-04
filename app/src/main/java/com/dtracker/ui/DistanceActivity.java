package com.dtracker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.dtracker.DTrackerApp;
import com.dtracker.R;
import com.dtracker.core.service.TrackingService;
import com.dtracker.ui.base.BaseTrackingServiceActivity;

import butterknife.InjectView;

public class DistanceActivity extends BaseTrackingServiceActivity implements TrackingService.OnTrackingListener {

    @InjectView(R.id.activity_distance_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.activity_distance_tv_distance)
    TextView tvDistance;

    public static void start(Context context) {
        context.startActivity(new Intent(context, DistanceActivity.class));
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
        return R.layout.activity_distance;
    }

    @Override
    protected TrackingService.OnTrackingListener getOnTrackingListener() {
        return this;
    }

    @Override
    public void onTrackingStatusChanged(boolean isTracking) {

    }

    @Override
    public void onDistanceChange(float distance) {
        tvDistance.setText(String.valueOf(distance));
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
