package com.diveno.fastandroid;

import com.diveno.fastandroid.data.DataManager;
import com.diveno.fastandroid.data.local.DatabaseHelper;
import com.diveno.fastandroid.data.local.PrefHelper;
import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.data.remote.Services;
import com.diveno.fastandroid.test.TestDataFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Diego on 15/06/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    private DataManager dataManager;
    @Mock
    PrefHelper mockPrefHelper;
    @Mock
    Services mockServices;
    @Mock
    DatabaseHelper mockDatabaseHelper;

    @Before
    public void setUp() {
        dataManager = new DataManager(mockServices, mockPrefHelper, mockDatabaseHelper);
    }

    @Test
    public void syncReposEmitsValues() {
        List<Repo> ribots = Arrays.asList(TestDataFactory.makeRepo("1"),
                TestDataFactory.makeRepo("2"));
        stubSyncRepoHelperCalls(ribots);

        TestSubscriber<Repo> result = new TestSubscriber<>();
        dataManager.syncRepos().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ribots);
    }

    @Test
    public void syncReposCallsApiAndDatabase() {
        List<Repo> repos = Arrays.asList(TestDataFactory.makeRepo("1"),
                TestDataFactory.makeRepo("2"));
        stubSyncRepoHelperCalls(repos);

        dataManager.syncRepos().subscribe();
        // Verify right calls to helper methods
        verify(mockServices).getRepos();
        verify(mockDatabaseHelper).setRepos(repos);
    }

    private void stubSyncRepoHelperCalls(List<Repo> repos) {
        // Stub calls to the ribot service and database helper.
        when(mockServices.getRepos())
                .thenReturn(Observable.just(repos));
        when(mockDatabaseHelper.setRepos(repos))
                .thenReturn(Observable.from(repos));
    }
}
