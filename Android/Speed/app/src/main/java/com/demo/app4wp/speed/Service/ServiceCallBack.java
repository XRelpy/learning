package com.demo.app4wp.speed.Service;

import android.os.Bundle;

/**
 * Created by mark on 17-7-31.
 */

public interface ServiceCallBack {
    public static String SEG_SPEED = "speed";
    public static String SEG_DISTANCE = "distance";
    public void updateData(Bundle bundle);
}
