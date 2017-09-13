package com.zm.zmandroidlog.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zm.utilslib.base.BaseActivity;
import com.zm.zmandroidlog.R;
import com.zm.zmandroidlog.customview.CircleMenu.CircleActivity;
import com.zm.zmandroidlog.customview.CircleMenu.CircleShrinkActivity;
import com.zm.zmandroidlog.customview.RadarView.RadarViewActivity;
import com.zm.zmandroidlog.customview.cardviewpager.CardViewPagerActivity;
import com.zm.zmandroidlog.customview.piechart.PieChartActivity;

public class CustomViewActivity extends BaseActivity implements View.OnClickListener {
    private Button btnPieChart;
    private Button btnCardVP;
    private Button btnCircle;
    private Button btnCircleShrink;
    private Button btnRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_custom_view;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        btnPieChart = (Button) findViewById(R.id.btn_pie_chart);
        btnPieChart.setOnClickListener(this);
        btnCardVP = (Button) findViewById(R.id.btn_card_vp);
        btnCardVP.setOnClickListener(this);
        btnCircle = (Button) findViewById(R.id.btn_circle);
        btnCircle.setOnClickListener(this);
        btnCircleShrink = (Button) findViewById(R.id.btn_circle_shrink);
        btnCircleShrink.setOnClickListener(this);
        btnRadio= (Button) findViewById(R.id.btn_radio);
        btnRadio.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pie_chart:
                openAct(this, PieChartActivity.class);
                break;
            case R.id.btn_card_vp:
                openAct(this, CardViewPagerActivity.class);
                break;
            case R.id.btn_circle:
                openAct(this, CircleActivity.class);
                break;
            case R.id.btn_circle_shrink:
                openAct(this, CircleShrinkActivity.class);
                break;
            case R.id.btn_radio:
                openAct(this, RadarViewActivity.class);
                break;
        }
    }
}
