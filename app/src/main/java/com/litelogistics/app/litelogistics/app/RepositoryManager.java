package com.litelogistics.app.litelogistics.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.litelogistics.app.litelogistics.LitePayApplication;

/**
 * Created by Lekan Adigun on 6/25/2018.
 */

public class RepositoryManager {

    /*
    * A simple class to easily maintain app prefs
    * */
    private static RepositoryManager manager; //
    private SharedPreferences mSharedPreferences;

    public static final String TOKEN_KEY = "token_key"; //Use to persist JWT token to pref

    private RepositoryManager() {
        Context context = LitePayApplication.getApplication().getApplicationContext();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /*
    * Returns singleton instance of this class
    * */
    public static synchronized RepositoryManager manager() {
        if (manager == null) manager = new RepositoryManager();

        return manager;
    }

    public SharedPreferences preferences() {
        return mSharedPreferences;
    }
}
