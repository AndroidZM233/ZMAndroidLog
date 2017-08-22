package com.zm.zmandroidlog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zm.utilslib.utils.DateUtils;
import com.zm.utilslib.utils.DeviceUtils;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

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
