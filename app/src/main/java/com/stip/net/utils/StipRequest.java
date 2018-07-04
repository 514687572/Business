package com.stip.net.utils;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

public class StipRequest<T> extends Request<T>{
	public static final int GET = Method.GET;
    public static final int POST = Method.POST;
    private static Gson gson = new Gson();
    private Type mType;
    private final ResponeListener<T> mlistener;
    private Map<String, String> mParams;
    
    //自定义回调，方便使用
    public interface ResponeListener<T> extends ErrorListener, Listener<T> {
    }
    
    /**
     * 默认get方式提交http请求
     * @param url
     * @param type
     * @param params
     * @param listener
     */
    public StipRequest(String url, Type type, Map<String, String> params, ResponeListener<T> listener) {
        super(GET, getUrl(url, params), listener);
        this.mlistener = listener;
        this.mType = type;
        setCommonArgs(params);
        setShouldCache(false);
    }
    
    /**
     * 增加method参数选择get或post方式提交http请求
     * @param method
     * @param url
     * @param type
     * @param params
     * @param listener
     */
    public StipRequest(int method, String url, Type type, Map<String, String> params, ResponeListener<T> listener) {
        super(method,method==POST?url:getUrl(url, params), listener);
        this.mlistener = listener;
        this.mType = type;
        if(method==POST){
            mParams = params;
        }
        setCommonArgs(params);
        setShouldCache(false);
    }
    

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mParams != null) {
            return mParams;
        } else {
            return super.getParams();
        }
    }
    //构造url，get方式请求也可直接传参数
    private static String getUrl(String url, Map<String, String> params) {
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            url += sb.toString();
        }
        return url;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T result;
            String jsonStr = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
            result = gson.fromJson(jsonStr, mType);

            return Response.success(result,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mlistener.onResponse(response);
    }
    
    /**
     * 固定参数
     *
     * @return
     */
    public static Map<String, String> setCommonArgs(Map<String, String> params) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = df.format(date);

        return params;
    }

}
