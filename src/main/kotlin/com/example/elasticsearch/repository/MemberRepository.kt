package com.example.elasticsearch.repository

import com.example.elasticsearch.entity.Member
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

/**
 * @author Doyeop Kim
 * @since 2022/10/31
 */
interface MemberRepository: ElasticsearchRepository<Member, String> {
    fun findAllByAge(age: Int): List<Member>
}