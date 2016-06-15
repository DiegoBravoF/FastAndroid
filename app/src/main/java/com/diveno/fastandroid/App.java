package com.diveno.fastandroid;

import android.app.Application;
import android.content.Context;

import com.diveno.fastandroid.injection.component.ApplicationComponent;
import com.diveno.fastandroid.injection.component.DaggerApplicationComponent;
import com.diveno.fastandroid.injection.module.ApplicationModule;

/**
 * Created by Diego on 15/06/2016.
 */
public class App extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
