package com.jam.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.nextfilter.HashSetNextFilter;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @program: SpringCloudStudy
 * @description: 爬虫工具
 * @author: Mr.Pu
 * @create: 2022-05-08 14:33
 **/

public class Crawler extends BreadthCrawler {
    public static final Logger LOG = LoggerFactory.getLogger(Crawler.class);

    protected String historyKeysPath;
    protected BufferedWriter historyKeysWriter;

    public Crawler(String crawlPath, String historyKeysPath) throws Exception {
        super(crawlPath, false);
        this.historyKeysPath = historyKeysPath;
        LOG.info("initializing history-keys-filter ......");
        this.setNextFilter(new HistoryKeysFilter(historyKeysPath));
        LOG.info("creating history-keys-writer");
        historyKeysWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(historyKeysPath, true), StandardCharsets.UTF_8));

    }

    /**
     * 构造一个基于RocksDB的爬虫
     * RocksDB文件夹为crawlPath，crawlPath中维护了历史URL等信息
     * 不同任务不要使用相同的crawlPath
     * 两个使用相同crawlPath的爬虫并行爬取会产生错误
     *
     * @param crawlPath RocksDB使用的文件夹
     * @param autoParse 是否根据设置的正则自动探测新URL
     */
    public Crawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String account = page.meta("account");

        if (page.matchType("account_search")) {
            //对于账号搜索页面

            //抽取公众号文章列表页URL
            Element accountLinkEle = page.select("p.tit>a").first();
            //防止搜索结果为空
            if (accountLinkEle == null) {
                LOG.info("公众号\"" + account + "\"不存在，请给出准确的公众号名");
                return;
            }
            //防止公众号名错误
            String detectedAccount = accountLinkEle.text().trim();
            if (!account.equals(detectedAccount)) {
                LOG.info("公众号\"" + account + "\"与搜索结果\"" + detectedAccount + "\"名称不符，请给出准确的公众号名");
                return;
            }
            String accountUrl = accountLinkEle.attr("abs:href");
            next.add(new CrawlDatum(accountUrl, "article_list").meta("account", account));

        } else if (page.matchType("article_list")) {
            //对于公众号文章列表页

            String prefix = "msgList = ";
            String suffix = "seajs.use";
            int startIndex = page.html().indexOf(prefix) + prefix.length();
            int endIndex = page.html().indexOf(suffix);
            String jsonStr = page.html().substring(startIndex, endIndex);
            JSONObject json = new JSONObject(jsonStr);
            JSONArray articleJSONArray = json.getJSONArray("list");
            for (int i = 0; i < articleJSONArray.length(); i++) {
                JSONObject articleJSON = articleJSONArray.getJSONObject(i).getJSONObject("app_msg_ext_info");
                String title = articleJSON.getString("title").trim();
                String key = account + "_" + title;
                String articleUrl = "http://mp.weixin.qq.com" + articleJSON.getString("content_url").replace("&", "&");
                next.add(new CrawlDatum(articleUrl, "article").key(key).meta("account", account));
            }

        } else if (page.matchType("article")) {
            //对于文章页
            //抽取标题、内容等信息，此处仅print少数信息作为参考
            String title = page.select("h2.rich_media_title").first().text().trim();
            String date = page.select("em#post-date").first().text().trim();
            String content = page.select("div.rich_media_content").first().text().trim();

            try {
                writeHistoryKey(page.key());
                JSONObject articleJSON = new JSONObject();
                articleJSON.append("account", account)
                        .append("title", title)
                        .append("date", date)
                        .append("content", content);
                System.out.println(articleJSON);
            } catch (IOException ex) {
                LOG.info("writer exception", ex);
            }

        }
    }

    public synchronized void writeHistoryKey(String key) throws IOException {
        historyKeysWriter.write(key + "\n");
    }

    @Override
    public void start(int depth) throws Exception {
        super.start(depth);
        //关闭文件，保存history keys
        historyKeysWriter.close();
        LOG.info("save history keys");
    }

    public void addAccount(String account) throws UnsupportedEncodingException {
        String seedUrl = "http://weixin.sogou.com/weixin?type=1&"
                + "s_from=input&ie=utf8&query=" + URLEncoder.encode(account, "utf-8");
        CrawlDatum seed = new CrawlDatum(seedUrl, "account_search").meta("account", account);
        addSeed(seed);
    }

    //该示例读取文件中的key进行文章去重
    //线上应用请老老实实用数据库
    public static class HistoryKeysFilter extends HashSetNextFilter {

        //读取历史文章标题，用于去重
        public HistoryKeysFilter(String historyKeysPath) throws Exception {
            File historyFile = new File(historyKeysPath);
            if (historyFile.exists()) {
                FileInputStream fis = new FileInputStream(historyKeysPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    this.add(line);
                }
                reader.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Crawler crawler = new Crawler("crawl_weixin", "wx_history.txt");
        crawler.addAccount("同济大学");
//        crawler.addAccount("ZEALER订阅号");
        crawler.setThreads(5);
        crawler.start(10);
    }

}
