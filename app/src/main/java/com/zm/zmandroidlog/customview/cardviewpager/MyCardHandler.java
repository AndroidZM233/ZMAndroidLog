package com.zm.zmandroidlog.customview.cardviewpager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.zm.utilslib.view.CardViewPager.CardHandler;
import com.zm.utilslib.view.CardViewPager.CardViewPager;
import com.zm.utilslib.view.CardViewPager.ElasticCardView;
import com.zm.zmandroidlog.R;

/**
 * Created by 张明_ on 2017/9/10.
 * Email 741183142@qq.com
 */

public class MyCardHandler implements CardHandler<ImageBean> {
    @Override
    public View onBind(Context context, ImageBean data, int position, int mode) {
        View view=View.inflate(context, R.layout.cardhandler_item,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.image);
        ElasticCardView elasticCardView= (ElasticCardView) view.findViewById(R.id.cardview);
        final boolean isCard = mode == CardViewPager.MODE_CARD;
        elasticCardView.setPreventCornerOverlap(isCard);
        elasticCardView.setUseCompatPadding(isCard);

        imageView.setImageResource(data.getImg());
        return view;
    }
}
