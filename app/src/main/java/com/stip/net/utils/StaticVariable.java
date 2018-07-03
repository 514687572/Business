package com.stip.net.utils;

import com.android.volley.RequestQueue;

public class StaticVariable {
    private static RequestQueue mRequestQueue;

    public static void clear() {
        HttpUtils.cancelAllRequest();
        mRequestQueue = null;
    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public static void setRequestQueue(RequestQueue paramRequestQueue) {
        mRequestQueue = paramRequestQueue;
    }
}
