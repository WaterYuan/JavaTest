package com.aaa.javatest;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }

    ActivityLifecycleCallbacks mActivityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.i(TAG, "onActivityCreated: ");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.i(TAG, "onActivityStarted: ");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.i(TAG, "onActivityResumed: ");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.i(TAG, "onActivityPaused: ");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.i(TAG, "onActivityStopped: ");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.i(TAG, "onActivitySaveInstanceState: ");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.i(TAG, "onActivityDestroyed: ");
        }
    };
}
