package com.example.elasticsearch.repository

import com.example.elasticsearch.Shop
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

/**
 * @author Brian
 * @since 2022/11/01
 */
@Repository
interface ShopRepository : ElasticsearchRepository<Shop, String>, ShopPoiRepository {
    fun findAllByName(name: String): List<Shop>
}