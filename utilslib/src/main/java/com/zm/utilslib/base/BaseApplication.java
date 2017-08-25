package com.zm.utilslib.base;

import android.app.Activity;
import android.app.Application;
import java.util.ArrayList;

/**
 * Created by 张明_ on 2017/8/23.
 * Email 741183142@qq.com
 */

public class BaseApplication extends Application {
    private ArrayList<Activity> activityList = new ArrayList<>();
    private static BaseToast toast;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    /**
     * Toast单例
     * @return Toast
     */
    public static BaseToast getToast() {
        if (toast == null) {
            toast = new BaseToast();
        }
        return toast;
    }

    /**
     * 添加到ArrayList<Activity>
     *
     * @param activity：Activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 退出所有的Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
