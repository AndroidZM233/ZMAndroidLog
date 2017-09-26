package com.zm.zmandroidlog.customview.BatteryView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.zm.utilslib.base.BaseActivity;
import com.zm.utilslib.view.BatteryView;
import com.zm.zmandroidlog.R;

public class BatteryActivity extends BaseActivity {
    private BatteryView horizontalBattery, verticalBattery;
    private int power;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_battery;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        horizontalBattery = (BatteryView) findViewById(R.id.horizontalBattery);
        verticalBattery = (BatteryView) findViewById(R.id.verticalBattery);
        verticalBattery.setColor(Color.BLACK);
        verticalBattery.setPower(85);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
