/*
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<receiver android:name=".NetworkChangeReceiver">
   <intent-filter>
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
    </intent-filter>
</receiver>
 */
package com.zm.zmandroidlog.broadcast;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * 网络连接状态变化广播接收器
 * Created by 张明_ on 2017/8/23.
 * Email 741183142@qq.com
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectMgr = (ConnectivityManager)
                context.getSystemService(CONNECTIVITY_SERVICE);
        if (connectMgr==null){
            return;
        }
        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            //网络断开
        } else {
            //网络连接
        }
    }
}
