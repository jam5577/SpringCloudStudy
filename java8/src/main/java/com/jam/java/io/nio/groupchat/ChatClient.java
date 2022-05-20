package com.jam.java.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @program: SpringCloudStudy
 * @description: 客户端实现
 * @author: Mr.Pu
 * @create: 2022-05-19 19:19
 **/

public class ChatClient {

    Selector selector;

    static final int PORT = 9000;

    SocketChannel socketChannel;

    public ChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("当前客户端准备完成:");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInfo() throws IOException {
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    System.out.println(new String(buffer.array()).trim());
                }
                iterator.remove();
            }
        }
    }

    private void sendToServer(String s) {
        try {
            socketChannel.write(ByteBuffer.wrap((Thread.currentThread().getName() + ":" + s).getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        //定义一个线程专门负责监听服务端发送过来的读消息事件
        new Thread(() -> {
            try {
                client.readInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            client.sendToServer(s);
        }
    }
}
