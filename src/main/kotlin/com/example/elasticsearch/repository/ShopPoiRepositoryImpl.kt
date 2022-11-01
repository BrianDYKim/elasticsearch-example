package com.example.elasticsearch.repository

import com.example.elasticsearch.Shop
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder
import org.springframework.stereotype.Repository

/**
 * @author Brian
 * @since 2022/11/01
 */
@Repository
class ShopPoiRepositoryImpl(
    private val elasticsearchOperations: ElasticsearchOperations
) : ShopPoiRepository {

    override fun searchWithIn(geoPoint: GeoPoint, distance: Double, unit: String): List<Shop> {
        val criteria = Criteria.where("location").within(geoPoint, distance.toString() + unit)
        val query = CriteriaQuery.builder(criteria)
            .withSort(Sort.by(GeoDistanceOrder("location", geoPoint).withUnit(unit))) // 거리의 오름차순으로 검색한다
            .build()

        val searchResult = elasticsearchOperations.search(query, Shop::class.java)

        return searchResult.searchHits.map { it.content }
    }
}