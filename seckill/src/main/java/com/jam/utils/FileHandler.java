package com.jam.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Objects;

/**
 * @program: SpringCloudStudy
 * @description: 操作文件
 * @author: Mr.Pu
 * @create: 2022-05-17 08:48
 **/

public class FileHandler {

    private static final Logger log = LoggerFactory.getLogger(FileHandler.class);

    private static final String PATH = "E:\\下载\\done";

    /**
     * 删除路径下每个文件中的空目录
     * 回溯算法
     */
    public static void delete() {
        File file = new File(PATH);
        File[] files = file.listFiles(pathname -> pathname.exists() && pathname.isDirectory());
        assert files != null;
        backtrack(files);
    }

    static void backtrack(File[] file) {
        if (file == null) {
            return;
        }
        for (File value : file) {
            if (Objects.requireNonNull(value.listFiles()).length == 0) {
                log.info("{}:文件的长度为:{}", value.getName(), Objects.requireNonNull(value.listFiles()).length);
                boolean delete = value.delete();
                if (delete) {
                    log.info("{}:删除成功！", value.getName());
                    continue;
                }
            }
            backtrack(value.listFiles(p -> p.exists() && p.isDirectory()));
        }
    }

    public static void main(String[] args) {
        delete();
    }
}
