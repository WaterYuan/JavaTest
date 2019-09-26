package com.aaa.javatest.event_bus_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.aaa.javatest.R;
import com.aaa.javatest.json_test.Json_test;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {
    private static final String TAG = "EventBusActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        new Json_test().testJson();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventOne event) {
        Log.i(TAG, "onMessageEvent: " + event.getMsg() + " " + event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(EventOne event) {
        Log.i(TAG, "onMsgEvent: " + event.getMsg() + " " + event);
    }

    /*
2019-09-26 12:42:11.494 21123-21470/com.aaa.javatest I/EventBusActivity: run:
2019-09-26 12:42:13.496 21123-21470/com.aaa.javatest I/EventOne: EventOne:
2019-09-26 12:42:13.496 21123-21470/com.aaa.javatest I/BtManager: sendMsg: -1 com.aaa.javatest.event_bus_test.EventOne@1529cd4
2019-09-26 12:42:13.497 21123-21470/com.aaa.javatest I/EventOne: getInstance:
2019-09-26 12:42:13.497 21123-21470/com.aaa.javatest I/BtManager: sendMsg: 0 com.aaa.javatest.event_bus_test.EventOne@1393cae
2019-09-26 12:42:13.497 21123-21470/com.aaa.javatest I/EventOne: getInstance:
2019-09-26 12:42:13.497 21123-21123/com.aaa.javatest I/EventBusActivity: onMessageEvent: -1 com.aaa.javatest.event_bus_test.EventOne@1529cd4
2019-09-26 12:42:13.497 21123-21123/com.aaa.javatest I/EventBusActivity: onMsgEvent: -1 com.aaa.javatest.event_bus_test.EventOne@1529cd4
2019-09-26 12:42:13.497 21123-21123/com.aaa.javatest I/EventBusActivity: onMessageEvent: 0 com.aaa.javatest.event_bus_test.EventOne@1393cae
2019-09-26 12:42:13.497 21123-21123/com.aaa.javatest I/EventBusActivity: onMsgEvent: 0 com.aaa.javatest.event_bus_test.EventOne@1393cae
2019-09-26 12:42:13.498 21123-21471/com.aaa.javatest I/BtManager: sendMsg: run:
2019-09-26 12:42:15.500 21123-21471/com.aaa.javatest I/BtManager: sendMsg: 1
2019-09-26 12:42:15.500 21123-21471/com.aaa.javatest I/EventOne: getInstance:
2019-09-26 12:42:15.501 21123-21471/com.aaa.javatest I/BtManager: sendMsg: 12
2019-09-26 12:42:15.501 21123-21471/com.aaa.javatest I/EventOne: getInstance:
发送队列？？？
2019-09-26 12:42:15.501 21123-21123/com.aaa.javatest I/EventBusActivity: onMessageEvent: 12 com.aaa.javatest.event_bus_test.EventOne@1393cae
2019-09-26 12:42:15.501 21123-21123/com.aaa.javatest I/EventBusActivity: onMsgEvent: 12 com.aaa.javatest.event_bus_test.EventOne@1393cae
2019-09-26 12:42:15.502 21123-21123/com.aaa.javatest I/EventBusActivity: onMessageEvent: 12 com.aaa.javatest.event_bus_test.EventOne@1393cae
2019-09-26 12:42:15.502 21123-21123/com.aaa.javatest I/EventBusActivity: onMsgEvent: 12 com.aaa.javatest.event_bus_test.EventOne@1393cae
    * */

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BtManager.getBtManager().sendMsg();
            }
        }).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
