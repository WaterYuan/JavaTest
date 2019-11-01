package com.aaa.javatest.lru;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aaa.javatest.R;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LruFragment extends Fragment {
    private static final String TAG = "LruFragment";


    private LruFragment() {
        Log.i(TAG, "LruFragment: ");

        initLru();

        initLinkedHashMap();
    }

    private void initLinkedHashMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(0, 0.75f, true);

        for (int i = 0; i < 5; i++) {
            linkedHashMap.put("k" + i, "v" + i);
        }

        linkedHashMap.put("k3", "v3");

        {
            Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                Log.i(TAG, "initLinkedHashMap: " + entry.getKey() + ":" + entry.getValue());
            }
        }

        /*
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k0:v0
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k1:v1
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k2:v2
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k4:v4
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k3:v3 //
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: ---
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k1
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k2
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k4
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k3
2019-11-01 14:24:56.068 17245-17245/com.aaa.javatest I/LruFragment: initLinkedHashMap: k0 //
         * */
        Log.i(TAG, "initLinkedHashMap: ---");
        linkedHashMap.get("k0");

        {
            Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                Log.i(TAG, "initLinkedHashMap: " + entry.getKey());
            }
        }


    }

    private void initLru() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        LruCache<String, String> mLruCache = new LruCache((int) (maxMemory / 8));

        for (int i = 0; i < 5; i++) {
            mLruCache.put("k" + i, "v" + i);
        }


        Log.i(TAG, "initLru: " + mLruCache.size());
        {
            Set<Map.Entry<String, String>> entries = mLruCache.snapshot().entrySet();
            for (Map.Entry<String, String> entry : entries) {
                Log.i(TAG, "initLru: " + entry.getKey());
            }
        }
        mLruCache.get("k2");
        Log.i(TAG, "initLru: ---");
        {
            Set<Map.Entry<String, String>> entries = mLruCache.snapshot().entrySet();
            for (Map.Entry<String, String> entry : entries) {
                Log.i(TAG, "initLru: " + entry.getKey());
            }
        }
        /**
         2019-11-01 13:41:55.691 15510-15510/com.aaa.javatest I/LruFragment: initLru: k0
         2019-11-01 13:41:55.691 15510-15510/com.aaa.javatest I/LruFragment: initLru: k1
         2019-11-01 13:41:55.691 15510-15510/com.aaa.javatest I/LruFragment: initLru: k2
         2019-11-01 13:41:55.691 15510-15510/com.aaa.javatest I/LruFragment: initLru: k3
         2019-11-01 13:41:55.691 15510-15510/com.aaa.javatest I/LruFragment: initLru: k4
         2019-11-01 13:41:55.692 15510-15510/com.aaa.javatest I/LruFragment: initLru: ---
         2019-11-01 13:41:55.692 15510-15510/com.aaa.javatest I/LruFragment: initLru: k0
         2019-11-01 13:41:55.692 15510-15510/com.aaa.javatest I/LruFragment: initLru: k1
         2019-11-01 13:41:55.692 15510-15510/com.aaa.javatest I/LruFragment: initLru: k3
         2019-11-01 13:41:55.692 15510-15510/com.aaa.javatest I/LruFragment: initLru: k4
         2019-11-01 13:41:55.692 15510-15510/com.aaa.javatest I/LruFragment: initLru: k2
         * */
        Log.i(TAG, "initLru: " + mLruCache.size());
    }

    public void getLast(View view) {
    }

    public static LruFragment getInstance() {
        Log.i(TAG, "getInstance: ");
        return new LruFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_lru, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: isVisible=" + isVisible());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged: hidden=" + hidden + " isVisible=" + isVisible());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: isVisible=" + isVisible());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }
}
