package com.zm.zmandroidlog.customview.piechart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zm.utilslib.base.BaseActivity;
import com.zm.zmandroidlog.R;

import java.util.ArrayList;

public class PieChartActivity extends BaseActivity {
    private ViewPager vpMain;
    private ArrayList<MonthBean> fromJson;
    private Button btnNext;
    private Button btnPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData(Bundle bundle) {
        Gson gson = new Gson();
        fromJson = gson.fromJson(mJson, new TypeToken<ArrayList<MonthBean>>() {
        }.getType());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_pie_chart;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        vpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                updateJumpText();
                return PieChartFragment.newInstance(fromJson.get(position));
            }

            @Override
            public int getCount() {
                return fromJson.size();
            }
        });

        btnNext = (Button) findViewById(R.id.bt_next);
        btnNext.setOnClickListener(this);
        btnPre = (Button) findViewById(R.id.bt_pre);
        btnPre.setOnClickListener(this);

        updateJumpText();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                if (vpMain.getCurrentItem() != vpMain.getAdapter().getCount()) {
                    vpMain.setCurrentItem(vpMain.getCurrentItem() + 1);
                }
                break;
            case R.id.bt_pre:
                if (vpMain.getCurrentItem() != 0) {
                    vpMain.setCurrentItem(vpMain.getCurrentItem() - 1);
                }
                break;
        }
        updateJumpText();
    }

    private void updateJumpText() {
        if (vpMain.getCurrentItem() != vpMain.getAdapter().getCount()-1) {
            btnNext.setText(handleText(fromJson.get(vpMain.getCurrentItem()+1).getDate()));
        } else {
            btnNext.setText("没有了！");
        }
        if (vpMain.getCurrentItem() != 0) {
            btnPre.setText(handleText(fromJson.get(vpMain.getCurrentItem()-1).getDate()));
        } else {
            btnPre.setText("没有了！");
        }
    }
    private CharSequence handleText(String date) {
        return date.substring(date.indexOf("年")+1);
    }

    private String mJson = "[{\"date\":\"2016年5月\",\"obj\":[{\"title\":\"外卖\",\"value\":34},{\"title\":\"娱乐\",\"value\":21},{\"title\":\"其他\",\"value\":45}]},{\"date\":\"2016年6月\",\"obj\":[{\"title\":\"外卖\",\"value\":32},{\"title\":\"娱乐\",\"value\":22},{\"title\":\"其他\",\"value\":42}]}]";
}
