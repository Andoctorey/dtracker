package com.dtracker.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dtracker.DTrackerApp;
import com.dtracker.R;
import com.dtracker.ui.base.BaseTrackingServiceActivity;

public class MainActivity extends BaseTrackingServiceActivity {

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

}
