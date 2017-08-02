package com.demo.app4wp.appprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by mark on 17-6-8.
 */

public class DictProvider extends ContentProvider {
    private static final String TAG = "DictProvider";
    private HashMap<String, Boolean>  privateList = new HashMap<String, Boolean>();
    private HashMap<String, Integer>  pkgValue = new HashMap<String, Integer>();

    private static final int PRIVATE = 1;
    private static final int NUM = 2;

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(DictUtils.AUTHORITY, DictUtils.DICT_NUM, NUM);
        matcher.addURI(DictUtils.AUTHORITY, DictUtils.DICT_PRIVATE, PRIVATE);
    }

    @Override
    public  boolean onCreate(){
        Log.d(TAG, "Create Provider");
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            case NUM:
                Log.d(TAG,"Value1:" + uri + " -- " + values);
                break;
            case PRIVATE:
                Log.d(TAG,"Value2:" + uri + " -- " + values);
                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] args) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] args) {

        return 0;
    }

    @Override
    public Cursor query(Uri uri, String[] project, String selection, String[] args, String sortOrder) {
        return null;
    }

    @Override
    public  String getType(Uri uri) {
        String res = null;
        switch (matcher.match(uri)) {
            case NUM:
                return "vnd.android.cursor.item/xreply.num";
            case PRIVATE:
                return "vnd.android.cursor.item/xreply.private";
        }
        return res;
    }

}
