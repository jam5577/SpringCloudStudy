package com.jam.java.io.biofile;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @program: SpringCloudStudy
 * @description: 文件上传客户端
 * @author: Mr.Pu
 * @create: 2022-05-18 21:50
 **/

public class FileClient {

    private static final String NAME = "E:\\Steam\\steamapps\\workshop\\content\\1834860\\2730539880\\8000.png";

    public static void main(String[] args) {
        try (//读取本地数据并上传到服务器
             InputStream inputStream = new FileInputStream(NAME)) {
            Socket socket = new Socket("127.0.0.1", 9000);
            //获取特殊输出字符流
            DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
            //定义数据编码
            stream.writeUTF(".png");
            //设置缓冲字符
            byte[] buffer = new byte[1024];
            //设置文件数据长度
            int len;
            //当读取文件长度 > 0时就进行上传
            while ((len = inputStream.read(buffer)) > 0) {
                stream.write(buffer, 0, len);
            }
            stream.flush();
            //通知服务端文件上传已结束
            socket.shutdownOutput();
            System.out.println("文件上传结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
