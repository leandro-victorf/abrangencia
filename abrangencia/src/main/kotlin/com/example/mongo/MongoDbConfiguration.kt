package com.example.mongo

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("db")
class MongoDbConfiguration {
    lateinit var name: String
    lateinit var collectionPostalCode: String
    lateinit var collectionShippingCompany: String
}
