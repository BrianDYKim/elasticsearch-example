package com.example.elasticsearch.repository

import com.example.elasticsearch.Shop
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.geo.GeoPoint
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.data.elasticsearch.core.query.CriteriaQueryBuilder
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import java.util.UUID

/**
 * @author Brian
 * @since 2022/11/01
 */
@SpringBootTest
class ShopRepositoryTest @Autowired constructor(
    private val shopRepository: ShopRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) {

    @Test
    @DisplayName("Product save test")
    fun save() {
        // given
        val id = UUID.randomUUID().toString()
        val name = "파리바게트 시지사월점"
        val description = "파리바게트 시지사월점입니다."
        val location = GeoPoint(35.8394218, 128.7146896)
        val givenShop = Shop(id, name, description, location)

        // when
        val savedShop = shopRepository.save(givenShop)

        // then
        assertEquals(savedShop.id, id)
    }

    @Test
    @DisplayName("Name에 대해서 완전 검색이 가능한지 테스트")
    fun fullNameSearchTest() {
        // given
        val name = "테스트 가게"

        // when
        val shopList = shopRepository.findAllByName(name)

        // then
        shopList.forEach {
            println(it.name)
            println(it.description)
        }
    }

    @Test
    @DisplayName("Name에 대해서 부분 검색이 가능한지 테스트")
    fun partialNameSearchTest() {
        // given
        val partialName = "동해물과"

        // when
        val shopList = shopRepository.findAllByName(partialName)

        // then
        shopList.forEach {
            println(it.name)
            println(it.description)
        }
    }

    @Test
    @DisplayName("반경 검색 테스트")
    fun radiusSearchTest() {
        // given
        val searchPoint = GeoPoint(35.831728870626556, 128.7585397591836) // 영남대학교 위치

        // when
        val criteria = Criteria.where("location").within(searchPoint, "10km")
        val query = CriteriaQuery.builder(criteria).build()
        val search = elasticsearchOperations.search(query, Shop::class.java)

        // then
        search.searchHits.forEach {
            println(it.content.name)
        }
    }

    @Test
    @DisplayName("페이징 처리 테스트")
    fun paginationTest() {
        // given
        val searchPoint = GeoPoint(35.831728870626556, 128.7585397591836) // 영남대학교 위치

        // when
        val criteria = Criteria.where("location").within(searchPoint, "10km") // 영남대학교 10km 인근의 가게를 검색한다
        val pageable = Pageable.ofSize(5)
        val query = CriteriaQuery.builder(criteria)
            .withSort(Sort.by(GeoDistanceOrder("location", searchPoint).withUnit("km"))) // 내림차순으로 정렬하여
            .withPageable(pageable) // 2개의 가게만 가져온다
            .build()
        val search = elasticsearchOperations.search(query, Shop::class.java)

        // then
        search.forEach {
            println(it.content.name)
        }
    }

    @Test
    @DisplayName("POI repository 테스트")
    fun withInSearchTest() {
        // given
        val searchPoint = GeoPoint(35.831728870626556, 128.7585397591836) // 영남대학교 위치
        val distance = 10.0
        val unit = "km"

        // when
        val shopList = shopRepository.searchWithIn(searchPoint, distance, unit)

        // then
        shopList.forEach {
            println(it.name)
        }
    }

    @Test
    @DisplayName("가게 삭제 테스트")
    fun deleteShopTest() {
        // given
        val id = "8ed688e1-c448-4220-a830-4c8c97393b6c"

        // when
        shopRepository.deleteById(id)
    }
}