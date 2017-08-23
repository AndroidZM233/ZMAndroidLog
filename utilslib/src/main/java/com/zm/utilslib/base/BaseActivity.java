package com.zm.utilslib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 张明_ on 2017/8/23.
 * Email 741183142@qq.com
 */

public class BaseActivity extends Activity {


    public void openAct(Context packageContext, Class<?> cls){
        Intent intent=new Intent(packageContext,cls);
        startActivity(intent);
    }

}
