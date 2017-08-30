package com.zm.zmandroidlog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zm.utilslib.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView tv;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        tv = findViewById(R.id.tv);
        tv.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
        tv.setText("33333333333");
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()){
            case R.id.tv:
                Toast.makeText(mActivity, "sss", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
