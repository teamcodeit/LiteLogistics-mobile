package com.litelogistics.app.litelogistics;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Lekan Adigun on 7/21/2018.
 */

public class LitePayApplication extends Application {

    private static LitePayApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        activateFont();
    }

    public static LitePayApplication getApplication() {
        return application;
    }

    private void activateFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
