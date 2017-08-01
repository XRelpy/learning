package com.demo.app4wp.speed.Service;

import android.content.Context;

/**
 * Created by mark on 17-7-31.
 */

public interface ServiceInterface {
    public void setCallBack(ServiceCallBack callback, Context context);
    public void Start();
    public void Stop();
}
