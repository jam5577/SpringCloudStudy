package com.jam.java.io.biofile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.LongBuffer;

/**
 * @program: SpringCloudStudy
 * @description: 用于文件上传的server
 * @author: Mr.Pu
 * @create: 2022-05-18 21:50
 **/

public class FileServer {

    public static void main(String[] args) {
        final LongBuffer allocate = LongBuffer.allocate(10);
        try {
            ServerSocket socket = new ServerSocket(9000);
            while (true) {
                Socket accept = socket.accept();
                new FileThread(accept).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
