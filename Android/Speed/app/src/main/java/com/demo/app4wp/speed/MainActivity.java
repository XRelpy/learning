package com.demo.app4wp.speed;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.demo.app4wp.speed.Service.ServiceCallBack;
import com.demo.app4wp.speed.Service.ServiceInterface;
import com.demo.app4wp.speed.Service.SpeedService;
import com.demo.app4wp.speed.Utils.LogCallBack;
import com.demo.app4wp.speed.Utils.LogExtends;
import com.demo.app4wp.speed.Utils.LogL;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ServiceCallBack, LogCallBack{
    private TextView txtSpeed = null;
    private Button btnStart = null;
    private TextView txtLog =null;
    private boolean startFlag = false;
    private ServiceInterface service;
    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFullScreen();
        checkPermissionFirst();
        init();
        initService();
        powerOn();
    }
    private void init() {
        txtSpeed = (TextView)findViewById(R.id.txt_speed);
        txtLog = (TextView)findViewById(R.id.txt_log);
        txtLog.setMovementMethod(ScrollingMovementMethod.getInstance());
        btnStart = (Button)findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startFlag) {
                    btnStart.setBackgroundColor(getResources().getColor(R.color.start));
                    btnStart.setText("START");
                    startFlag = false;
                    service.Stop();
                } else {
                    btnStart.setBackgroundColor(getResources().getColor(R.color.stop));
                    btnStart.setText("STOP");
                    startFlag = true;
                    checkPermission();
                    //gpsTest();
                    service.Start();
                }
            }
        });
        LogExtends.getInstance().init(this.getBaseContext(), this);
    }
    private void setFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initService() {
        Intent intent = new Intent(this, SpeedService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void powerOn() {
        powerManager = (PowerManager)this.getSystemService(this.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service =(ServiceInterface) iBinder;
            SpeedService.SpeedBinder binder = (SpeedService.SpeedBinder)iBinder;
            binder.getService();
            service.setCallBack(MainActivity.this, MainActivity.this.getBaseContext());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void updateData(Bundle bundle) {
        sendUpdateMessage(bundle);
    }

    private void sendUpdateMessage(Bundle bundle) {
        Message msg = new Message();
        msg.what = 1;
        msg.setData(bundle);
        handler.removeMessages(1);
        handler.sendMessage(msg);
    }

    public void updateLogData(String s){
        updateLogMessage(s);
    }

    private void updateLogMessage(String s) {
        Message msg = new Message();
        msg.what = 2;
        Bundle bundle = new Bundle();
        bundle.putString("LOG", s);
        msg.setData(bundle);
        handler.removeMessages(2);
        handler.sendMessage(msg);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    float speed = bundle.getFloat(ServiceCallBack.SEG_SPEED);
                    float distance = bundle.getFloat(ServiceCallBack.SEG_DISTANCE);
                    LogL.d("update data:" + speed + " -- " + distance);
                    txtSpeed.setText("速度:" + speed + "\n" + "距离:" + distance);
                    //updateLog("S:" + speed + "km D:" + distance + "km");
                    break;
                case 2:
                    Bundle bundleLog = msg.getData();
                    String s = bundleLog.getString("LOG");
                    updateLog(s);
            }
            super.handleMessage(msg);
        }
    };

    //StringBuffer buffer = new StringBuffer();
    private void updateLog(String value) {
        Date date = new Date(System.currentTimeMillis());
        DateFormat mediumFormat = new SimpleDateFormat("HH:mm:ss");
        String d = mediumFormat.format(date).toString();
        //buffer.append();
        //txtLog.setText(buffer.toString());
        if (txtLog.getLineCount() > 1000) {
            txtLog.setText("");
        }
        txtLog.append("\n"+"["+d + "]" + value);
        int offset=txtLog.getLineCount()*txtLog.getLineHeight();
        if(offset>txtLog.getHeight()){
            txtLog.scrollTo(0,offset-txtLog.getHeight());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //doNext(requestCode,grantResults);
    }

    private void checkPermission()  {
        //权限检测
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    10010);
        }
    }

    private void checkPermissionFirst() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    10011);
        }
    }

    private Location mLocation;
    private void gpsTest() {
        LocationManager locationm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        String provider = locationm.getBestProvider(criteria, true);
        List<String> list = locationm.getAllProviders();
        //for(Iterator<String> i = list.iterator(); i.hasNext();) {
        //    LogL.d("Provider:" + i.next());
        //}
        /*
        try {
            synchronized (this) {
                HandlerThread handlerThread = new HandlerThread("location");
                handlerThread.start();
                locationm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
                this.wait(5000);
            }
            //assert(mLocation != null);
            LogL.d(mLocation.toString());
        } catch (Exception ie) {
            LogL.d(ie.toString());
        } */
        //Location location = locationm.getLastKnownLocation(provider);
        //LogL.d(location.toString());
    }
    //位置监听
    private LocationListener locationListener=new LocationListener() {

        /**
         * 位置信息变化时触发
         */
        public void onLocationChanged(Location location) {
            //updateView(location);
            mLocation = location;
            LogL.d("时间："+location.getTime());
            LogL.d( "经度："+location.getLongitude());
            LogL.d( "纬度："+location.getLatitude());
            LogL.d( "海拔："+location.getAltitude());
        }

        /**
         * GPS状态变化时触发
         */
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                //GPS状态为可见时
                case LocationProvider.AVAILABLE:
                    LogL.d("当前GPS状态为可见状态");
                    break;
                //GPS状态为服务区外时
                case LocationProvider.OUT_OF_SERVICE:
                    LogL.d( "当前GPS状态为服务区外状态");
                    break;
                //GPS状态为暂停服务时
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    LogL.d("当前GPS状态为暂停服务状态");
                    break;
            }
        }

        /**
         * GPS开启时触发
         */
        public void onProviderEnabled(String provider) {
            //Location location=lm.getLastKnownLocation(provider);
            //updateView(location);
        }

        /**
         * GPS禁用时触发
         */
        public void onProviderDisabled(String provider) {
            //updateView(null);
        }
    };
}
