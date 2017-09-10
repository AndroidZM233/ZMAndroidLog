package com.zm.utilslib.view.cardslideview;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * description
 * <p>调整ViewPager滑动视差
 * Created by sunjian on 2017/6/22.
 */
class CardTransformer implements ViewPager.PageTransformer {

    private int mMaxTranslateOffsetX;
    private float mScaleRate;
    private ViewPager mViewPager;

    CardTransformer(int maxOffset, float scaleRate) {
        mMaxTranslateOffsetX = maxOffset;
        mScaleRate = scaleRate;
    }


    @Override
    public void transformPage(View page, float position) {

        if (mViewPager == null) {
            mViewPager = (ViewPager) page.getParent();
        }

        int leftInScreen = page.getLeft() - mViewPager.getScrollX();
        int centerXInViewPager = leftInScreen + page.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - mViewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * mScaleRate / mViewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);

        if (scaleFactor > 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setTranslationX(-mMaxTranslateOffsetX * offsetRate);
        }
    }
}
