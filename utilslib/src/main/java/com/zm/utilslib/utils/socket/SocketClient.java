package com.zm.utilslib.utils.socket;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by 张明_ on 2017/9/29.
 * Email 741183142@qq.com
 */

public class SocketClient {

    private SocketClient() {
    }

    private static SocketClient socketClient;
    private static DatagramSocket socketUdp = null;
    private static Socket socketTcp = null;

    //获取单例
    public static SocketClient getSingle() {
        if (socketClient == null) {
            synchronized (SocketClient.class) {//加锁 多个进程使用
                if (socketClient == null) {//优化
                    socketClient = new SocketClient();
                }
            }
        }
        return socketClient;
    }


    /**
     * 获取TCP连接
     *
     * @param serverIp ：服务Ip地址
     * @param prot     ：端口号
     * @param data     ：data 要发送的数据
     * @param call     ：CallBackSocket  接口
     */
    public void getTCPConnect(String serverIp, int prot, List<String> data, CallBackSocketTCP call) {
        //创建Socket"192.168.1.115",60000
        try {
            socketTcp = new Socket(serverIp, prot);
            SocketUtil util = new SocketUtil(socketTcp);
            for (int i = 0; i < data.size(); i++) {
                Log.d("ZM_TIMER", "TCP发送数据: " + data.get(i));
                util.sendData(data.get(i));
            }
            //获取输入流，并读取服务端的响应信息
//            boolean b = true;
//            while (b) {
            boolean ba = util.isConnect();
            //把值给接口，这里的接口作用，就是传值的作用
            call.isConnect(ba);
            System.out.println("当前Soket是否连接：" + ba);
            String info = util.receiveData();
            //同理
            call.Receive(info);
            Log.d("ZM_TIMER", "服务器回复: " + info);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            call.isConnect(false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            call.isConnect(false);
        }
    }


    public void getTCPConnect(String serverIp, int prot, String data, CallBackSocketTCP call) {
        //创建Socket"192.168.1.115",60000
        try {
            socketTcp = new Socket(serverIp, prot);
            SocketUtil util = new SocketUtil(socketTcp);
            util.sendData(data);
            //获取输入流，并读取服务端的响应信息
            boolean b = true;
            while (b) {
                boolean ba = util.isConnect();
                //把值给接口，这里的接口作用，就是传值的作用
                call.isConnect(ba);
                System.out.println("当前Soket是否连接：" + ba);
                String info = util.receiveData();
                //同理
                call.Receive(info);
                System.out.println(" 服务器说：" + info);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            call.isConnect(false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            call.isConnect(false);
        }
    }

    /**
     * UDP连接，
     *
     * @param port  端口号
     * @param datas 通过UDP发送的数据
     */
    public void getUDPConnect(int port, String datas, CallBackSocketUDP call) {
        try {
               /*
                 * 向服务器端发送数据
                 */
            // 1.定义服务器的地址、端口号、数据localhost
            InetAddress address = InetAddress.getByName("localhost");
//                port = 8800;
            byte[] data = datas.getBytes();
            // 2.创建数据报，包含发送的数据信息
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            // 3.创建DatagramSocket对象
            socketUdp = new DatagramSocket();
            // 4.向服务器端发送数据报
            socketUdp.send(packet);
            boolean b = true;
            while (b) {
                         /*
                         * 接收服务器端响应的数据
                         */
                // 1.创建数据报，用于接收服务器端响应的数据
                byte[] data2 = new byte[1024];
                DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
                // 2.接收服务器响应的数据
                socketUdp.receive(packet2);
                // 3.读取数据
                String reply = new String(data2, 0, packet2.getLength());

                call.Receive(reply);//把发送的数据交给接口

                System.out.println("我是客户端，服务器说：" + reply);
                // 4.关闭资源
            }
//              socketUdp.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 关闭 。
     */
    public void closeSocket() {
        if (socketUdp != null) {
            socketUdp.close();
            System.out.println("关闭了UDP连接");
        } else if (socketTcp != null) {
            try {
                socketTcp.close();
                Log.d("ZM_TIMER", "closeSocket: 已关闭");
                System.out.println("关闭了TCP连接");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("ZM_TIMER", "closeSocket: 异常");
            }
        } else {
            System.out.println("没有打开无须关闭！！");
        }
    }
}
