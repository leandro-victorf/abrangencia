package com.example.repository

import com.example.model.PostalCode
import com.example.mongo.MongoDbConfiguration
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import jakarta.inject.Singleton

@Singleton
class PostalCodeRepositoryImpl(private val mongoClient: MongoClient, private val conf: MongoDbConfiguration) :
    PostalCodeRepository {
    override fun getByPostalCode(postalCode: String): PostalCode? {
        return getCollection().find(eq("postalCode", postalCode)).first()
    }

    private fun getCollection(): MongoCollection<PostalCode> {
        return mongoClient.getDatabase(conf.name)
            .getCollection(conf.collectionPostalCode, PostalCode::class.java)
    }
}
