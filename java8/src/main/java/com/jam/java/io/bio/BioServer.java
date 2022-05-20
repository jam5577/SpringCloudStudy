package com.jam.java.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: SpringCloudStudy
 * @description: 一个BIO模型的实现类
 * @author: Mr.Pu
 * @create: 2022-05-18 18:59
 **/

public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接");
            //BIO阻塞方法
            Socket accept = serverSocket.accept();
            System.out.println("有客户端连接了");
            new BioThread(accept).start();
        }
    }
}
