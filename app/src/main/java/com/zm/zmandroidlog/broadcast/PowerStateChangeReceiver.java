/*
关机系统级别<manifes>里添加：
android:sharedUserId="android.uid.system"
<uses-permission android:name="android.permission.SHUTDOWN"/>

<receiver android:name=".PowerStateChangeReceiver" >
<intent-filter>
<action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
<action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" />
</intent-filter>
</receiver>
 */
package com.zm.zmandroidlog.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 监听充电断点状态、断点后关机
 * Created by 张明_ on 2017/8/14.
 * Email 741183142@qq.com
 */

public class PowerStateChangeReceiver extends BroadcastReceiver {

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        if ("android.intent.action.ACTION_POWER_CONNECTED".equals(intent
                .getAction())) {
        } else if ("android.intent.action.ACTION_POWER_DISCONNECTED"
                .equals(intent.getAction())) {
            new Thread(new shutdownThread()).start();
        }
    }

    /**
     * Shutdown关机
     *
     * @param context
     */
    public void shutdown(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
            intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    public class shutdownThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                shutdown(context);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



