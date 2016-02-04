package com.dtracker.core.jobs;


import com.dtracker.core.jobs.base.BaseJobEvent;

public class OnGetLocationEvent extends BaseJobEvent {

    public final String location;

    public OnGetLocationEvent(boolean isInProgress, Throwable error, String location) {
        super(isInProgress, error);
        this.location = location;
    }

    public static OnGetLocationEvent createGotLocation(String location) {
        return new OnGetLocationEvent(false, null, location);
    }

    public static OnGetLocationEvent createWithError(Throwable error) {
        return new OnGetLocationEvent(false, error, null);
    }
}
