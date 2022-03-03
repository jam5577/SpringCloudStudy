package com.jam.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @program: SpringCloudStudy
 * @description: 测试jsoup
 * @author: Mr.Pu
 * @create: 2022-02-21 17:59
 **/
@Slf4j
public class JsoupTest {
    final String http = "https://www.bilibili.com/";
    Document doc = Jsoup.connect("https://www.bilibili.com/").get();

    public JsoupTest() throws IOException {
        log.info(doc.title());
    }

    @Test
    public void test() {
        log.info("doc.title():{}",doc.title());
        log.info("doc.location():{}",doc.location());
//        log.info("doc.data():{}",doc.data());
        log.info("doc.baseUri():{}",doc.baseUri());
//        log.info("doc.html():{}",doc.html());
//        log.info("doc.head().html():{}",doc.head().html());
        log.info("doc.body().tagName():{}",doc.body().tagName());
        Elements elements = doc.select(".inner-logo");
        Elements select = doc.html(http).select(".inner-logo");
        for (Element element : select) {
            log.info("element:{}",element.text());
        }
    }
}
