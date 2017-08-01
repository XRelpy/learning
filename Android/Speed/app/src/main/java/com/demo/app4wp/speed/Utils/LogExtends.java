package com.demo.app4wp.speed.Utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mark on 17-8-1.
 */

public class LogExtends {
    private static LogExtends logExtends;
    private LogCallBack callback;
    private Context mcontext;
    private File file;
    private PrintWriter print;
    private LogExtends() {

    }

    public static  LogExtends getInstance() {
        if (logExtends == null) {
            logExtends = new LogExtends();
        }
        return logExtends;
    }

    public void init(Context context, LogCallBack c) {
        mcontext = context;
        callback = c;
        File path = mcontext.getFilesDir();
        file = new File(path, "Speed_log_"+System.currentTimeMillis() + ".log");
        try {
            file.createNewFile();
            print = new PrintWriter(file);
        } catch (Exception ex) {
            LogL.e("Create file error:" + ex.toString());
        }
    }

    public void update(String tag, String value) {
        if (callback != null) {
            callback.updateLogData(value);
        }
        // write to log file
        if (mcontext != null) {
            Date date = new Date(System.currentTimeMillis());
            DateFormat mediumFormat = new SimpleDateFormat("HH:mm:ss");
            String d = mediumFormat.format(date).toString();
            print.append(d + " " + tag + " " + value + "\n");
            print.flush();
        }
    }
}
