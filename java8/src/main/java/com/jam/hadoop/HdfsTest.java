package com.jam.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @program: SpringCloudStudy
 * @description: 测试hdfs文件系统
 * @author: Mr.Pu
 * @create: 2022-05-28 19:18
 **/

public class HdfsTest {

    private FileSystem fileSystem;

    @Before
    public void before() throws IOException {
        //将windows用户名在运行时修改为root用户
        //或者可以在hdfs-default.xml中修改配置文件，将permissions.enabled修改为false
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration configuration = new Configuration();
        //设置dfs副本数量
        configuration.set("dfs.replication", "1");
        //设置dfs端口号
        //configuration.set("fs.defaultFS", "hdfs://192.168.52.129:9000");
        //添加了hosts端口号后可以使用主机名进行操作
        configuration.set("fs.defaultFS", "hdfs://bird:9000");
        this.fileSystem = FileSystem.get(configuration);
    }

    /**
     * 创建hdfs客户端
     */
    @Test
    public void testClient() throws IOException {
        Path path = new Path("/");
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        for (FileStatus status : fileStatuses) {
            System.out.println(status.getLen());
            System.out.println(status.getPath());
        }
    }

    /**
     * 上传文件
     *
     * @throws IOException IO异常
     */
    @Test
    public void upload() throws IOException {
        //创建上传流
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/test.log"));
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream("D:\\WebProject\\SpringCloudStudy\\java8\\src\\main\\java\\com\\jam\\hadoop\\test.txt");
        //复制流进行上传
        IOUtils.copyBytes(fileInputStream, fsDataOutputStream, 1024, true);
    }

    /**
     * 下载文件
     *
     * @throws IOException IO异常
     */
    @Test
    public void download() throws IOException {
        Path source = new Path("/test.log");
        Path target = new Path("D:\\WebProject\\SpringCloudStudy\\java8\\src\\main\\java\\com\\jam\\hadoop");
        //第一个参数代表是否删除hdfs上的源文件，最后一个参数代表是否采用windows兼容方式下载
        fileSystem.copyToLocalFile(false, source, target, true);
    }


    @After
    public void after() throws IOException {
        fileSystem.close();
    }
}
