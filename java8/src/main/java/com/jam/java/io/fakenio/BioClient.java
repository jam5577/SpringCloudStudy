package com.jam.java.io.fakenio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: SpringCloudStudy
 * @description: 一个BIO客户端
 * @author: Mr.Pu
 * @create: 2022-05-18 19:33
 **/

public class BioClient {

    public static void main(String[] args) throws IOException {
        //创建一个套接字Socket
        Socket socket = new Socket("127.0.0.1", 9000);
        //获取输出流
        OutputStream outputStream = socket.getOutputStream();
        //将输出流包装为打印流
        String msg = "";
        while (!"exit".equals(msg)) {
            System.out.println("请输入:");
            Scanner scanner = new Scanner(System.in);
            PrintStream ps = new PrintStream(outputStream);
            msg = scanner.nextLine();
            ps.println(msg);
            ps.flush();
        }

    }

}
