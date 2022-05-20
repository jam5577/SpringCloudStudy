package com.jam.utils;

import com.jam.app.entity.Content;
import com.jam.downloader.downbit.DownloadMain;
import com.jam.downloader.downbit.util.ThunderUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: SpringCloudStudy
 * @description: jsoup爬虫工具
 * @author: Mr.Pu
 * @create: 2022-03-23 13:47
 **/

@Slf4j
public class ParseUtil {

    private static final String baseUrl = "https://www.xiurenb.net/plus/search/index.asp";

    private static final String basePageUrl = "https://www.xiurenb.net/";

//    private static final String imageDownloadUrl = "https://t.xiurenb.net/";

    private static final String path = "E:\\图片\\SomeThing\\pix_result\\";

    private static final String name = "娜露";

    public static void main(String[] args) throws Exception {
        requestUrl(name);
//        parse("java").forEach(System.out::println);
    }

    public static List<Content> parse(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        System.out.println(element.html());
        Elements elements = document.getElementsByTag("li");

        List<Content> list = new ArrayList<>();

        for (Element el : elements) {
            if (el.attr("class").equalsIgnoreCase("gl-item")) {
                String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String title = el.getElementsByClass("p-name").eq(0).text();
                System.out.println("============================");
                System.out.println(img);
                System.out.println(price);
                System.out.println(title);
                Content content = Content.builder().img(img).name(title).price(price).build();
                list.add(content);

            }
        }
        return list;
    }

    public static boolean delDir(File file) {
//        boolean delete = false;
//        if (file.isFile()) {
//            File[] files = file.listFiles();
//            for (File file1 : files) {
//                delete = file1.delete();
//            }
//        }
//        return delete;
        if (file.isDirectory()) {
            File[] zFiles = file.listFiles();
            for (File file2 : zFiles) {
                delDir(file2);
            }
        }
        return file.delete();
    }

    public static void requestUrl(String keyword) throws Exception {
        //分页链接集合
        List<String> pageList = new ArrayList<>();
        //单个页面链接合集
        List<String> dataList = new ArrayList<>();
        //图片链接合集
        List<String> imageList = new ArrayList<>();
        String searchUrl = baseUrl + "?keyword=" + keyword + "&searchtype=title&p=1";
        Document document = Jsoup.parse(new URL(searchUrl), 300000);
        Elements a = document.select("div.page > a[href]");
        log.info("max page is:{}", a.last().text());
        for (Element element : a) {
            pageList.add(element.attr("href"));
        }
        for (String url : pageList) {
            url = baseUrl + url;
            Document parse = Jsoup.parse(new URL(url), 300000);
            Elements select = parse.select("div.title > a[href]");
            dataList.add(select.attr("href"));
        }
        for (String pageUrl : dataList) {
            pageUrl = basePageUrl + pageUrl;
            Document parse = Jsoup.parse(new URL(pageUrl), 300000);
            Elements select = parse.select("div.content");
            Elements elements = select.select("[href]");
            for (Element element : elements) {
                imageList.add(element.attr("abs:href"));
            }
        }
        imageList = imageList.stream().distinct().collect(Collectors.toList());
        log.info("pageUrl:{}", imageList);
        for (String downloadLink : imageList) {
            log.info("downloadLink:{}", downloadLink);
            Document parse = Jsoup.parse(new URL(downloadLink), 300000);
            Elements select = parse.select("div.content");
            Elements elements = select.select("[src]");
            for (Element element : elements) {
                String src = element.attr("abs:src");
                log.info("下载链接:{}", src);
                if (!Objects.isNull(src)) {
//                    download(src);
//                download(imageDownloadUrl + src.replace(src.charAt(1), String.valueOf(src.charAt(1)).toUpperCase().charAt(0)));
//                    Downloader downloader = new Downloader(src, 4, name);
//                    downloader.start();
                    new DownloadMain().download(ThunderUtils.toHttpUrl(src));
                }
            }
        }
        log.info("pageList:{}", pageList);
        log.info("dataList:{}", dataList);
        log.info("imageList:{}", imageList);
    }

