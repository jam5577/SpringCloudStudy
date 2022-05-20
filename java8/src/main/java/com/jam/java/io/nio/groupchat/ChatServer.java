package com.jam.java.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @program: SpringCloudStudy
 * @description: 一个群聊的服务端
 * @author: Mr.Pu
 * @create: 2022-05-19 17:58
 **/

public class ChatServer {
    /**
     * 一个选择器
     */
    Selector selector;
    /**
     * 一个通道
     */
    ServerSocketChannel channel;
    /**
     * 端口
     */
    static final int PORT = 9000;

    /**
     * 定义初始化逻辑
     */
    public ChatServer() {
        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress(PORT));
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        System.out.println("监听线程: " + Thread.currentThread().getName());
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel accept = channel.accept();
                        accept.configureBlocking(false);
                        System.out.println(accept.getRemoteAddress() + " 上线 ");
                        accept.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        //定义读取数据方法
                        readData(key);
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey key) throws IOException {
        SocketChannel socketChannel = null;
        try {
            //获取当前客户端通道
            socketChannel = (SocketChannel) key.channel();
            //创建缓冲区对象开始接收客户端消息
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(buffer);
            if (read > 0) {
                buffer.flip();
                //获取到读取的内容
                String msg = new String(buffer.array(), 0, buffer.remaining());
                System.out.println("获取到的内容:" + msg);
                sendAllMsg(msg, socketChannel);
            }
        } catch (IOException e) {
            //当前客户端离线，取消注册
            System.out.println("当前有人离线了:" + socketChannel.getRemoteAddress().toString());
            key.cancel();
            socketChannel.close();

        }
    }

    private void sendAllMsg(String msg, SocketChannel socketChannel) throws IOException {
        System.out.println("开始转发消息，当前处理线程:" + Thread.currentThread().getName());
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            //拿到选择器中所有的通道并进行转发，注意不能转发给自己
            if (channel instanceof SocketChannel && channel != socketChannel) {
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                ((SocketChannel) channel).write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        //创建服务端实例
        ChatServer server = new ChatServer();
        //开启监听
        server.listen();
    }

}
