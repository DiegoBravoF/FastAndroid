package com.diveno.fastandroid;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.diveno.fastandroid.data.model.Repo;
import com.diveno.fastandroid.test.TestComponentRule;
import com.diveno.fastandroid.test.TestDataFactory;
import com.diveno.fastandroid.ui.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

/**
 * Created by Diego on 15/06/2016.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public final TestComponentRule component = new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<MainActivity>(MainActivity.class, false, false) {
                @Override
                protected Intent getActivityIntent() {
                    // Override the default intent so we pass a false flag for syncing so it doesn't
                    // start a sync service in the background that would affect  the behaviour of
                    // this test.
                    return MainActivity.getStartIntent(
                            InstrumentationRegistry.getTargetContext(), false);
                }
            };
    @Rule
    public final TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void listOfReposShows() {
        List<Repo> testDataRepos = TestDataFactory.makeListRepos(20);
        when(component.getMockDataManager().getRepos())
                .thenReturn(Observable.just(testDataRepos));

        main.launchActivity(null);

        int position = 0;
        for (Repo repo : testDataRepos) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.scrollToPosition(position));
            String name = repo.getName();
            onView(withText(name))
                    .check(matches(isDisplayed()));
            position++;
        }
    }


}

