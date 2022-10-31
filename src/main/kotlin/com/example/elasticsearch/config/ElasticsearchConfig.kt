package com.example.elasticsearch.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

/**
 * @author Doyeop Kim
 * @since 2022/10/31
 */
@Configuration
@EnableElasticsearchRepositories
class ElasticsearchConfig : AbstractElasticSearchConfiguration() {

    override fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build()

        return RestClients.create(clientConfiguration)
            .rest()
    }
}