package com.diveno.fastandroid.runner;

import android.os.Bundle;
import android.support.test.espresso.Espresso;

import com.diveno.fastandroid.utils.RxIdlingExecutionHook;
import com.diveno.fastandroid.utils.RxIdlingResource;

import rx.plugins.RxJavaPlugins;

/**
 * Created by Diego on 15/06/2016.
 */
public class RxAndroidJUnitRunner extends UnlockDeviceAndroidJUnitRunner {

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        RxJavaPlugins.getInstance()
                .registerObservableExecutionHook(new RxIdlingExecutionHook(rxIdlingResource));
        Espresso.registerIdlingResources(rxIdlingResource);
    }
}
