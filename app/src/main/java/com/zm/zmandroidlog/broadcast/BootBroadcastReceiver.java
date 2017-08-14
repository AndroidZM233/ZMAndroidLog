//<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
//
//<receiver android:name=".BootBroadcastReceiver">
//<intent-filter>
//<action android:name="android.intent.action.BOOT_COMPLETED" />
//<category android:name="android.intent.category.HOME" />
//</intent-filter>
//</receiver>
//
//自启动失败的原因:
//1、应用程序安装后重来没有启动过，这种情况下应用程序接收不到任何广播，包括BOOT_COMPLETED、
//ACTION_PACKAGE_ADDED、CONNECTIVITY_ACTION等等(Android3.1之后，系统为了加强了安全性控制)
//2、应用安装到了sd卡内，安装在sd卡内的应用是收不到BOOT_COMPLETED广播的
//3、系统开启了Fast Boot模式，这种模式下系统启动并不会发送BOOT_COMPLETED广播
//
//adb调试:
//adb shell am broadcast -a android.intent.action.BOOT_COMPLETED
//更精确的发送到某个package
//adb shell am broadcast -a android.intent.action.BOOT_COMPLETED -c android.intent.category.HOME -n package_name/class_name

package com.zm.zmandroidlog.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//开机自启
public class BootBroadcastReceiver extends BroadcastReceiver {
    static final String action_boot = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(action_boot)) {

        }

    }
}
