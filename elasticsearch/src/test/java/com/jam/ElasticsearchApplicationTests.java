package com.jam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Test
    void contextLoads() throws IOException {
        Document parse = Jsoup.parse(new URL("https://www.xiurenb.net//XiuRen/10311.html"), 300000);
        Elements select = parse.select("div.content > img");
        String src = select.attr("src");
        System.out.println(src);

    }

}
