package com.zm.utilslib.view.cardslideview;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * description
 * <p>构建view并处理UI逻辑
 * Created by sunjian on 2017/6/22.
 */
public interface CardHandler<T> extends Serializable {

    View onBind(Context context, T data, int position, @CardViewPager.TransformerMode int mode);
}
