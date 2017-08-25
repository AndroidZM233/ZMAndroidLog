package com.zm.zmandroidlog;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.zm.utilslib.base.BaseActivity;
import com.zm.utilslib.utils.DateUtils;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        String currentTimeMillis = DateUtils.getCurrentTimeMillis(DateUtils.FORMAT_FULL);
        tv.append(currentTimeMillis);
        Calendar c= Calendar.getInstance();
        int i = c.get(Calendar.YEAR);
        int i1 = c.get(Calendar.MONTH) + 1;
        tv.append(i+"\n");
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }
}
