package com.zm.utilslib.utils.socket;

/**
 * Created by 张明_ on 2017/9/29.
 * Email 741183142@qq.com
 */

public interface CallBackSocketUDP {
    void isConnect(boolean state);//判断状态
    void Receive(String data); //接收服务端返回的数据
}
