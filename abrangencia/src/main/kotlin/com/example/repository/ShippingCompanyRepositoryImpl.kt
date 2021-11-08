package com.example.repository

import com.example.model.ShippingCompany
import com.example.mongo.MongoDbConfiguration
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.UpdateResult
import jakarta.inject.Singleton

@Singleton
class ShippingCompanyRepositoryImpl(private val mongoClient: MongoClient, private val conf: MongoDbConfiguration) :
    ShippingCompanyRepository {
    override fun getByShippingCompany(shippingCompany: String): ShippingCompany? {
        return getCollection().find(Filters.eq("shippingCompany", shippingCompany)).first()
    }

    override fun getShippingCompany(shippingCompany: String): List<ShippingCompany> {
        return getCollection().find(Filters.eq("shippingCompany", shippingCompany)).toList()
    }

    override fun addShippingCompany(company: ShippingCompany): InsertOneResult {
        return getCollection().insertOne(company)
    }

    override fun updateShippingCompany(company: ShippingCompany): UpdateResult {
       return getCollection().updateOne(Filters.eq("id", company.id))
    }

    override fun deleteShippingCompany(company: ShippingCompany): DeleteResult {
        return getCollection().deleteOne(Filters.eq("id", company.id))
    }


    private fun getCollection(): MongoCollection<ShippingCompany> {
        return mongoClient.getDatabase(conf.name)
            .getCollection(conf.collectionShippingCompany, ShippingCompany::class.java)
    }
}



}
