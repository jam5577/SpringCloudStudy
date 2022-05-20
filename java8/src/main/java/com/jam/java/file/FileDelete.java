package com.jam.java.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @program: SpringCloudStudy
 * @description: 文件删除类
 * @author: Mr.Pu
 * @create: 2022-05-20 14:25
 **/

public class FileDelete {

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("E:\\下载\\done"), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Files.isDirectory(file)) {
                    if (Files.list(file).count() == 0) {
                        Files.delete(file);
                    }
                }
                return super.visitFile(file, attrs);
            }
        });
    }
}
