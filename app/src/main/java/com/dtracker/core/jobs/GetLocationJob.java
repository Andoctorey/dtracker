package com.dtracker.core.jobs;

import android.location.Location;

import com.dtracker.core.service.GeocodeService;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.squareup.otto.Bus;

import java.io.IOException;

import javax.inject.Inject;

public class GetLocationJob extends Job {

    @Inject
    protected transient GeocodeService geocodeService;

    @Inject
    protected transient Bus bus;

    private  Location location;

    public GetLocationJob() {
        super(new Params(0));
    }

    public GetLocationJob init(Location location) {
        this.location = location;
        return this;
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {
        try {
            bus.post(OnGetLocationEvent.createGotLocation(geocodeService.getAddressFromLocation(location)));
        } catch (IOException | IllegalArgumentException error) {
            bus.post(OnGetLocationEvent.createWithError(error));
        }
    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}