package com.stip.net.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
/**
 * 请求工具类
 * @author C
 *
 */
public class HttpUtils {
	/**
	 * 添加Request请求到队列中，因为我们使用OkHttp作为Volley的传输层，所以增加一个HttpStack参数
	 * 
	 * @param request
	 */
	public static void addRequest(Request<?> request) {
		if (request != null) {
			if (StaticVariable.getRequestQueue() == null) {
				StaticVariable.setRequestQueue(Volley.newRequestQueue(ContextUtil.getInstance()));
			}
			Volley.newRequestQueue(ContextUtil.getInstance(),new OkHttpStack());
			StaticVariable.getRequestQueue().add(request);
		}
	}

	/**
	 * 取消所有Request
	 */
	public static void cancelAllRequest() {
		RequestQueue localRequestQueue = StaticVariable.getRequestQueue();
		if (localRequestQueue != null) {
			localRequestQueue.cancelAll(new RequestQueue.RequestFilter() {

				@Override
				public boolean apply(Request<?> request) {
					return true;
				}
			});
			localRequestQueue.stop();
		}
	}

	/**
	 * 根据标签取消指定Request
	 * 
	 * @param tag
	 */
	public static void cancelRequestByTag(String tag) {
		if (!TextUtils.isEmpty(tag)) {
			if (StaticVariable.getRequestQueue() != null) {
				StaticVariable.getRequestQueue().cancelAll(tag);
			}
		}
	}

	/**
	 * 定义OkHttpStack
	 * 
	 * @author C
	 * @param
	 * 
	 */
	private static class OkHttpStack extends HurlStack {
		private final OkUrlFactory okUrlFactory;

		public OkHttpStack() {
			this(new OkUrlFactory(new OkHttpClient()));
		}

		public OkHttpStack(OkUrlFactory okUrlFactory) {
			if (okUrlFactory == null) {
				throw new NullPointerException("Client must not be null.");
			}
			this.okUrlFactory = okUrlFactory;
		}

		@Override
		protected HttpURLConnection createConnection(URL url)throws IOException {
			return okUrlFactory.open(url);
		}
	}

}
