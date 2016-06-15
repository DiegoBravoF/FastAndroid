package com.diveno.fastandroid.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.diveno.fastandroid.App;
import com.diveno.fastandroid.injection.component.ActivityComponent;
import com.diveno.fastandroid.injection.component.DaggerActivityComponent;
import com.diveno.fastandroid.injection.module.ActivityModule;

/**
 * Created by Diego on 15/06/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(App.get(this).getApplicationComponent())
                    .build();

        }
        return activityComponent;
    }
}
