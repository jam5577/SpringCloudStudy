package com.jam.excel.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

/**
 * @program: SpringCloudStudy
 * @description: poi写入excel测试类
 * @author: Mr.Pu
 * @create: 2022-02-10 18:57
 **/

public class WriteTest {

    private static final String path="D:\\WebProject\\SpringCloudStudy\\excel-operation\\src\\test\\java\\com\\jam\\excel\\poi\\";

    //03版本excel
    @Test
    public void excelWrite() throws Exception{
        String name = UUID.randomUUID().toString();
        //工作簿
        Workbook workbook = new HSSFWorkbook();
        //工作表
        Sheet sheet = workbook.createSheet("test");

        //创建行
        Row row = sheet.createRow(0);
        //创建单元格
        Cell cell = row.createCell(0);
        //在单元格中写入数据
        cell.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        //使用IO进行文件写出
        FileOutputStream fileOutputStream = new FileOutputStream(path+name+".xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

    }
}
