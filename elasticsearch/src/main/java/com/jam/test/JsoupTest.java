package com.jam.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-03-23 19:23
 **/

public class JsoupTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> list = new ArrayList<>();
        Document parse = Jsoup.parse(new URL("https://www.xiurenb.net//XiuRen/10311.html"), 300000);
//        Elements select = parse.select("div.content > img");
        Elements select = parse.select("div.content");
        Elements elements = select.select("[href]");
//        String attr = elements.attr("abs:href");
        String src = select.attr("src");
//        System.out.println(src);
//        System.out.println(attr);
//        System.out.println(select);
//        System.out.println(elements);
        for (Element element : elements) {
            list.add(element.attr("abs:href"));
        }
        list = list.stream().distinct().collect(Collectors.toList());
        list.forEach(System.out::println);

//        ParseUtil.download("https://t.xiurenb.net/Uploadfile/202203/15/7C163222327.jpg");
    }

}
