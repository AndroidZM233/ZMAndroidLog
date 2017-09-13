package com.zm.zmandroidlog.customview.RadarView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.zm.utilslib.base.BaseActivity;
import com.zm.utilslib.view.RadarView.RadarView;
import com.zm.zmandroidlog.R;

public class RadarViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_radio_view;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        final RadarView radarView = (RadarView) findViewById(R.id.radar);
        //设置雷达扫描方向
        radarView.setDirection(RadarView.ANTI_CLOCK_WISE);
        radarView.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
//                radarView.stop();
//                radarView.hideView();
            }
        }).start();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
