package com.example.elasticsearch.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.*
import java.time.LocalDateTime

/**
 * @author Doyeop Kim
 * @since 2022/10/31
 */
@Document(indexName = "member")
@Mapping(mappingPath = "elastic/member-mapping.json")
@Setting(settingPath = "elastic/member-setting.json")
class Member(
    @Id
    var id: String = "",
    var name: String = "",
    var nickname: String = "",
    var age: Int = 0,
    var status: Status = Status.REGISTERED,
    var description: String = "",
    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis])
    var createdAt: LocalDateTime = LocalDateTime.now()
) {

}