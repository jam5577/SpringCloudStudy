package com.jam.adapter.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloudStudy
 * @description: es配置类
 * @author: Mr.Pu
 * @create: 2022-03-22 18:38
 **/

@Configuration
public class ElasticSearchConfig {

    @Bean
    @Deprecated
    public RestHighLevelClient restClient() {
        RestHighLevelClient restClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")
                )
        );
        return restClient;
    }

    @Bean
    public ElasticsearchClient client() {
        RestClient http = RestClient.builder(
                new HttpHost("127.0.0.1", 9200, "http")).build();

        ElasticsearchTransport transport = new RestClientTransport(http, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }


}
