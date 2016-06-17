package com.diveno.fastandroid.utils;

import rx.Observable;
import rx.Subscription;
import rx.plugins.RxJavaObservableExecutionHook;

/**
 * Created by Diego on 15/06/2016.
 */
public class RxIdlingExecutionHook extends RxJavaObservableExecutionHook {

    private RxIdlingResource rxIdlingResource;

    public RxIdlingExecutionHook(RxIdlingResource rxIdlingResource) {
        this.rxIdlingResource = rxIdlingResource;
    }

    @Override
    public <T> Observable.OnSubscribe<T> onSubscribeStart(
            Observable<? extends T> observableInstance, Observable.OnSubscribe<T> onSubscribe) {
        rxIdlingResource.incrementActiveSubscriptionsCount();
        return super.onSubscribeStart(observableInstance, onSubscribe);
    }

    @Override
    public <T> Throwable onSubscribeError(Throwable e) {
        rxIdlingResource.decrementActiveSubscriptionsCount();
        return super.onSubscribeError(e);
    }

    @Override
    public <T> Subscription onSubscribeReturn(Subscription subscription) {
        rxIdlingResource.decrementActiveSubscriptionsCount();
        return super.onSubscribeReturn(subscription);
    }
}
