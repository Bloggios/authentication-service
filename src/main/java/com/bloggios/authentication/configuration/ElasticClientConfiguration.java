package com.bloggios.authentication.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - authentication-service
 * Package - com.bloggios.authentication.configuration
 * Created_on - August 28 - 2024
 * Created_at - 23:00
 */

@Configuration
@EnableElasticsearchRepositories
@ComponentScan(basePackages = "com.bloggios.authentication")
public class ElasticClientConfiguration extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        HttpHeaders compatibilityHeaders = new HttpHeaders();
        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        compatibilityHeaders.add("Content-Type", "application/vnd.elasticsearch+json;"
                + "compatible-with=7");
        ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .connectedTo("localhost:9200")
                .withBasicAuth("elastic", "1234")
                .withDefaultHeaders(compatibilityHeaders)
                .build();
        return RestClients
                .create(clientConfiguration)
                .rest();
    }

    @Bean
    public ElasticsearchOperations template() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
