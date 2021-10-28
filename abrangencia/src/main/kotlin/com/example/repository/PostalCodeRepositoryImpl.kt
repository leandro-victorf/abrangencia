package com.example.repository

import com.example.model.PostalCode
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import jakarta.inject.Singleton

@Singleton
class PostalCodeRepositoryImpl(private val mongoClient: MongoClient) : PostalCodeRepository {
    override fun getByPostalCode(postalCode: String): PostalCode? {
        return getCollection().find(eq("postalCode", postalCode)).first()
    }

    private fun getCollection(): MongoCollection<PostalCode> {
        return mongoClient.getDatabase("abrangencia")
            .getCollection("postalCode", PostalCode::class.java)
    }
}
