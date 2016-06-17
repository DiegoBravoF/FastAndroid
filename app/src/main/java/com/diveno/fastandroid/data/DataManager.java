package com.diveno.fastandroid.data;

import com.diveno.fastandroid.data.local.DatabaseHelper;
import com.diveno.fastandroid.data.local.PrefHelper;
import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.data.remote.Services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Diego on 15/06/2016.
 */
@Singleton
public class DataManager {
    private final Services services;
    private final PrefHelper prefHelper;
    private final DatabaseHelper databaseHelper;

    @Inject
    public DataManager(Services services, PrefHelper prefHelper, DatabaseHelper databaseHelper) {
        this.services = services;
        this.prefHelper = prefHelper;
        this.databaseHelper = databaseHelper;
    }

    public PrefHelper getPrefHelper() {
        return prefHelper;
    }

    public Observable<List<Repo>> getRepos() {
        return services.getRepos().distinct();
    }

    public Observable<Repo> syncRepos() {
        return services.getRepos()
                .concatMap(new Func1<List<Repo>, Observable<Repo>>() {
            @Override
            public Observable<Repo> call(List<Repo> repos) {
                return databaseHelper.setRepos(repos);
            }
        });
    }
}
