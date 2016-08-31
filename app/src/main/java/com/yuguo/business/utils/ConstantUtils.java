package com.yuguo.business.utils;


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

    public static String SERVER_ADDRESS = "http://192.168.0.188:8080/yuguoSAS"; // 服务器地址
    public static String MCH_LOGIN_URL = "/yg/commercial/login.do"; // 商家登录
    //rsa私钥
    public static String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL26761dzS+8jV/fTrbU9RQSPHD+1ir1ARDDfxBhl2n6S34LNiGZi7v9lmC3p3mgNu58IU4dDlTssuTy2WzBqhCCR03BLRpv1/FAN223UoUsDT8Uj30mGeNQebrISc2H0HdDaRl+kJU7JSddouKZNs7jTqqOU1B2nURLCAmOacDhAgMBAAECgYBZURtrvfDOPDFBpNplb7NAE6WofA9OgbBSt/WlPIwPM+k+GQEOqD4D7clRFqQcubOiaYU0RLFugU9mQHzimfj4o5n/wOlpgmiFKnv2wR07tgIeKahczQs+dMt2dJoFArOChDlKUedntR4HDd5PsFN2P+e75RyY1vec5Uuxs7WQUQJBAPiWiIjpjAOTQTvIZ+cj10I96rkrySg/BPU1/Dsgz6N9kUavqgNK/WRHOeBnMp0SyZR3qtvDZaBBC3I0ZDUdQGsCQQDDYySE9T1F6ouMihiXGCtQa5+JK/SaLs/ikTe67RoylU2LYTGFafTvWOXbL0tcqpZ0yybBhvCMvU5Y1qQxLWbjAkALpwK/aDMn978XDk3Qh92PPVfDVkoXmNyioeUw4FjbUQd+SL2vadR23t/XpP66XicUzoczxfT9jg77S3lnA/gBAkAIXZhUsQNcmPw8mjUPUxOXr+P2xZEFgPeZpcoTEM/MVqQpUZIDOlQoY1AEIHRDlXI3sfMI2jQ2cmyGVTpWeLEtAkEAtSM2cB2TZrWBSz4UebT1XJfcQC9fCY0bxN9IuXI4bjcG4OCwepRZjNkUJTbCBmfjXAXiEGyr41JbpQHGD66sWg==";
    public static boolean SUCCESS_STATUS = true; // 请求返回成功状态
    public static boolean FAILURE_STATUS= false; // 请求返回失败状态
    
    public static int DEPRECATED_GET_OR_POST = -1;
    public static int GET = 0;
    public static int POST = 1;
    public static int PUT = 2;
    public static int DELETE = 3;
}
