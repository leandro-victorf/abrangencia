package com.example.repository

import com.example.model.PostalCode
import com.example.model.ShippingCompany
import com.example.mongo.MongoDbConfiguration
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters

class ShippingCompanyRepositoryImpl(private val mongoClient: MongoClient, private val conf: MongoDbConfiguration) :
    ShippingCompanyRepository {
    override fun getByShippingCompany(shippingCompany: String): ShippingCompany? {
        return getCollection().find(Filters.eq("shippingCompany", shippingCompany)).first()
    }

    private fun getCollection(): MongoCollection<ShippingCompany> {
        return mongoClient.getDatabase(conf.name)
            .getCollection(conf.collectionShippingCompany, ShippingCompany::class.java)
    }
}