package com.jam.spring.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @program: SpringCloudStudy
 * @description: es配置类
 * @author: Mr.Pu
 * @create: 2022-06-10 23:50
 **/

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    //java交互用9200，集群架构心跳检测用9300
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration build = ClientConfiguration.builder()
                .connectedTo("192.168.52.129:9200")
                .build();
        return RestClients.create(build).rest();
    }
}