    public static void download(String href) throws InterruptedException, IOException {
        long begin_time = new Date().getTime();
        URL url = null;
        try {
            url = new URL(href);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        URLConnection conn = null;
        try {
            assert url != null;
            conn = url.openConnection();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        assert conn != null;
        conn.setConnectTimeout(300000);
        conn.setReadTimeout(300000);
        log.info("设置链接超时时间为{}秒,设置读取超时时间为{}秒",
                conn.getConnectTimeout() / 1000, conn.getReadTimeout() / 1000);
        String fileName = url.getFile();
        File check = new File(path + name);
        String[] list = check.list();
        boolean contains = Arrays.asList(list).contains(fileName);
        if (!contains) {
            int fileSize = conn.getContentLength();
            while (fileSize < 0) {
                log.error("文件大小为{},现在重新获取链接>>>", fileSize);
                conn = url.openConnection();
                fileSize = conn.getContentLength();
            }
            fileName = fileName.substring(fileName.lastIndexOf("/"));
            log.debug("开始下载>>>");

            log.debug("文件总共大小为:{}字节", fileSize);


            // 设置分块大小
            int blockSize = 1024 * 200;
            // 文件分块的数量
            int blockNum = fileSize / blockSize;

            if ((fileSize % blockSize) != 0) {
                blockNum += 1;
            }

            log.info("分块数->线程数:{}", blockNum);

            File tempFilePath = new File(path + "temp_file\\");
            if (!tempFilePath.exists()) {
                tempFilePath.mkdirs();
            }

            Thread[] threads = new Thread[blockNum];
            for (int i = 0; i < blockNum; i++) {

                // 匿名函数对象需要用到的变量
                final int index = i;
                final int finalBlockNum = blockNum;
                final String finalFileName = fileName;

                // 创建一个线程
                int finalFileSize = fileSize;
                threads[i] = new Thread(() -> {
                    URL url1 = null;
                    try {
                        url1 = new URL(href);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                    try {

                        // 重新获取连接
                        assert url1 != null;
                        URLConnection conn1 = url1.openConnection();
                        conn1.setConnectTimeout(300000);
                        conn1.setReadTimeout(300000);

                        // 重新获取流
                        InputStream in = conn1.getInputStream();
                        // 定义起始和结束点
                        int beginPoint, endPoint;

                        log.info("第{}块文件：", index + 1);
                        beginPoint = index * blockSize;

                        // 判断结束点
                        if (index < finalBlockNum - 1) {
                            endPoint = beginPoint + blockSize;
                        } else {
                            endPoint = finalFileSize;
                        }

                        log.info("起始字节数：{},结束字节数：{}", beginPoint, endPoint);

                        // 将下载的文件存储到一个文件夹中
                        //当该文件夹不存在时，则新建
                        File filePath = new File(path + name);
                        if (!filePath.exists()) {
                            boolean b = filePath.mkdirs();
                            if (b) {
                                log.info("创建{}文件夹成功", filePath);
                            }
                        }
                        FileOutputStream fos = new FileOutputStream(new File(path + "temp_file\\", finalFileName + "_" + (index + 1)));


                        // 跳过 beginPoint个字节进行读取
                        long l = in.skip(beginPoint);
                        if (l > 0) log.info("跳过{}个字节读取", beginPoint);
                        byte[] buffer = new byte[1024];
                        int count;
                        // 定义当前下载进度
                        int process = beginPoint;
                        // 当前进度必须小于结束字节数
                        while (process < endPoint) {

                            count = in.read(buffer);
                            // 判断是否读到最后一块
                            if (process + count >= endPoint) {
                                count = endPoint - process;
                                process = endPoint;
                            } else {
                                // 计算当前进度
                                process += count;
                            }
                            // 保存文件流
                            fos.write(buffer, 0, count);

                        }
                        fos.close();
                        in.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                threads[i].start();

            }

            // 当所有线程都结束时才开始文件的合并
            for (Thread t : threads) {
                t.join();
            }

            // 若该文件夹不存在，则创建一个文件夹
            File filePath = new File(path + name);
            if (!filePath.exists()) {
                boolean b = filePath.mkdirs();
                if (b) {
                    log.info("创建了{}文件夹", filePath);
                } else {
                    log.error("创建{}文件夹失败", filePath);
                }
            }
            // 定义文件输出流
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path + name + fileName);
//            fos = new FileOutputStream(path + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < blockNum; i++) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(path + "temp_file\\" + fileName + "_" + (i + 1));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                int count;
                try {
                    while (true) {
                        assert fis != null;
                        if (!((count = fis.read(buffer)) > 0)) break;
                        assert fos != null;
                        fos.write(buffer, 0, count);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            long end_time = new Date().getTime();
            long seconds = (end_time - begin_time) / 1000;
            long minutes = seconds / 60;
            long second = seconds % 60;
            log.info("下载完成,用时：{}分{}秒", minutes, second);
            boolean b = delDir(new File(path + "temp_file\\"));
            if (b) {
                log.info("成功删除临时文件");
            } else {
                log.error("删除临时文件失败");
            }
        } else {
            log.info("当前文件已存在:{}", fileName);
        }

    }
}
