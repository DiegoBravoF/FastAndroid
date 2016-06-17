package com.diveno.fastandroid.test.injection.module;

import android.app.Application;
import android.content.Context;

import com.diveno.fastandroid.data.DataManager;
import com.diveno.fastandroid.data.remote.Services;
import com.diveno.fastandroid.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Diego on 15/06/2016.
 */
@Module
public class ApplicationTestModule {

    private final Application app;

    public ApplicationTestModule(Application app) {
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


    /************* MOCKS *************/

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return mock(DataManager.class);
    }

    @Provides
    @Singleton
    Services provideServices() {
        return mock(Services.class);
    }
}
