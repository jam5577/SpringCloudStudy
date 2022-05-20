package com.jam.app.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson.JSON;
import com.jam.app.entity.Content;
import com.jam.app.service.ContentService;
import com.jam.utils.ParseUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.client.RequestOptions.DEFAULT;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-03-23 14:06
 **/

@Service
public class ContentServiceImpl implements ContentService {

    //    private final ElasticsearchClient client;
//
    @Autowired
    private RestHighLevelClient restClient;

//    @Autowired
//    public ContentServiceImpl(@Qualifier("client") ElasticsearchClient client, RestHighLevelClient restClient) {
//        this.client = client;
//        this.restClient = restClient;
//    }

    RestClient http = RestClient.builder(
            new HttpHost("127.0.0.1", 9200, "http")).build();

    ElasticsearchTransport transport = new RestClientTransport(http, new JacksonJsonpMapper());

    ElasticsearchClient client = new ElasticsearchClient(transport);

    @Override
    public boolean parseContent(String keyword) throws IOException {

        //1.解析数据
        List<Content> parse = ParseUtil.parse(keyword);
        //2.数据放入es中
        BulkRequest request = new BulkRequest();
        request.timeout("2m");
        for (Content content : parse) {
            request.add(
                    new IndexRequest("jd_goods")
                            .source(JSON.toJSONString(content), XContentType.JSON));
        }
//        Response response = client.(request);
        BulkResponse bulk = restClient.bulk(request, DEFAULT);

        return !bulk.hasFailures();
    }

    @Override
    public List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) pageNo = 1;
        //条件搜索
        SearchRequest request = new SearchRequest("jd_goods");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //分页
        builder.from(pageNo);
        builder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQuery = QueryBuilders.termQuery("title", keyword);
        builder.query(termQuery);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(builder);
        SearchResponse response = restClient.search(request, DEFAULT);

        RequestOptions.Builder toBuilder = DEFAULT.toBuilder();
//        RequestConfig.DEFAULT.
//        toBuilder.setRequestConfig()
//        Request get = new Request("GET", "/");
//        get.setOptions();

        ArrayList<Map<String, Object>> list = new ArrayList<>();

        for (SearchHit hit : response.getHits()) {
            list.add(hit.getSourceAsMap());
        }

//        SearchResponse<Content> search = client.search(s -> s.index("jd_goods").query(q -> q.term(t -> t.field("name").value(v -> v.stringValue("java")))), Content.class);
//
//        for (Hit<Content> hit : search.hits().hits()) {
//            list.add(hit.highlight());
//        }
        return list;
    }
}
