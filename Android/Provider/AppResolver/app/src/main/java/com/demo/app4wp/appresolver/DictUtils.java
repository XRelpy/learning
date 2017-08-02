package com.demo.app4wp.appresolver;

import android.net.Uri;

/**
 * Created by mark on 17-6-8.
 */

public final class DictUtils {
    public static final String AUTHORITY = "com.xreply.app.provider";
    public static final String DICT_PRIVATE = "private";
    public static final String DICT_NUM = "num";
    public static final Uri DICT_CONTENT_PRIVATE = Uri.parse("content://" + AUTHORITY + "/private");
    public static final Uri DICT_CONTENT_NUM = Uri.parse("content://" + AUTHORITY + "/num");
}
