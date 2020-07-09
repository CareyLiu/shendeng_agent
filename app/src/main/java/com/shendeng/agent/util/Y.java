package com.shendeng.agent.util;

import android.content.Context;
import android.content.res.Resources;

public class Y {//资源工具类

    private static Context mContent;

    public static void init(Context context) {
        Y.mContent = context;
    }

    public static Resources getResources() {
        return mContent.getResources();
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static int getDimen(int resId) {
        return (int) getResources().getDimension(resId);
    }
}
