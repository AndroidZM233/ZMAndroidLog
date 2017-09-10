package com.zm.zmandroidlog.customview.piechart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.zm.zmandroidlog.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张明_ on 2017/9/9.
 * Email 741183142@qq.com
 */

public class PieChartFragment extends Fragment implements OnChartValueSelectedListener {
    private static String PIE_DATA = "pie_data";
    private MonthBean mData;
    private PieChart pieChart;
    private TextView tvShow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mData = arguments.getParcelable(PIE_DATA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_pie_chart, null);
        pieChart = (PieChart) mView.findViewById(R.id.pie_chart);
        tvShow = (TextView) mView.findViewById(R.id.tv_show);
        initView();
        return mView;
    }

    private void initView() {
        initData();
        //隐藏左下角颜色标识
        pieChart.getLegend().setEnabled(false);
        //隐藏右下角描述
        pieChart.getDescription().setEnabled(false);
        //关闭饼状图旋转功能
        pieChart.setRotationEnabled(false);
        //监听点击区域
        pieChart.setOnChartValueSelectedListener(this);
        //隐藏饼图区域内容
        pieChart.setDrawSliceText(false);
        pieChart.getData().getDataSet().setDrawValues(false);
        //设置中心内容
        pieChart.setCenterText(getCenterText());
    }

    private CharSequence getCenterText() {
        CharSequence centerText = "总支出\n" + mData.getSum() + "元";
        SpannableString spannableString = new SpannableString(centerText);
        //设置字体颜色
        spannableString.setSpan(new ForegroundColorSpan(Color.rgb(178, 178,178)), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体大小
        spannableString.setSpan(new AbsoluteSizeSpan(64, true), 3, centerText.length()-1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @SuppressLint("Range")
    private void initData() {

        List<PieEntry> entrys = new ArrayList<>();
        for (int i = 0; i < mData.getObj().size(); i++) {
            MonthBean.PieBean pieBean = mData.getObj().get(i);
            PieEntry pieEntry = new PieEntry(pieBean.getValue(), pieBean.getTitle());
            entrys.add(pieEntry);
        }
        PieDataSet dataSet = new PieDataSet(entrys, "");
        //设置饼状图区域颜色
        List<Integer> mColors = new ArrayList<Integer>();
        mColors.add(Color.rgb(216, 77, 719));
        mColors.add(Color.rgb(183, 56, 63));
        mColors.add(Color.rgb(247, 85, 47));
        //设置饼状图区域字体大小
        dataSet.setValueTextSize(20);
        dataSet.setColors(mColors);

        PieData mPieData = new PieData(dataSet);
        //饼状图set数据
        pieChart.setData(mPieData);
    }

    //newInstance自动生成的代码 用于外部传进数据
    public static PieChartFragment newInstance(MonthBean monthBeans) {

        Bundle args = new Bundle();
        args.putParcelable(PIE_DATA, monthBeans);
        PieChartFragment fragment = new PieChartFragment();
        fragment.setArguments(args);
        return fragment;
    }


    //监听点击区域接口实现
    //点击往外凸时调用
    @SuppressLint("SetTextI18n")
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        //点击时使区域到最底端
        float proportion = 360 / mData.getSum();
        int hX = (int) h.getX();
        //外卖90度-自己的一半
        //娱乐-外卖-自己的一半
        //其他-娱乐-外卖-自己的一半
        float angle = 90 - mData.getSum(hX) * proportion
                - mData.getObj().get(hX).getValue() * proportion / 2;
        pieChart.setRotationAngle(angle);

        tvShow.setText(mData.getObj().get(hX).getTitle() + " " + mData.getObj().get(hX).getValue());
    }

    //往里缩时调用
    @Override
    public void onNothingSelected() {

    }
}
