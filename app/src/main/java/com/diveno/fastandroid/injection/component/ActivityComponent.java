package com.diveno.fastandroid.injection.component;

import com.diveno.fastandroid.injection.PerActivity;
import com.diveno.fastandroid.injection.module.ActivityModule;
import com.diveno.fastandroid.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Diego on 15/06/2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
