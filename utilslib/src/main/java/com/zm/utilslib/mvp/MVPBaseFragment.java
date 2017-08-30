package com.zm.utilslib.mvp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zm.utilslib.base.BaseFragment;

import java.lang.reflect.ParameterizedType;

/**
 * MVPPlugin
 */

public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends BaseFragment implements BaseView{
    public T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detachView();
    }

    public abstract int getLayout();
    public View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(getLayout(),container,false);
        this.root=root;
        return root;
    }


    @Override
    public Context getContext() {
        return super.getContext();
    }

    public  <T> T getInstance(Object o, int i) {
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
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }


}
