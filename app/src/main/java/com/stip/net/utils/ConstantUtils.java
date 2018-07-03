package com.stip.net.utils;


/**
 * 常量
 * 公共参数
 * @author 
 *
 */
public abstract class ConstantUtils {
    /**
     * 构造函数
     */
    private ConstantUtils() {
    }

    public static String SERVER_ADDRESS = "http://47.96.67.161/"; // 服务器地址
    public static String MCH_LOGIN_URL = "/admin/login"; // 登录地址
    public static boolean SUCCESS_STATUS = true; // 请求返回成功状态
    public static boolean FAILURE_STATUS= false; // 请求返回失败状态
    
    public static int DEPRECATED_GET_OR_POST = -1;
    public static int GET = 0;
    public static int POST = 1;
    public static int PUT = 2;
    public static int DELETE = 3;
}
