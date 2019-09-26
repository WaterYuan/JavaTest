package com.aaa.javatest.event_bus_test;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class BtManager {
    private static final String TAG = "BtManager";
    private static volatile BtManager sBtManager;

    private BtManager() {
        Log.i(TAG, "BtManager: ");
    }

    public static BtManager getBtManager() {
        if (sBtManager == null) {
            synchronized (BtManager.class) {
                if (sBtManager == null) {
                    sBtManager = new BtManager();
                }
            }
        }
        return sBtManager;
    }

    /**
     * 测试EventBus#post()同一个对象实例时的场景
     */
    public void sendMsg() {
        EventOne eventOne = new EventOne();
        Log.i(TAG, "sendMsg: -1 " + eventOne);
        EventBus.getDefault().post(eventOne.setMsg(-1));
        Log.i(TAG, "sendMsg: 0 " + EventOne.Holder.getInstance());
        EventBus.getDefault().post(EventOne.Holder.getInstance().setMsg(0));

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "sendMsg: run: ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "sendMsg: 1");
                EventBus.getDefault().post(EventOne.Holder.getInstance().setMsg(1));
                Log.i(TAG, "sendMsg: 12");
                EventBus.getDefault().post(EventOne.Holder.getInstance().setMsg(12));
            }
        }).start();
    }
}
