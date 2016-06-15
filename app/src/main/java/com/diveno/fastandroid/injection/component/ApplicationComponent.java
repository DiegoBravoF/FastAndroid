package com.diveno.fastandroid.injection.component;

import android.app.Application;
import android.content.Context;

import com.diveno.fastandroid.data.DataManager;
import com.diveno.fastandroid.data.SyncService;
import com.diveno.fastandroid.injection.ApplicationContext;
import com.diveno.fastandroid.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Diego on 15/06/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();

    DataManager dataManager();

    Application app();
}
