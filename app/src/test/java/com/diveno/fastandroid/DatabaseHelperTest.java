package com.diveno.fastandroid;

import com.diveno.fastandroid.data.local.DatabaseHelper;
import com.diveno.fastandroid.data.local.DbOpenHelper;
import com.diveno.fastandroid.utils.DefaultConfig;
import com.diveno.fastandroid.utils.RxSchedulersOverrideRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by Diego on 15/06/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    private final DatabaseHelper databaseHelper = new DatabaseHelper(new DbOpenHelper(RuntimeEnvironment.application));

    @Rule
    public final RxSchedulersOverrideRule overrideRule = new RxSchedulersOverrideRule();

    @Test
    public void setRepos(){

    }

    @Test
    public void getRepos(){

    }
}
