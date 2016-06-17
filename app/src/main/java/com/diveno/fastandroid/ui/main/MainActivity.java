package com.diveno.fastandroid.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.diveno.fastandroid.R;
import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Diego on 15/06/2016.
 */
public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter presenter;

    @Inject
    ReposAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.attachView(this);
        presenter.loadRepos();
    }

    @Override
    public void showRepos(List<Repo> repos) {
        adapter.setRepos(repos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showReposEmpty() {

    }

    @Override
    public void showError() {

    }
}
