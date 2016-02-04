package com.dtracker;


import com.dtracker.core.dagger.PerApp;
import com.dtracker.core.service.TrackingService;
import com.dtracker.ui.MainActivity;

import dagger.Component;

@PerApp
@Component(
        modules = {
                DTrackerModule.class
        }
)
public interface DTrackerAppComponent {

    void inject(MainActivity mainActivity);

    void inject(TrackingService trackingService);
}
