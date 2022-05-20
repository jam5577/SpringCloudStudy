package com.jam.java.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @program: SpringCloudStudy
 * @description: 对于多客户端的链接，需要创建多个一一对应的线程进行处理
 * @author: Mr.Pu
 * @create: 2022-05-18 19:55
 **/

public class BioThread extends Thread {

    /**
     * 接收一个socket参数进行一一对应构造线程的处理
     */
    private Socket socket;

    public BioThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = bf.readLine()) != null) {
                System.out.println("收到的客户端消息：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
