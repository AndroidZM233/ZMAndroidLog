package com.zm.zmandroidlog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zm.utilslib.data.DataConversionUtil;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        byte[] bytes = DataConversionUtil.hexStringToBytes("2B44EFD9");
        String s = DataConversionUtil.bytesToHexString(bytes);
        tv.setText(s);
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }
}
