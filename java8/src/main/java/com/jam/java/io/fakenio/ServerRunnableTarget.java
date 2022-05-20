package com.jam.java.io.fakenio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @program: SpringCloudStudy
 * @description: 实现Runnable用于管理线程池
 * @author: Mr.Pu
 * @create: 2022-05-18 21:40
 **/

public class ServerRunnableTarget implements Runnable {

    private final Socket socket;

    public ServerRunnableTarget(Socket socket) {
        this.socket = socket;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //处理客户端拿到的消息
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
