package com.diveno.fastandroid.ui.main;

import com.diveno.fastandroid.data.DataManager;
import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Diego on 15/06/2016.
 */
public class MainPresenter extends BasePresenter<MainMvpView> {
    private final DataManager dataManager;
    private Subscription subscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadRepos() {
        checkViewAttached();
        subscription = dataManager.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        if (repos.isEmpty()) {
                            getMvpView().showReposEmpty();
                        } else {
                            getMvpView().showRepos(repos);
                        }
                    }
                });
    }
}
