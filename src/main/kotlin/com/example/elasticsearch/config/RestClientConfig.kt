package com.example.elasticsearch.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate

/**
 * @author Brian
 * @since 2022/11/01
 */
@Configuration
class RestClientConfig : AbstractElasticsearchConfiguration() {

    @Bean
    override fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build()

        return RestClients.create(clientConfiguration).rest()
    }

    @Bean
    fun elasticsearchOperations(): ElasticsearchOperations {
        return ElasticsearchRestTemplate(elasticsearchClient())
    }
}