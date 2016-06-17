package com.diveno.fastandroid.ui.main;

import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.ui.base.MvpView;

import java.util.List;

/**
 * Created by Diego on 15/06/2016.
 */
public interface MainMvpView extends MvpView {
    void showRepos(List<Repo> repos);

    void showReposEmpty();

    void showError();
}
