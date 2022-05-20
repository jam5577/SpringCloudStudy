package com.jam.java.io.biofile;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * @program: SpringCloudStudy
 * @description: 对于多客户端的链接，需要创建多个一一对应的线程进行处理
 * @author: Mr.Pu
 * @create: 2022-05-18 19:55
 **/

public class FileThread extends Thread {

    private static final String PATH = "C:\\Users\\dell\\Desktop\\新建文件夹";

    /**
     * 接收一个socket参数进行一一对应构造线程的处理
     */
    private Socket socket;

    public FileThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream stream = new DataInputStream(socket.getInputStream())) {
            //读取文件类型
            String suffix = stream.readUTF();
            System.out.println("读取到的文件类型:" + suffix);
            //定义输出流进行文件输出
            final OutputStream outputStream = new FileOutputStream(PATH + File.separator + UUID.randomUUID().toString() + suffix);
            //读取数据并写出字节流到out中
            final byte[] buffer = new byte[1024];
            int len;
            while ((len = stream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
