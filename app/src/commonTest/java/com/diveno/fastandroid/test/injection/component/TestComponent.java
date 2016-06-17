package com.diveno.fastandroid.test.injection.component;

import com.diveno.fastandroid.injection.component.ApplicationComponent;
import com.diveno.fastandroid.test.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Diego on 15/06/2016.
 */
@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}

