package com.demo.app4wp.speed.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import com.demo.app4wp.speed.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SpeedService extends Service {
    private Binder binder = null;
    private ServiceCallBack callback;
    private boolean startFlag = false;
    private Timer timer;
    private Context mcontext;
    private GPSClient gpsClient;
    private float distance = 0f;
    private double speed = 0L;
    private double lastLatitude = -1f;
    private double lastLongitude = -1f;
    public SpeedService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        if (binder == null) {
            binder = new SpeedBinder();
        }
        return  binder;
    }

    public void setCallBack(ServiceCallBack callback, Context context) {
        this.callback = callback;
        mcontext = context;
        gpsClient = new GPSClient(mcontext);
        timer =new Timer(true);
        timer.schedule(task, 500,1000);
    }

    public void Start() {
        startFlag = true;
        updateData(0f, 0f);
    }
    public void Stop() {
        startFlag = false;
    }

    private void updateData(float speed, float distance) {
        if (callback != null) {
            Bundle bundle = new Bundle();
            bundle.putFloat(ServiceCallBack.SEG_SPEED, speed);
            bundle.putFloat(ServiceCallBack.SEG_DISTANCE, distance);
            callback.updateData(bundle);
        }
    }

    public class SpeedBinder extends Binder implements ServiceInterface{
        public SpeedService getService() {
            return SpeedService.this;
        }

        public void setCallBack(ServiceCallBack callback, Context context) {
            SpeedService.this.setCallBack(callback, context);
        }

        public void Start() {
            SpeedService.this.Start();
        }

        public void Stop() {
            SpeedService.this.Stop();
        }
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (startFlag) {
                if (gpsClient.getGPSStatus()) {
                    Bundle bundle =  gpsClient.getGpsData();
                    double clatitude = bundle.getDouble("Latitude");
                    double clongitude = bundle.getDouble("Longitude");
                    addDistance(clatitude, clongitude);
                    speed = bundle.getDouble("Speed");

                    updateData((float)Math.round((float)speed * 100)/100, (float)Math.round(distance));
                } else {
                    gpsClient.retryStart();
                }
            }
        }
    };

    private void addDistance(double clatitude, double clongtitude) {
        if (lastLatitude != -1f && lastLongitude != -1f) {
            float[] results  = new float[1];
            Location.distanceBetween(lastLatitude, lastLongitude, clatitude, clongtitude, results);
            distance += results[0];
            lastLatitude = clatitude;
            lastLongitude = clongtitude;
        } else {
            lastLatitude =clatitude;
            lastLongitude = clongtitude;
        }
    }
}
