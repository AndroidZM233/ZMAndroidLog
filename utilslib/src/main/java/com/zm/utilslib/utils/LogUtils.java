package com.zm.utilslib.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

/**
 * log工具类
 * Created by 张明_ on 2017/8/25.
 * Email 741183142@qq.com
 */

public class LogUtils {
    public static String tagPrefix = "";
    public static boolean showV = true;
    public static boolean showD = true;
    public static boolean showI = true;
    public static boolean showW = true;
    public static boolean showE = true;
    public static boolean showWTF = true;

    /**
     * 得到tag（所在类.方法（L:行））
     *
     * @return String
     */
    @SuppressLint("DefaultLocale")
    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String callerClazzName = stackTraceElement.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String tag = "%s.%s(L:%d)";
        Object[] args = {callerClazzName, stackTraceElement.getMethodName(), stackTraceElement.getLineNumber()};
        tag = String.format(tag, args);
        //给tag设置前缀
        tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
        return tag;
    }

    public static void v(String msg) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg);
        }
    }

    public static void v(String msg, Throwable tr) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String msg) {
        if (showD) {
            String tag = generateTag();
            Log.d(tag, msg);
        }
    }

    public static void d(String msg, Throwable tr) {
        if (showD) {
            String tag = generateTag();
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String msg) {
        if (showI) {
            String tag = generateTag();
            Log.i(tag, msg);
        }
    }

    public static void i(String msg, Throwable tr) {
        if (showI) {
            String tag = generateTag();
            Log.i(tag, msg, tr);
        }
    }

    public static void w(String msg) {
        if (showW) {
            String tag = generateTag();
            Log.w(tag, msg);
        }
    }

    public static void w(String msg, Throwable tr) {
        if (showW) {
            String tag = generateTag();
            Log.w(tag, msg, tr);
        }
    }

    public static void e(String msg) {
        if (showE) {
            String tag = generateTag();
            Log.e(tag, msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (showE) {
            String tag = generateTag();
            Log.e(tag, msg, tr);
        }
    }

    public static void wtf(String msg) {
        if (showWTF) {
            String tag = generateTag();
            Log.wtf(tag, msg);
        }
    }

    public static void wtf(String msg, Throwable tr) {
        if (showWTF) {
            String tag = generateTag();
            Log.wtf(tag, msg, tr);
        }
    }
}
