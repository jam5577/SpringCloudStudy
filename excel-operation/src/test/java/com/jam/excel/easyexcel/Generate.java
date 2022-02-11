package com.jam.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.jam.excel.easyexcel.entity.DemoData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 生成数据
 * @author: Mr.Pu
 * @create: 2022-02-10 21:05
 **/
@Slf4j
public class Generate {

    private static final String path="D:\\WebProject\\SpringCloudStudy\\excel-operation\\src\\test\\java\\com\\jam\\excel\\poi\\";


    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void simpleWrite() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = path + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        // 分页查询数据
        EasyExcel.write(fileName, DemoData.class)
                .sheet("模板")
                .doWrite(this::data);

//        // 写法2
//        fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
//
//        // 写法3
//        fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = null;
//        try {
//            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(data(), writeSheet);
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
    }

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = path + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
//        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
//            for (DemoData demoData : dataList) {
//                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
//            }
//        })).sheet().doRead();
//
//        // 写法2：
//        // 匿名内部类 不用额外写一个DemoDataListener
//        fileName = path + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new ReadListener<DemoData>() {
//            /**
//             * 单次缓存的数据量
//             */
//            public static final int BATCH_COUNT = 100;
//            /**
//             *临时存储
//             */
//            private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//
//            @Override
//            public void invoke(DemoData data, AnalysisContext context) {
//                cachedDataList.add(data);
//                if (cachedDataList.size() >= BATCH_COUNT) {
//                    saveData();
//                    // 存储完成清理 list
//                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//                }
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext context) {
//                saveData();
//            }
//
//            /**
//             * 加上存储数据库
//             */
//            private void saveData() {
//                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
//                log.info("存储数据库成功！");
//            }
//        }).sheet().doRead();

        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法3：
        fileName = path + "1644498692943.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
//
//        // 写法4：
//        fileName = path + "demo" + File.separator + "demo.xlsx";
//        // 一个文件一个reader
//        ExcelReader excelReader = null;
//        try {
//            excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//            // 构建一个sheet 这里可以指定名字或者no
//            ReadSheet readSheet = EasyExcel.readSheet(0).build();
//            // 读取一个sheet
//            excelReader.read(readSheet);
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
    }
}
