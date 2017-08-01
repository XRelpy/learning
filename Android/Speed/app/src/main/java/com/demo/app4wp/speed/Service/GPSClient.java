package com.demo.app4wp.speed.Service;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.demo.app4wp.speed.Utils.LogL;

/**
 * Created by mark on 17-7-31.
 */

public class GPSClient {
    private Context mcontext;
    private LocationManager locationm;
    private String provider;
    private double mLatitude;
    private double mLongitude;
    private double mspeed;
    private boolean gpsflag = false;
    public GPSClient(Context context) {
        mcontext = context;
        locationm = (LocationManager) mcontext.getSystemService(Context.LOCATION_SERVICE);
        gpsflag = registerGPS();
        LogL.d("init GPS");
    }

    public boolean getGPSStatus() {
        return  gpsflag;
    }

    public void retryStart() {
        gpsflag = registerGPS();
    }

    private boolean registerGPS() {
        LogL.d("Register GPS");
        //判断GPS是否正常启动
        if(!locationm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //Toast.makeText(mcontext, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
            //返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            //mcontext.startActivity(intent,1);
            mcontext.startActivity(intent);
            return false;
        }

        getGpsData();
        ///*
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        provider = locationm.getBestProvider(criteria, true);
        try {
            locationm.requestLocationUpdates(provider, 1000, 1, GPS_listener);
        } catch (Exception ex) {
            LogL.e("request location error:" + ex.toString());
            return false;
        }//*/
        return true;
    }
    // 获得自己位置

    private void gps_loc(Location location) {
        if (location != null) {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            mspeed = location.getSpeed();
            LogL.d("gps_loc1");
        } else {
            mLatitude = 0;
            mLongitude = 0;
            mspeed = 0;
            LogL.d("gps_loc2");
        }
        //LogL.d("Location:" + location.toString());
    }

    LocationListener GPS_listener = new LocationListener() {
        //监听位置变化，实时获取位置信息
        @Override
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            // TODO Auto-generated method stub
            LogL.d("GPS onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            LogL.d("GPS onProviderEnabled       ");
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub


        }

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            //位置发生改变时
            gps_loc(location);
        }

    };


    Handler mHandler;

    public void run(){
        Looper.prepare();
        mHandler = new Handler(){
            public void handleMessage(Message msg){
                Looper.myLooper().quit();
            }
        };
        try {
            //locationm.requestLocationUpdates(provider, 1000, 1, GPS_listener);
        } catch (Exception ex){
            LogL.e("Location Error:" + ex.toString());
            gpsflag = false;
        }
        mHandler.sendEmptyMessage(0); //send ourself a message so the looper can stop itself
        Looper.loop();
    }//end of run

    public Bundle getGpsData() {
        Bundle bundle = new Bundle();
        try {
            Location location = locationm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            gps_loc(location);
        } catch (Exception ex) {
            LogL.e("getGpsData Error:" + ex.toString());
        }
        LogL.d("GPS:" + mLatitude + " -- " + mLongitude);
        bundle.putDouble("Latitude", mLatitude);
        bundle.putDouble("Longitude", mLongitude);
        bundle.putDouble("Speed", mspeed);
        return bundle;
    }
}