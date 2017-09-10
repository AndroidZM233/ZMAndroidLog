package com.zm.utilslib.view.CardViewPager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * description
 * <p>card滑动条目基类
 * Created by sunjian on 2017/6/22.
 */
public class BaseCardItem<T> extends Fragment {

    protected static final String ARGUMENTS_HANDLER = "cards_handler";
    protected static final String ARGUMENTS_DATA = "cards_data";
    protected static final String ARGUMENTS_POSITION = "cards_position";

    protected Context mContext;

    @CardViewPager.TransformerMode
    int currentMode;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void bindHandler(CardHandler<T> handler) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            setArguments(bundle);
        }
        bundle.putSerializable(ARGUMENTS_HANDLER, handler);
    }
}
