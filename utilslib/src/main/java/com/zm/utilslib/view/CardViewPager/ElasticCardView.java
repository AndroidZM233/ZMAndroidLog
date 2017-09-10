package com.zm.utilslib.view.CardViewPager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.zm.utilslib.R;

/**
 * description
 * <p>可调整宽高比的CardView，默认开启阴影效果
 * Created by sunjian on 2017/6/22.
 */
public class ElasticCardView extends CardView {

    private final float RATIO;

    public ElasticCardView(Context context) {
        this(context, null);
    }

    public ElasticCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElasticCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ElasticCardView);
        RATIO = array.getFloat(R.styleable.ElasticCardView_ratio, 1.0f);
        array.recycle();

        setPreventCornerOverlap(true);
        setUseCompatPadding(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (RATIO > 0) {
            int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(widthMeasureSpec) * RATIO), MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
