package com.diveno.fastandroid.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.diveno.fastandroid.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Diego on 15/06/2016.
 */
@Singleton
public class PrefHelper {
    public static final String PREF_FILE = "Name";

    private final SharedPreferences pref;

    @Inject
    public PrefHelper(@ApplicationContext Context context) {
        pref = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    }

    public void clear() {
        pref.edit().clear().apply();
    }
}
