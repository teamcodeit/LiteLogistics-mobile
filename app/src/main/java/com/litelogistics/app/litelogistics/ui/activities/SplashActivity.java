package com.litelogistics.app.litelogistics.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.litelogistics.app.litelogistics.R;
import com.litelogistics.app.litelogistics.ui.base.BaseActivity;
import com.litelogistics.app.litelogistics.ui.fragments.IntroFragment;

/**
 * Created by Lekan Adigun on 7/17/2018.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_splash);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_splash_activity, IntroFragment.newInstance())
                    .commit();
        }
    }
}
