package com.yuguo.business.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 获得屏幕相关的辅助类
 * @author Administrator
 *
 */
public class ScreenUtils {
	private ScreenUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	/**
	 * 获得屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
	/**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}
}
