package com.example.elasticsearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// https://www.youtube.com/watch?v=0hq3_JpTAbk&t=1578s
@SpringBootApplication
class ElasticsearchExampleApplication

fun main(args: Array<String>) {
    runApplication<ElasticsearchExampleApplication>(*args)
}
