package com.dtracker.core.jobs;

import com.dtracker.BuildConfig;
import com.path.android.jobqueue.log.CustomLogger;

import timber.log.Timber;

public class TimberJobQueueLogger implements CustomLogger {
    @Override
    public boolean isDebugEnabled() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void d(String text, Object... args) {
        Timber.d(text, args);
    }

    @Override
    public void e(Throwable t, String text, Object... args) {
        Timber.e(t, text, args);
    }

    @Override
    public void e(String text, Object... args) {
        Timber.e(text, args);
    }
}
