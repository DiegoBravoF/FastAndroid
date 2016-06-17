package com.diveno.fastandroid.ui.base;

/**
 * Created by Diego on 15/06/2016.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    public T view;

    @Override
    public void attachView(T mvpView) {
        this.view = mvpView;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public T getMvpView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
