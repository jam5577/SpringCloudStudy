package com.jam.java.io.nio.telnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @program: SpringCloudStudy
 * @description: NIO服务端
 * @author: Mr.Pu
 * @create: 2022-05-19 16:31
 **/

public class NioServer {
    /**
     * nio中是基于事件的，所有选择器中收到的都是一个事件，对事件进行操作
     */
    public static void main(String[] args) throws IOException {
        //获取通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        //切换非阻塞模式
        channel.configureBlocking(false);
        //绑定端口地址
        channel.bind(new InetSocketAddress(9000));
        //获取选择器
        Selector selector = Selector.open();
        //将选择器注册到通道，并开始指定监听事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
        //进行选择器轮询
        while (selector.select() > 0) {
            //获取选择器中注册通道就绪的事件
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            //开始遍历事件
            while (keys.hasNext()) {
                //提取当前事件
                SelectionKey key = keys.next();
                //判断事件具体是什么
                if (key.isAcceptable()) {
                    //获取当前接入的客户端通道
                    SocketChannel socketChannel = channel.accept();
                    //切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    //将客户端注册到通道中
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //获取客户端通道
                    SocketChannel ssChannel = (SocketChannel) key.channel();
                    //读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = ssChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }

                }

                //事件完成后需要将其移除
                keys.remove();
            }

        }

    }
}
