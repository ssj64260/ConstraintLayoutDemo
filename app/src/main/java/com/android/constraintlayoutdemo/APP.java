package com.android.constraintlayoutdemo;

import android.app.Application;

public class APP extends Application {

    private static APP INSTANCE;

    public APP() {
        INSTANCE = this;
    }

    public static APP getInstance() {
        if (INSTANCE == null) {
            synchronized (APP.class) {
                if (INSTANCE == null) {
                    INSTANCE = new APP();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
    }
}
