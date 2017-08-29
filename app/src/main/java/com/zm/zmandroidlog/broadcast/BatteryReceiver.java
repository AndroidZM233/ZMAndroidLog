/*
<receiver android:name=".BatteryReceiver">
   <intent-filter>
        <action android:name="android.intent.action.BATTERY_CHANGED"/>
    </intent-filter>
</receiver>
 */
package com.zm.zmandroidlog.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 电池电量广播监听
 * Created by 张明_ on 2017/8/28.
 * Email 741183142@qq.com
 */

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //判断它是否是为电量变化的Broadcast Action
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            //获取当前电量
            int level = intent.getIntExtra("level", 0);
        }
    }
}
