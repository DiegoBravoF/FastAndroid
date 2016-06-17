package com.diveno.fastandroid.test;

import android.content.Context;

import com.diveno.fastandroid.App;
import com.diveno.fastandroid.data.DataManager;
import com.diveno.fastandroid.test.injection.component.DaggerTestComponent;
import com.diveno.fastandroid.test.injection.component.TestComponent;
import com.diveno.fastandroid.test.injection.module.ApplicationTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Diego on 15/06/2016.
 */
public class TestComponentRule implements TestRule{
    private final TestComponent testComponent;
    private final Context context;

    public TestComponentRule(Context context) {
        this.context = context;
        App application = App.get(context);
        testComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
    }

    public Context getContext() {
        return context;
    }

    public DataManager getMockDataManager() {
        return testComponent.dataManager();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                App application = App.get(context);
                application.setComponent(testComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
