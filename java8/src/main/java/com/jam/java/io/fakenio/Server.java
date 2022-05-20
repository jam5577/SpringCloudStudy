package com.jam.java.io.fakenio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: SpringCloudStudy
 * @description: 伪异步io的实现，使用线程池来进行管理
 * @author: Mr.Pu
 * @create: 2022-05-18 21:16
 **/

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(9000);
            //初始化一个线程池
            ServerThreadPool pool = new ServerThreadPool(5, 3);
            while (true) {
                //定义线程池进行socket管理
                Socket accept = socket.accept();
                //将socket封装为一个Runnable对象用于线程池管理
                Runnable target = new ServerRunnableTarget(accept);
                //提交给线程池
                pool.execute(target);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
