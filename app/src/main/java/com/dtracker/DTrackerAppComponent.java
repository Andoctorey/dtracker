package com.dtracker;


import com.dtracker.core.dagger.PerApp;
import com.dtracker.core.service.TrackingService;
import com.dtracker.ui.DistanceActivity;
import com.dtracker.ui.MainActivity;
import com.dtracker.ui.dialog.ConfirmationDialogFragment;

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

    void inject(ConfirmationDialogFragment confirmationDialogFragment);

    void inject(DistanceActivity distanceActivity);
}
