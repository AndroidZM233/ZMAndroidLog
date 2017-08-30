package com.zm.utilslib.mvp;

import android.content.Context;
import android.os.Bundle;
import com.zm.utilslib.base.BaseActivity;
import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 */

public abstract class MVPBaseActivity<V extends BaseView, T
        extends BasePresenterImpl<V>> extends BaseActivity implements BaseView {
    public T mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
