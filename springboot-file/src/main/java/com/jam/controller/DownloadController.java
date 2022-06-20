package com.jam.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: SpringCloudStudy
 * @description: 下载
 * @author: Mr.Pu
 * @create: 2022-06-14 20:36
 **/

@RestController
@Slf4j
public class DownloadController {

    private static final String UTF8 = "utf-8";

    private static final String FILE_PATH = "E:\\Downloads\\mongodb-linux-x86_64-rhel70-4.4.14.tgz";

    /**
     * 分片下载的分片大小
     */
    private static final long PARTIAL_PAGE = 1024L * 1024L * 5L;

    /**
     * 临时文件目录，将每个分片放入其中，在最后下载完成后再合并为一个文件
     */
    private static final String TEMP_PATH = "E:\\Downloads\\download-temp";

    /**
     * 定义线程池
     */
    ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * 暂时下载指定文件
     * 分片下载其实就是将文件读取为bytes的字节流，通过设置分片大小来进行下载
     * 这样就会多次调用接口进行下载
     */
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(FILE_PATH);
        response.setCharacterEncoding(UTF8);
        InputStream is = null;
        OutputStream os = null;
        try {
            //分片下载，首先拿到文件的大小
            long size = file.length();
            //为了处理可能的中文乱码问题，需要使用下面的方法对文件名进行utf8编码
            String filename = URLEncoder.encode(file.getName(), UTF8);
            //设置response的类型
            response.setContentType("application/x-download");
            //设置response的响应头，可以告诉浏览器这是一个下载的响应
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            //在前端传过来的request中，会有一个值是 Accept-Range 表示响应是否是支持分片下载
            //在response中需要设置这个值为分片下载的单位量，告诉浏览器这是分片下载
            response.setHeader("Accept-Range", "bytes");
            //设置自定义响应头，使得客户端知道文件的大小，由此知道分片数
            response.setHeader("size", String.valueOf(size));
            //设置自定义响应头，使得客户端知道文件名
            response.setHeader("filename", filename);
            //定义读文件每个分片的起始位置，总长度，到某一时刻读取了多少
            long pos = 0, last = size - 1, sum = 0;
            //判断前端是不是需要分片下载
            if (request.getHeader("Range") != null) {
                //因为会有多次接口调用，因此在每一次请求后不能将返回值设置为200，200会告诉浏览器本次请求结束
                //需要使用206状态码，告诉浏览器这是分片下载的请求，不能将请求中断
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                //从request中获取到当前分片请求的信息
                //request中可拿到Range的头信息，大概格式为：(key: Range, value: bytes=1-100)
                //Range也有可能是 (bytes=1000-) 这表示从当前起始位置读到结尾
                String range = request.getHeader("Range").replaceAll("bytes=", "");
                String[] ranges = range.split("-");
                //判断是否是读到结尾的操作
                if (ranges.length == 2) {
                    pos = Long.parseLong(ranges[0].trim());
                    last = Long.parseLong(ranges[1].trim());
                    //在分片信息中有可能结尾值是大于文件大小的，所以要做判断
                    if (last > size - 1) {
                        last = size - 1;
                    }
                } else {
                    pos = Long.parseLong(range.replaceAll("-", "").trim());
                }
            }
            //获取总共需要读取的字节数大小
            long length = last - pos + 1;
            //需要通知客户端，当前读取的分片是哪一个以及分片大小是多少
            String contentRange = "bytes=" +
                    //加入起始位置信息
                    pos +
                    //加入结束位置信息
                    "-" +
                    last +
                    //加入文件大小
                    "/" +
                    size;
            //将信息设置到响应头中
            response.setHeader("Content-Range", contentRange);
            //将当前读取的分片大小设置到响应头中
            response.setHeader("Content-Length", String.valueOf(length));

            //开始文件下载
            //获取到response的输出流，也就是一个文件输出到浏览器的流
            os = new BufferedOutputStream(response.getOutputStream());
            //获取文件输入流，也就是将文件读取入流中
            is = new BufferedInputStream(Files.newInputStream(file.toPath()));
            //在下载第一个之后的分片时，需要将之前已经读过的分片跳过
            is.skip(pos);

            //读取文件的流操作
            //定义缓冲区
            byte[] buffer = new byte[1024];
            //当读取的数量还不达到总长度时
            int currentLength;
            while (sum < length) {
                currentLength = is.read(buffer, 0, (length - sum) <= buffer.length ? (int) (length - sum) : buffer.length);
                sum += currentLength;
                os.write(buffer, 0, currentLength);
            }
            log.info("当前文件:{}下载完成", filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * 分片下载
     * 文件大小决定了分片数量，需要文件名称来生成文件
     * 使用探测来获取文件相关信息，也就是先下载几个字节的文件，然后再开始下载
     */
    @RequestMapping("/partial-download")
    public String download() {
        //探测获取文件信息，读取10个字节信息，并将page设置为-1
        FileInfo fileInfo = preDownload(0, 10, -1, null);
        if (fileInfo == null) {
            return "failure";
        }
        //计算分片数量
        long pages = fileInfo.filesize / PARTIAL_PAGE;
        log.info("文件大小为:{}", fileInfo.filesize);
        log.info("分片数量为:{}", pages);
        //对于每一个分片都用一个线程去下载，定义内部类来存储相关下载信息
        for (int i = 0; i <= pages; i++) {
            pool.submit(DownloadInfo.builder()
                    //开始位置是第i个分片的开始位置
                    .start(i * PARTIAL_PAGE)
                    //终止位置是下一个分片的开始位置 - 1
                    .end((i + 1) * PARTIAL_PAGE - 1)
                    //当前分片的位置
                    .page(i)
                    //当前分片文件名
                    .filename(fileInfo.filename)
                    .build());
        }
        return "success";
    }

    /**
     * 先使用探测方法来下载文件的信息，返回两个参数信息
     * 然后开始分片下载
     */
    private static FileInfo preDownload(long start, long end, long page, String filename) {
        //获取到临时目录的文件，首先进行判断如果是下载好了的就不用再下载了
        File file = new File(TEMP_PATH, page + "-" + filename);
        //如果是探测任务的话，需要重新探测，以及如果分片长度不对也需要重新下载
        if (file.exists() && page != -1 && file.length() == PARTIAL_PAGE) {
            return null;
        }
        //获取到的文件大小
        long filesize = 0;
        //使用第三方包生成一个客户端
        HttpClient httpClient = HttpClients.createDefault();
        //使用get下载
        HttpGet httpGet = new HttpGet("http://127.0.0.1:9000/download");
        //设置响应头来告诉浏览器是分片下载
        httpGet.setHeader("Range", "bytes=" + start + "-" + end);

        //获取到响应值
        try {
            //执行请求
            HttpResponse response = httpClient.execute(httpGet);
            //获取文件大小
            filesize = Long.parseLong(response.getFirstHeader("size").getValue());
            //获取文件名称
            filename = URLEncoder.encode(response.getFirstHeader("filename").getValue(), UTF8);
            //获取entity以及其中的流
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            //获取文件输出流，将文件下载到此处
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            //定义一个判断值进行判断是否下载完毕
            int ch;
            while ((ch = is.read(buffer)) != -1) {
                //写文件
                fos.write(buffer, 0, ch);
            }
            is.close();
            fos.flush();
            fos.close();
            log.info("文件下载完成:{}", filename);
            //当最后一个分片下载完成后进行合并操作
            if (end - filesize > 0) {
                merge(filename, page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FileInfo(filesize, filename);
    }

    /**
     * 合并文件方法
     *
     * @param filename 文件名
     * @param page     合并的分片
     */
    private static void merge(String filename, long page) throws Exception {
        log.info("调用了merge方法");
        //获取file对象
        File file = new File(TEMP_PATH, filename);
        //获取输出流
        BufferedOutputStream os = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
        for (int i = 0; i <= page; i++) {
            //对于每一个分片都进行合并
            File temp = new File(TEMP_PATH, i + "-" + filename);
            //因为是多线程下载，可能出现某个分片还未下载完或者还未开始下载，需要进行判断
            //第二个参数是指如果当前分片不是最后一个分片，并且分片大小小于设定的分片大小，那么临时文件还未下载完
            while (!file.exists() || (i != page && temp.length() < PARTIAL_PAGE)) {
                Thread.sleep(100);
            }
            //使用工具类将临时文件转为字节流
            byte[] bytes = FileUtils.readFileToByteArray(temp);
            //写出文件
            os.write(bytes);
            os.flush();
            //删除临时文件
            boolean delete = temp.delete();
            if (delete) {
                log.info("临时文件{}已删除", temp.getName());
            }
        }
        //删除探测文件
        boolean delete = new File(TEMP_PATH, -1 + "-null").delete();
        if (delete) {
            log.info("探测文件{}已删除", -1 + "-null");
        }
        os.flush();
        os.close();
    }

    /**
     * 探测到的文件信息
     */
    @AllArgsConstructor
    static class FileInfo {
        long filesize;
        String filename;
    }

    @AllArgsConstructor
    @Builder
    static class DownloadInfo implements Runnable {
        long start;
        long end;
        long page;
        String filename;

        @Override
        public void run() {
            preDownload(start, end, page, filename);
        }
    }
}
