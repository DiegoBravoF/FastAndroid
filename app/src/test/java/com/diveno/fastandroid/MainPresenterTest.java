package com.diveno.fastandroid;

import com.diveno.fastandroid.data.DataManager;
import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.test.TestDataFactory;
import com.diveno.fastandroid.ui.main.MainMvpView;
import com.diveno.fastandroid.ui.main.MainPresenter;
import com.diveno.fastandroid.utils.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Diego on 15/06/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    DataManager mockDataManager;
    private MainPresenter presenter;
    @Mock
    private MainMvpView mockView;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        presenter = new MainPresenter(mockDataManager);
        presenter.attachView(mockView);
    }


    @After
    public void tearDown(){
        presenter.detachView();
    }

    @Test
    public void loadRepos() {
        List<Repo> repos = TestDataFactory.makeListRepos(10);
        when(mockDataManager.getRepos()).thenReturn(Observable.just(repos));
        presenter.loadRepos();
        verify(mockView).showRepos(repos);
        verify(mockView, never()).showError();
        verify(mockView, never()).showReposEmpty();
    }

    @Test
    public void loadReposReturnsEmpty() {
        when(mockDataManager.getRepos()).thenReturn(Observable.just(Collections.<Repo>emptyList()));
        presenter.loadRepos();
        verify(mockView).showReposEmpty();
        verify(mockView, never()).showError();
        verify(mockView, never()).showRepos(anyListOf(Repo.class));

    }

    @Test
    public void loadReposFail() {
        when(mockDataManager.getRepos()).thenReturn(Observable.<List<Repo>>error(new RuntimeException()));
        presenter.loadRepos();
        verify(mockView).showError();
        verify(mockView, never()).showReposEmpty();
        verify(mockView, never()).showRepos(anyListOf(Repo.class));
    }
}
