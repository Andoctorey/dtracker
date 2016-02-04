package com.dtracker;


import com.dtracker.core.dagger.PerApp;

import dagger.Component;

@PerApp
@Component(
        modules = {
                DTrackerModule.class
        }
)
public interface DTrackerAppComponent {

}
