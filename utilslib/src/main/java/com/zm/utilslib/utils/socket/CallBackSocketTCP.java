package com.zm.utilslib.utils.socket;

/**
 * Created by 张明_ on 2017/9/29.
 * Email 741183142@qq.com
 */

public interface CallBackSocketTCP {
    //这个回调用于获取服务端返回的数据
    void Receive(String info);
    //判断是否处在连接在状态
    void isConnect(boolean state);
}
