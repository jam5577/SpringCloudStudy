package com.jam.java.excel;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 写excel的工具类
 * @author: Mr.Pu
 * @create: 2022-04-24 20:03
 **/

public class ExcelUtil<T> {

    //写入excel，传入参数就可完成简单编写，最简单的：将带有注解的数据传入，在根目录导出为一个excel文件
    public static void simpleWrite(Class<?> clazz, List<?> list) {
        String filename = clazz.getName() + File.pathSeparator + ".xlsx";
        EasyExcel.write(filename, clazz)
                .sheet()
                .doWrite(list);
    }

    public void test() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setDate(new Date());
            demoData.setString("ceshi" + i);
            list.add(demoData);
        }
        simpleWrite(DemoData.class, list);
    }
}
