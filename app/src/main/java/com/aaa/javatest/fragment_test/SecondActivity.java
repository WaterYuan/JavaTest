package com.aaa.javatest.fragment_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aaa.javatest.R;
import com.aaa.javatest.fragment_test.FirstActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void jumpA(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Util.setFragStr("A");
        startActivity(intent);
    }

    public void jumpB(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Util.setFragStr("B");
        startActivity(intent);
    }
}
