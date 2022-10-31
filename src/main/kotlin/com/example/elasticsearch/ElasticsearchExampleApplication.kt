package com.example.elasticsearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElasticsearchExampleApplication

fun main(args: Array<String>) {
    runApplication<ElasticsearchExampleApplication>(*args)
}
