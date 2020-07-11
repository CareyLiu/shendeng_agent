package com.shendeng.agent.util;

public class Urls {
    //public static String SERVER_URL = "https://shop.hljsdkj.com/";//基本地址
    public static String SERVER_URL = "https://test.hljsdkj.com/";//基本地址

    public static String APP = SERVER_URL + "shop_new/app/";//APP端
    public static String WORKER = APP + "worker/";//卖家端

    public static String KEY = "20180305124455yu";//全局请求key


    public static String LOGIN = WORKER + "login";//卖家端登录接口
    public static String MSG = SERVER_URL + "msg";//发送短信验证接口

}
