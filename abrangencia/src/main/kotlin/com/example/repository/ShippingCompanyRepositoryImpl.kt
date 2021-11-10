package com.example.repository

import com.example.model.ShippingCompany
import com.example.mongo.MongoDbConfiguration
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.elemMatch
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.lte
import com.mongodb.client.model.Filters.gte
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.UpdateResult
import jakarta.inject.Singleton
import org.bson.Document
import org.bson.types.ObjectId

@Singleton
class ShippingCompanyRepositoryImpl(private val mongoClient: MongoClient, private val conf: MongoDbConfiguration) :
    ShippingCompanyRepository {
    override fun getByShippingCompany(id: String): ShippingCompany? {
        return getCollection().find(Filters.eq("_id",ObjectId(id))).first()
    }

    override fun getShippingCompany(shippingCompany: String): List<ShippingCompany> {
        return getCollection().find().toList()
    }

    override fun getByPostalCodeShippingCompany(postalCode: String): List<ShippingCompany> {
        return getCollection().find(
            elemMatch("ranges", and(lte("start", postalCode),gte("end", postalCode)))
        ).toList()
        //{"ranges":{$elemMatch:{$and:[{"end":{$gte:"09290800"}},{"start":{$lte:"09290800"}}]}}}
    }

    override fun addShippingCompany(company: ShippingCompany): InsertOneResult {
        return getCollection().insertOne(company)
    }

    override fun updateShippingCompany(company: ShippingCompany): UpdateResult {
       return getCollection().updateOne(
           Filters.eq("id", company.id),
           Document("\$set",company)
       )
    }

    override fun deleteShippingCompany(id: String): DeleteResult {
        return getCollection().deleteOne(Filters.eq("_id",ObjectId(id) ))
    }


    private fun getCollection(): MongoCollection<ShippingCompany> {
        return mongoClient.getDatabase(conf.name)
            .getCollection(conf.collectionShippingCompany, ShippingCompany::class.java)
    }
}




