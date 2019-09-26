package com.aaa.javatest.event_bus_test;

import android.util.Log;

public class EventOne {
    private static final String TAG = "EventOne";

    static class Holder {
        static EventOne sBean = new EventOne();

        public static EventOne getInstance() {
            Log.i(TAG, "getInstance: ");
            return sBean;
        }
    }

    private int msg = 0;

    public EventOne() {
        Log.i(TAG, "EventOne: ");
    }

    public EventOne setMsg(int msg) {
        this.msg = msg;
        return this;
    }

    public int getMsg() {
        return msg;
    }
}
