package com.example.elasticsearch

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.ElasticsearchTransport
import co.elastic.clients.transport.rest_client.RestClientTransport
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Brian
 * * since 2022/11/01
 */
@Configuration
class ElasticsearchConfig {

    // This method use to configure the URL and port on which Elasticsearch is running
    @Bean
    fun restClient(): RestClient {
        val restClient = RestClient.builder(
            HttpHost("localhost", 9200)
        ).build()

        return restClient
    }

    // It returns the Transport object, whose purpose is it automatically map the our Mdel Class to Json
    // and integrates them with API client
    @Bean
    fun elasticsearchTransport(): ElasticsearchTransport {
        return RestClientTransport(
            restClient(), JacksonJsonpMapper()
        )
    }

    // Elasticsearch client which we further use to perform all query operation with Elasticsearch
    @Bean
    fun elasticsearchClient(): ElasticsearchClient {
        return ElasticsearchClient(elasticsearchTransport())
    }
}