package com.aaa.javatest.fragment_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aaa.javatest.R;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";
    private FragmentA mFragmentA;
    private FragmentB mFragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.i(TAG, "onCreate: " + getIntent());

        mFragmentA = new FragmentA();
        mFragmentB = new FragmentB();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, mFragmentA, "A")
                .show(mFragmentA)
                .add(R.id.fl_content, mFragmentB, "B")
                .hide(mFragmentB)
                .commit();
    }

    /*
2019-09-28 13:47:35.145 31597-31597/com.aaa.javatest I/FirstActivity: onStop:

2019-09-28 13:47:35.430 31597-31597/com.aaa.javatest W/FirstActivity: onNewIntent:
2019-09-28 13:47:35.430 31597-31597/com.aaa.javatest I/FirstActivity: onRestart:
2019-09-28 13:47:35.431 31597-31597/com.aaa.javatest I/FragA: onStart:
2019-09-28 13:47:35.431 31597-31597/com.aaa.javatest I/FragB: onStart:
2019-09-28 13:47:35.431 31597-31597/com.aaa.javatest I/FirstActivity: onStart:
2019-09-28 13:47:35.431 31597-31597/com.aaa.javatest I/FirstActivity: onResume:
2019-09-28 13:47:35.431 31597-31597/com.aaa.javatest I/FragA: onResume:
2019-09-28 13:47:35.431 31597-31597/com.aaa.javatest I/FragB: onResume:

2019-09-28 13:47:37.008 31597-31597/com.aaa.javatest I/FragA: onPause:
2019-09-28 13:47:37.008 31597-31597/com.aaa.javatest I/FragB: onPause:
2019-09-28 13:47:37.008 31597-31597/com.aaa.javatest I/FirstActivity: onPause:
2019-09-28 13:47:37.403 31597-31597/com.aaa.javatest I/FragA: onStop:
2019-09-28 13:47:37.403 31597-31597/com.aaa.javatest I/FragB: onStop:
2019-09-28 13:47:37.403 31597-31597/com.aaa.javatest I/FirstActivity: onStop:

2019-09-28 13:47:37.798 31597-31597/com.aaa.javatest W/FirstActivity: onNewIntent:
2019-09-28 13:47:37.799 31597-31597/com.aaa.javatest I/FirstActivity: onRestart:
2019-09-28 13:47:37.800 31597-31597/com.aaa.javatest I/FragA: onStart:
2019-09-28 13:47:37.800 31597-31597/com.aaa.javatest I/FragB: onStart:
2019-09-28 13:47:37.800 31597-31597/com.aaa.javatest I/FirstActivity: onStart:
2019-09-28 13:47:37.801 31597-31597/com.aaa.javatest I/FirstActivity: onResume:
2019-09-28 13:47:37.801 31597-31597/com.aaa.javatest I/FragA: onResume:
2019-09-28 13:47:37.801 31597-31597/com.aaa.javatest I/FragB: onResume:
    * */

    public void jump2(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /*
W/FirstActivity: onNewIntent:
I/FirstActivity: onRestart: B
I/FragA: onHiddenChanged: hidden=true isVisible=false
I/FragB: onHiddenChanged: hidden=false isVisible=true
I/FragA: onStart: isVisible=false
I/FragB: onStart: isVisible=true
I/FirstActivity: onStart:
I/FirstActivity: onResume:
I/FragA: onResume: isVisible=false
I/FragB: onResume: isVisible=true
I/FirstActivity: onWindowFocusChanged: hasFocus=true
     * */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i(TAG, "onWindowFocusChanged: hasFocus=" + hasFocus);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("\n");
        Log.w(TAG, "onNewIntent: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: " + Util.getFragStr());

        getSupportFragmentManager().beginTransaction()
                .hide("A".equals(Util.getFragStr()) ? mFragmentB : mFragmentA)
                .show("A".equals(Util.getFragStr()) ? mFragmentA : mFragmentB)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
