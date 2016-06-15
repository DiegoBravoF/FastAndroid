package com.diveno.fastandroid.injection.module;

import android.app.Application;
import android.content.Context;

import com.diveno.fastandroid.data.remote.Services;
import com.diveno.fastandroid.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diego on 15/06/2016.
 */
@Module
public class ApplicationModule {
    protected final Application app;

    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    Application provideApplication() {
        return app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return app;
    }


//    @Provides
//    @Singleton
//    Bus provideEventBus() {
//        return new Bus();
//    }

    @Provides
    @Singleton
    Services provideReposService() {
        return Services.Creator.newService();
    }
}
