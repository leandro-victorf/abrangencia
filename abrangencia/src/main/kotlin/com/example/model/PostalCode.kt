package com.example.model

import io.micronaut.core.annotation.Introspected
import org.bson.codecs.pojo.annotations.BsonProperty

@Introspected
data class PostalCode(
    @field:BsonProperty("postalCode")
    val postalCode: String,
    @field:BsonProperty("city")
    val city: String,
    @field:BsonProperty("state")
    val state: String,
    @field:BsonProperty("codeType")
    val codeType: String
)
