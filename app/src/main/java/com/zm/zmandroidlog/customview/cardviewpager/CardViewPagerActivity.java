package com.zm.zmandroidlog.customview.cardviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zm.utilslib.base.BaseActivity;
import com.zm.utilslib.view.cardslideview.CardViewPager;
import com.zm.zmandroidlog.R;
import com.zm.zmandroidlog.customview.piechart.MonthBean;
import com.zm.zmandroidlog.customview.piechart.PieChartFragment;

import java.util.ArrayList;
import java.util.List;

public class CardViewPagerActivity extends BaseActivity {
    private CardViewPager cardViewPager;

    //饼状图
    private ViewPager vpMain;
    private ArrayList<MonthBean> fromJson;
    private String mJson = "[{\"date\":\"2016年5月\",\"obj\":[{\"title\":\"外卖\",\"value\":34},{\"title\":\"娱乐\",\"value\":21},{\"title\":\"其他\",\"value\":45}]},{\"date\":\"2016年6月\",\"obj\":[{\"title\":\"外卖\",\"value\":32},{\"title\":\"娱乐\",\"value\":22},{\"title\":\"其他\",\"value\":42}]}]";


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
        return R.layout.activity_card_view_pager;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        cardViewPager = (CardViewPager) findViewById(R.id.vp_card);
        List<ImageBean> mData = new ArrayList<>();
        mData.add(new ImageBean(R.mipmap.ic_launcher));
        mData.add(new ImageBean(R.mipmap.ic_launcher));
        mData.add(new ImageBean(R.mipmap.ic_launcher_round));
        cardViewPager.bind(getSupportFragmentManager(), new MyCardHandler(), mData);
        switchCard();

        vpMain = (ViewPager) findViewById(R.id.vp_main);
        vpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PieChartFragment.newInstance(fromJson.get(position));
            }

            @Override
            public int getCount() {
                return fromJson.size();
            }
        });

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.sendMessage(handler.obtainMessage(0,position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        cardViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.sendMessage(handler.obtainMessage(1,position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    cardViewPager.setCurrentItem((Integer) msg.obj);
                    break;
                case 1:
                    vpMain.setCurrentItem((Integer) msg.obj);
                    break;
            }
        }
    };

    private void switchCard() {
        cardViewPager.setCardTransformer(180, 0.38f);
        cardViewPager.setCardPadding(60);
        cardViewPager.setCardMargin(40);
        cardViewPager.notifyUI(CardViewPager.MODE_CARD);
    }


    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}
