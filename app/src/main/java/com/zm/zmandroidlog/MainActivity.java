package com.zm.zmandroidlog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zm.utilslib.base.BaseActivity;
import com.zm.zmandroidlog.customview.CustomViewActivity;

public class MainActivity extends BaseActivity {
    private Button btnView;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        btnView= (Button) findViewById(R.id.btn_view);
        btnView.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()){
            case R.id.btn_view:
                openAct(this, CustomViewActivity.class);
                break;
        }
    }
}
