package com.example.elasticsearch

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

/** product entity 24분까지 봄
 * @author Brian
 * @since 2022/11/01
 */
@Document(indexName = "products")
class Product(
    @Id
    var id: String = "",
    @Field(type = FieldType.Text, name = "name")
    var name: String = "",
    @Field(type = FieldType.Text, name = "description")
    var description: String = "",
    @Field(type = FieldType.Double, name = "price")
    var price: Double = 0.0
) {

}