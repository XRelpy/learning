package com.demo.app4wp.speed.Utils;

import android.util.Log;

/**
 * Created by mark on 17-7-31.
 */

public  class LogL {
    public static String TAG = "Speed";
    public static void d(String value) {
        Log.d(TAG, "[debug]:" + value);
        LogExtends.getInstance().update("[DEUBG]", value);
    }

    public static void e(String value) {
        Log.d(TAG, "[Error]:" + value);
        LogExtends.getInstance().update("[Error]", value);
    }

    public static void w(String value) {
        Log.d(TAG, "[Warr]:" + value);
        LogExtends.getInstance().update("[Warr]", value);
    }
}
