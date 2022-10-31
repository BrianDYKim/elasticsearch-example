package com.example.elasticsearch.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter

/**
 * @author Doyeop Kim
 * @since 2022/10/31
 */
abstract class AbstractElasticSearchConfiguration {
    @Bean
    abstract fun elasticsearchClient(): RestHighLevelClient

    @Bean
    fun elasticsearchOperations(
        elasticsearchConverter: ElasticsearchConverter,
        elasticsearchClient: RestHighLevelClient
    ): ElasticsearchOperations {

        return ElasticsearchRestTemplate(elasticsearchClient, elasticsearchConverter)
    }
}