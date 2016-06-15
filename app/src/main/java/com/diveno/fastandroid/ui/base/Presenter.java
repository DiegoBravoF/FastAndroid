package com.diveno.fastandroid.ui.base;

/**
 * Created by Diego on 15/06/2016.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
