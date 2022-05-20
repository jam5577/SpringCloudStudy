package com.jam.java.io.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: SpringCloudStudy
 * @description: 一个nio的channel测试
 * @author: Mr.Pu
 * @create: 2022-05-19 15:17
 **/

public class NioChannel {

    private static final String PATH = "java8/src/main/java/com/jam/java/io/nio/data01.txt";

    public static void main(String[] args) {
//        fileWrite();
//        fileRead();
//        fileCopy();
        transfer();
    }

    public static void fileWrite() {
        try {
            //写入文件流，在文件流获取channel通道时，不能将fos定义为OutputStream，否则没有相关api
            FileOutputStream fos = new FileOutputStream(PATH);
            FileChannel fileChannel = fos.getChannel();
            //分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("这是一个测试的文件".getBytes());
            //把缓冲区切换为写入模式
            buffer.flip();
            //写入channel
            fileChannel.write(buffer);
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileRead() {
        try {
            //获取文件输入流
            FileInputStream fis = new FileInputStream(PATH);
            //拿到输入流的channel通道
            FileChannel channel = fis.getChannel();
            //定义缓冲区并写入数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //打印显示并关闭通道
            channel.read(buffer);
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.remaining()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileCopy() {
        try {
            //获取文件的输入流
            FileInputStream fis = new FileInputStream(PATH);
            //获取文件的输入流
            FileOutputStream fos = new FileOutputStream("java8/src/main/java/com/jam/java/io/nio/datanew.txt");
            //拿到各自的channel通道，并定义缓冲区
            FileChannel fisChannel = fis.getChannel();
            FileChannel fosChannel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //执行复制操作，从输入流复制到输出流
            while (true) {
                //在下一次循环开始时需要先清空缓冲区
                buffer.clear();
                //先读取数据到缓冲区再写入数据到缓冲区
                int flag = fisChannel.read(buffer);
                //当flag为-1时跳出循环
                if (flag < 0) {
                    break;
                }
                //将缓冲区切换并写入数据
                buffer.flip();
                fosChannel.write(buffer);
            }
            //关闭通道和流
            fisChannel.close();
            fosChannel.close();
            fis.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * transferFrom是从目标通道中去读取原通道中的数据
     * transferTo是从原通道中读取数据到目标通道中
     */
    public static void transfer() {
        try {
            //获取文件的输入流
            FileInputStream fis = new FileInputStream(PATH);
            //获取文件的输入流
            FileOutputStream fos = new FileOutputStream("java8/src/main/java/com/jam/java/io/nio/datanew.txt");
            //拿到各自的channel通道，并定义缓冲区
            FileChannel fisChannel = fis.getChannel();
            FileChannel fosChannel = fos.getChannel();
            fosChannel.transferFrom(fisChannel, fisChannel.position(), fisChannel.size());
            fisChannel.close();
            fosChannel.close();
            //fisChannel.transferTo(fisChannel.position(),fisChannel.size(),fosChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
