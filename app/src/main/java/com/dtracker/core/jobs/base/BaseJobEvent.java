package com.dtracker.core.jobs.base;

public abstract class BaseJobEvent {
    public final boolean isInProgress;
    public final Throwable error;

    protected BaseJobEvent(boolean isInProgress, Throwable error) {
        this.isInProgress = isInProgress;
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }
}
