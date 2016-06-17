package com.diveno.fastandroid.utils;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Diego on 15/06/2016.
 */
public class RxIdlingResource implements IdlingResource {

    private final AtomicInteger activeSubscriptionsCount = new AtomicInteger(0);
    private ResourceCallback resourceCallback;

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return activeSubscriptionsCount.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

    public void incrementActiveSubscriptionsCount() {
        int count = activeSubscriptionsCount.incrementAndGet();
//        Timber.i("Active subscriptions count increased to %d", count);
    }

    public void decrementActiveSubscriptionsCount() {
        int count = activeSubscriptionsCount.decrementAndGet();
//        Timber.i("Active subscriptions count decreased to %d", count);
        if (isIdleNow()) {
//            Timber.i("There is no active subscriptions, transitioning to Idle");
            resourceCallback.onTransitionToIdle();
        }
    }
}
