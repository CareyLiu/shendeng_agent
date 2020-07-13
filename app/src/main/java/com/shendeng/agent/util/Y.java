package com.shendeng.agent.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.shendeng.agent.app.App;

import java.text.DecimalFormat;

/**
 * 开发工具类
 */
public class Y {


    private static final boolean open = true;

    public static void i(String str) {
        if (open)
            Log.i("安卓开发", "----------------------------     " + str + "      -------------------------------");
    }

    public static void e(String bodyMsg) {
        if (open) {
            if (bodyMsg.length() > 4000) {
                for (int i = 0; i < bodyMsg.length(); i += 4000) {
                    if (i + 4000 < bodyMsg.length()) {
                        Log.e("安卓长开发", bodyMsg.substring(i, i + 4000));
                    } else {
                        //当前截取的长度已经超过了总长度，则打印出剩下的全部信息
                        Log.e("安卓长开发", bodyMsg.substring(i, bodyMsg.length()));
                    }
                }
            } else {
                //直接打印
                Log.e("安卓开发:", "----------------------------     " + bodyMsg + "      -------------------------------");
            }
        }
    }

    public static void t(String str) {
        Toast.makeText(App.getInstance().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    public static void tLong(String str) {
        Toast.makeText(App.getInstance().getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    public static Resources getResources() {
        return App.getInstance().getApplicationContext().getResources();
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


    public static int getInt(String content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                return Integer.parseInt(content);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static double getDouble(String content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                return Double.parseDouble(content);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static float getFloat(String content) {
        if (!TextUtils.isEmpty(content)) {
            try {
                return Float.parseFloat(content);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 获取Money
     */
    public static String getMoney(double money) {
        String format = new DecimalFormat("#.##").format(money);
        return format;
    }

    /**
     * 获取Money
     */
    public static String getMoney(float money) {
        String format = new DecimalFormat("#.##").format(money);
        return format;
    }
}
