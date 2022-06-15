package com.jam.java.file;

import org.junit.Test;

import java.io.*;

/**
 * @program: SpringCloudStudy
 * @description: 读取文件并替换链接
 * @author: Mr.Pu
 * @create: 2022-06-15 11:23
 **/

public class FileLinkReplace {

    //    private static final String FILE_PATH = "D:\\WebProject\\SpringCloudStudy\\bigdata-notes\\notes";
    private static final String FILE_PATH = "E:\\Downloads\\download-temp\\temp-md";

    private static final String OLD_LINK = "https://gitee.com/heibaiying/BigData-Notes/raw/master/pictures/";

    private static final String NEW_LINK = "https://gitee.com/jam5577/depository/raw/repo/picture/";

    @Test
    public void read() throws IOException {
        File file = new File(FILE_PATH);
        File[] files = file.listFiles();
        System.out.println(files.length);
        for (File f : files) {
            if (f.isFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                // 内存流, 作为临时流
                CharArrayWriter tempStream = new CharArrayWriter();
                String temp;
                while ((temp = reader.readLine()) != null) {
                    if (temp.contains(OLD_LINK)) {
                        // 替换每行中, 符合条件的字符串
                        temp = temp.replaceAll(OLD_LINK, NEW_LINK);
                    }
                    // 将该行写入内存
                    tempStream.write(temp);
                    // 添加换行符
                    tempStream.append(System.getProperty("line.separator"));
                }
                reader.close();
                FileWriter writer = new FileWriter(f);
                tempStream.writeTo(writer);
                writer.close();
            }
        }
    }
}
