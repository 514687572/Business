package com.yuguo.business.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuguo.business.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by C on 2016/8/26.
 */
public class VolleyErrorHelper {
    //用于返回具体错误信息，分辨错误类别
    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return "请求超时";
        }else if (isServerProblem(error)) {
            return handleServerError(error, context);
        }else if (isNetworkProblem(error)) {
            return "网络异常";
        }else if (isParseProblem(error)) {
            return "数据解析错误";
        }
        return "网络异常002";
    }

    //判断是否是网络错误
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) ||
                (error instanceof NoConnectionError);
    }

    //判断是否是服务端错误
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) ||
                (error instanceof AuthFailureError);
    }

    //判断是否是数据解析错误
    private static boolean isParseProblem(Object error) {
        return (error instanceof ParseError);
    }

    //处理服务端错误
    private static String handleServerError(Object err, Context context) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        if (response != null) {
            switch (response.statusCode) {
                case 404:
                case 422:
                case 401:
                    try {
                        //用gson解析返回参数
                        HashMap<String, String> result = new Gson().fromJson(new String(response.data),
                                new TypeToken<Map<String, String>>() {}.getType());

                        if (result != null && result.containsKey("error")) {
                            return result.get("error");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return error.getMessage();

                default:
                    return "网络异常001";
            }
        }
        return "网络异常001";
    }
}
